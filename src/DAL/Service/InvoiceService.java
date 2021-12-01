/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAL.Service;

import DAL.Entity.Invoice;
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
public class InvoiceService implements WareHouse<Invoice, Integer>{

    @Override
    public void insert(Invoice entity) {
        String sql ="insert into Invoice( MaNhanVien,NgayBan, MaKhachHang, TongTien) values(?,?,?,?)";
        JdbcHelper.executeUpdate(sql, entity.getMaNhanVien(),entity.getNgayBan(),entity.getMaKhachHang(), entity.getTongTien());
    }

    @Override
    public void update(Invoice entity) {
        String sql ="update  Invoice  set MaNhanVien =? ,NgayBan=?, MaKhachHang =?, TongTien=? where MaHoaDon =?";
                JdbcHelper.executeUpdate(sql, entity.getMaNhanVien(),entity.getNgayBan(),entity.getMaKhachHang(), entity.getTongTien(),entity.getMaHoaDon());

    }

    @Override
    public void delete(Integer key) {
        String sql ="delete from Invoice where  MaHoaDon =?";
        JdbcHelper.executeUpdate(sql, key);
        
        
    }

    @Override
    public List<Invoice> selectALL() {
        return selectbySQL("Select * from invoice");
    }

    @Override
    public Invoice select_by_id(Integer key) {
          String sql = "Select * from invoice where MaHoaDon=?";
          List<Invoice> list = selectbySQL(sql, key);
          if(list.isEmpty()){
          return null;}
          return list.get(0);
          
    }

    @Override
    public List<Invoice> selectbySQL(String sql, Object... arg) {
        List<Invoice> listInvoices =new ArrayList<>();
        try {
           
            ResultSet rs = JdbcHelper.executeQuery(sql, arg);
            while (rs.next()) {
                Invoice invoice = new Invoice();
                invoice.setMaHoaDon(rs.getInt("MaHoaDon"));
                invoice.setMaNhanVien(rs.getString("MaNhanVien"));
                invoice.setNgayBan(rs.getDate("NgayBan"));
                invoice.setMaKhachHang(rs.getString("MaHoaDon"));
                invoice.setTongTien(rs.getInt("TongTien"));
                listInvoices.add(invoice);
                
                
            }
        } catch (SQLException ex) {
            Logger.getLogger(ProductService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return listInvoices;
    }

  
}
