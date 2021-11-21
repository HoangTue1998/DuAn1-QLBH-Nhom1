/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAL.Service;

import DAL.Entity.Product;
import DAL.Entity.Staff;
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
public class StaffService implements WareHouse<Staff, String> {

    @Override
    public void insert(Staff entity) {
        String sql = "insert into Staff(MaNhanVien,TaiKhoan,TenNhanVien,MatKhau,GioiTinh,DiaChi,DienThoai,Email,NgaySinh,VaiTro,TrangThai)"
                + "values(?,?,?,?,?,?,?,?,?,?,?)";
        JdbcHelper.executeUpdate(sql, entity.getMaNhanVien(),
                entity.getTaiKhoan(),
                entity.getTenNhanVien(),
                entity.getMatKhau(),
                entity.isGioiTinh(),
                entity.getDiaChi(),
                entity.getDienThoai(),
                entity.getEmail(),
                entity.getNgaySinh(),
                entity.isVaiTro(),
                entity.isTrangThai());
    }

    @Override
    public void update(Staff entity) {
        String sql = "update Staff set TaiKhoan=?,TenNhanVien=?,MatKhau=?,GioiTinh=?,DiaChi=?,DienThoai=?,Email=?,NgaySinh=?,VaiTro=?,TrangThai=? where MaNhanVien =?";
        JdbcHelper.executeUpdate(sql,
                entity.getTaiKhoan(),
                entity.getTenNhanVien(),
                entity.getMatKhau(),
                entity.isGioiTinh(),
                entity.getDiaChi(),
                entity.getDiaChi(),
                entity.getEmail(),
                entity.getNgaySinh(),
                entity.isVaiTro(),
                entity.isTrangThai(),
                entity.getMaNhanVien()
        );

    }

    @Override
    public void delete(String key) {
        String sql = "delete from Staff where MaNhanVien =?";
        JdbcHelper.executeUpdate(sql, key);

    }

    @Override
    public List<Staff> selectALL() {
        return selectbySQL("Select * from Staff");
    }

    public List<Staff> selectEmail() {
        return selectbySQL("Select Email from Staff where TrangThai = 0");
    }

    @Override
    public Staff select_by_id(String key) {
        String sql = "delete from Staff where MaNhanVien =?";
        List<Staff> list = selectbySQL(sql, key);
        if (list.isEmpty()) {
            return null;
        }
        return list.get(0);

    }

    @Override
    public List<Staff> selectbySQL(String sql, Object... arg) {
        List<Staff> listStaffs = new ArrayList<>();
        try {

            ResultSet rs = JdbcHelper.executeQuery(sql, arg);
            while (rs.next()) {
                Staff staff = new Staff();
                staff.setMaNhanVien(rs.getString("MaNhanVien"));
                staff.setTaiKhoan(rs.getString("TaiKhoan"));
                staff.setMatKhau(rs.getString("MatKhau"));
                staff.setTenNhanVien(rs.getString("TenNhanVien"));
                staff.setGioiTinh(rs.getBoolean("GioiTinh"));
                staff.setDiaChi(rs.getString("DiaChi"));
                staff.setDienThoai(rs.getString("Email"));
                staff.setNgaySinh(rs.getDate("NgaySinh"));
                staff.setVaiTro(rs.getBoolean("VaiTro"));
               // staff.setVaiTro(rs.getBoolean("MaNhanVien"));

                listStaffs.add(staff);

            }
        } catch (SQLException ex) {
            Logger.getLogger(ProductService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return listStaffs;

    }

}
