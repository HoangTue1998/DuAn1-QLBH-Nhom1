/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAL.Service;

import DAL.Entity.InvoiceDetail;
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
public class InvoiceDetailService implements WareHouse<InvoiceDetail, String>{

    @Override
    public void insert(InvoiceDetail entity) {
        String sql ="insert into  Detailed_Invoice (MaHoaDon, MaSanPhamChiTiet, SoLuong, DonGia, GiamGia, Tong) values (?,?,?,?,?,?)";
        JdbcHelper.executeUpdate(sql, entity.getMaHoaDon(), entity.getMaSanPhamChiTiet(), entity.getSoLuong(), entity.getDonGia(), entity.getGiamGia(), entity.getTong());
    }
    
//      @Override
//    public void insert(Invoice entity) {
//        String sql ="insert into Invoice( MaNhanVien,NgayBan, MaKhachHang, TongTien) values(?,?,?,?)";
//        JdbcHelper.executeUpdate(sql, entity.getMaNhanVien(),entity.getNgayBan(),entity.getMaKhachHang(), entity.getTongTien());
//    }

    @Override
    public void update(InvoiceDetail entity) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void delete(String key) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<InvoiceDetail> selectALL() {
        return selectbySQL("Select * from Detailed_Invoice");
    }

    @Override
    public InvoiceDetail select_by_id(String key) {
        String sql ="Select * from Detailed_Invoice WHERE MaSanPhamChiTiet =?";
        List<InvoiceDetail> list = selectbySQL(sql, key);
        if(list.isEmpty()){
        return null;}
        return list.get(0);
        
    }

    @Override
    public List<InvoiceDetail> selectbySQL(String sql, Object... arg) {
        List<InvoiceDetail> list =new ArrayList<>();
        try {
           
            ResultSet rs = JdbcHelper.executeQuery(sql, arg);
            while (rs.next()) {
                InvoiceDetail invoiceDetail = new InvoiceDetail();
                invoiceDetail.setMaHoaDon(rs.getInt("MaHoaDon"));
                invoiceDetail.setMaSanPhamChiTiet(rs.getString("MaSanPhamChiTiet"));
                invoiceDetail.setSoLuong(rs.getInt("SoLuong"));
                invoiceDetail.setDonGia(rs.getInt("DonGia"));
                invoiceDetail.setGiamGia(rs.getInt("GiamGia"));
                invoiceDetail.setTong(rs.getInt("Tong"));
                
                
                list.add(invoiceDetail);
                
            }
        } catch (SQLException ex) {
            Logger.getLogger(ProductService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }

  
    
    
}
