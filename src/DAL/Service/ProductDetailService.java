/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAL.Service;

import DAL.Entity.ProductDetail;
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
public class ProductDetailService implements WareHouse<ProductDetail, String>{

    @Override
    public void insert(ProductDetail entity) {
        String sql ="insert into Product_Detail(MaSanPhamChiTiet, TenSanPham, SoLuong, GiaNhap,GiaBan, MaSanPham, MaTheLoai, maSize, maMau, TrangThai) values (?,?,?,?,?,?,?,?,?,?)";
        JdbcHelper.executeUpdate(sql, entity.getMaSanPhamChiTiet(), entity.getTenSanPham(), entity.getSoLuong(), entity.getGiaNhap(), entity.getGiaBan(), entity.getMaSanPham(), entity.getMaTheLoai(), 
                entity.getMaSize(), entity.getMaMau(), entity.isTrangThai());
    }

    @Override
    public void update(ProductDetail entity) {
        String sql ="update Product_Detail set TenSanPham =?, SoLuong=?, GiaNhap=?, GiaBan=?, MaSanPham=?, MaTheLoai=?, maSize=?, maMau=?, TrangThai=? where MaSanPhamChiTiet = ?";
        JdbcHelper.executeUpdate(sql, entity.getTenSanPham(), entity.getSoLuong(), entity.getGiaNhap(), entity.getGiaBan(),  entity.getMaSanPham(), entity.getMaTheLoai(), 
                entity.getMaSize(), entity.getMaMau(), entity.isTrangThai(), entity.getMaSanPhamChiTiet());
    }

    @Override
    public void delete(String key) {
        String sql="delete from Product_Detail where MaSanPhamChiTiet =?";
        JdbcHelper.executeUpdate(sql, key);
     }

    @Override
    public List<ProductDetail> selectALL() {
        return selectbySQL("select * from Product_Detail");
    }

    @Override
    public ProductDetail select_by_id(String key) {
        String sql ="select * from Product_Detail where MaSanPhamChiTiet =?";
        List<ProductDetail> list = selectbySQL(sql, key);
        if(list.isEmpty()){
        return null;}
        return list.get(0);
        
    }

    @Override
    public List<ProductDetail> selectbySQL(String sql, Object... arg) {
         List<ProductDetail> list =new ArrayList<>();
        try {
           
            ResultSet rs = JdbcHelper.executeQuery(sql, arg);
            while (rs.next()) {
                ProductDetail productDetail = new ProductDetail();
                    productDetail.setMaSanPhamChiTiet(rs.getString("MaSanPhamChiTiet"));
                    productDetail.setTenSanPham(rs.getString("TenSanPham"));
                    productDetail.setSoLuong(rs.getInt("SoLuong"));
                    productDetail.setGiaNhap(rs.getInt("GiaNhap"));
                    productDetail.setGiaBan(rs.getInt("GiaBan"));
                    productDetail.setMaSanPham(rs.getString("MaSanPham"));
                    productDetail.setMaTheLoai(rs.getString("MaTheLoai"));
                    productDetail.setMaSize(rs.getInt("maSize"));
                    productDetail.setMaMau(rs.getInt("maMau"));
                    productDetail.setTrangThai(rs.getBoolean("TrangThai"));
                    
                    list.add(productDetail);
                    
               
            }
        } catch (SQLException ex) {
            Logger.getLogger(ProductService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }
    
}
