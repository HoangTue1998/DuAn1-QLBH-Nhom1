/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAL.Service;

import DAL.Entity.Size;
import Utils.JdbcHelper;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Minh
 */
public class SizeService implements WareHouse<Size, Integer> {

    @Override
    public void insert(Size entity) {
        String sql= "insert into size(TenSize, TrangThai) values (?, ?)";
        JdbcHelper.executeUpdate(sql, entity.getMaSize(), entity.isTrangThai());
    }

    @Override
    public void update(Size entity) {
        String sql= "update size set TenSize=?, TrangThai=? where maSize=?";
        JdbcHelper.executeUpdate(sql, entity.getTenSize(),entity.isTrangThai(), entity.getMaSize());
    }

    @Override
    public void delete(Integer key) {
        String sql= "Delete from size where maSize=?";
        JdbcHelper.executeUpdate(sql, key);
 
    }

    @Override
    public List<Size> selectALL() {
        return selectbySQL("select * from size where trangThai =1");
    }

    @Override
    public Size select_by_id(Integer key) {
        List<Size> list = selectbySQL("select * from size where maSize=?", key);
        if(list.isEmpty()){
        return null;}
        return list.get(0);
    }

    @Override
    public List<Size> selectbySQL(String sql, Object... arg) {
         List<Size> listSize = new ArrayList<>();
        try {
           
            ResultSet rs = JdbcHelper.executeQuery(sql, arg);
            while (rs.next()) {
                Size size = new Size();
                size.setMaSize(rs.getInt("maSize"));
                size.setTenSize(rs.getString("TenSize"));
                size.setTrangThai(rs.getBoolean("TrangThai"));
                listSize.add(size);
                
            }
        } catch (SQLException ex) {
            Logger.getLogger(ProductService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return listSize;
        
    }

    
    
    
}
