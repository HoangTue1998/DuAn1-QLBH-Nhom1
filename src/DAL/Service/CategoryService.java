/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAL.Service;

import DAL.Entity.Category;
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
public class CategoryService implements WareHouse<Category, String>{

    @Override
    public void insert(Category entity) {
        String sql = "Insert into Category(MaTheLoai, TenTheLoai, TrangThai) values (?,?,?)";
        JdbcHelper.executeUpdate(sql, entity.getMaTheLoai(), entity.getTenTheLoai(), entity.isTrangThai());
    }

    @Override
    public void update(Category entity) {
        String sql ="Update Category set TenTheLoai = ? , TrangThai = ? where MaTheLoai = ?";
        JdbcHelper.executeUpdate(sql, entity.getTenTheLoai(), entity.isTrangThai(), entity.getMaTheLoai());
    }

    @Override
    public void delete(String key) {
        String sql ="delete Category  where MaTheLoai = ?";
        JdbcHelper.executeUpdate(sql, key);
    }

    @Override
    public List<Category> selectALL() {
        return selectbySQL("Select * from category");
    }

    @Override
    public Category select_by_id(String key) {
        List<Category> list = selectbySQL("select * from Category where MaTheLoai=?", key);
        if(list.isEmpty()){
        return null;
        }
        
        return list.get(0);
        
    }

    @Override
    public List<Category> selectbySQL(String sql, Object... arg) {
      List<Category> listProduct =new ArrayList<>();
        try {
           
            ResultSet rs = JdbcHelper.executeQuery(sql, arg);
            while (rs.next()) {
                Category category = new Category();
                category.setMaTheLoai(rs.getString("MaTheLoai"));
                category.setTenTheLoai(rs.getString("TenTheLoai"));
                category.setTrangThai(rs.getBoolean("TrangThai"));
                listProduct.add(category);
                
            }
        } catch (SQLException ex) {
            Logger.getLogger(ProductService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return listProduct;
    }
    
}
