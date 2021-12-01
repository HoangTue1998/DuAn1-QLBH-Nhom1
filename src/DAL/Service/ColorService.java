/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAL.Service;

import DAL.Entity.Color;
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
public class ColorService implements WareHouse<Color, Integer>{

    @Override
    public void insert(Color entity) {
       String sql  = "insert into color(temMau, trangThai)  values (?,?)";
       JdbcHelper.executeUpdate(sql, entity.getTenMau(), entity.isTrangThai());
               
    }

    @Override
    public void update(Color entity) {
        String sql = "update color set temMau =?, TrangThai=? where  maMau=?";
        JdbcHelper.executeUpdate(sql, entity.getTenMau(), entity.isTrangThai(), entity.getMaMau());
    }

    @Override
    public void delete(Integer key) {
        String sql  = "delete form color where tenMau =?";
       JdbcHelper.executeUpdate(sql, key);
    }

    @Override
    public List<Color> selectALL() {
         return selectbySQL("select * from color where trangThai =1");
    }

    @Override
    public Color select_by_id(Integer key) {
        List<Color> list = selectbySQL("select * from color where maMau=?", key);
        if(list.isEmpty()){
            return null;
        }
        return list.get(0);
    }

    @Override
    public List<Color> selectbySQL(String sql, Object... arg) {
          List<Color> listSize =new ArrayList<>();
        try {
           
            ResultSet rs = JdbcHelper.executeQuery(sql, arg);
            while (rs.next()) {
                Color color = new Color();
                color.setMaMau(rs.getInt("maMau"));
                color.setTenMau(rs.getString("temMau"));
                color.setTrangThai(rs.getBoolean("TrangThai"));
                listSize.add(color);
                
            }
        } catch (SQLException ex) {
            Logger.getLogger(ProductService.class.getName()).log(Level.SEVERE.SEVERE, null, ex);
        }
        return listSize;
    }
    
    
}
