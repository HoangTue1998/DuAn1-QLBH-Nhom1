/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAL.Entity;

import java.util.Date;

/**
 *
 * @author Minh
 */
public class Staff {
    private String maNhanVien;
    private String taiKhoan;
    private String matKhau;
    private String tenNhanVien;
    private boolean gioiTinh;
    private String diaChi;
    private String dienThoai;
    private String email;
    private Date ngaySinh;
    private boolean vaiTro;
    private boolean trangThai;

    public Staff() {
    }

    public Staff(String maNhanVien, String taiKhoan, String matKhau, String tenNhanVien, boolean gioiTinh, String diaChi, String dienThoai, String email, Date ngaySinh, boolean vaiTro, boolean trangThai) {
        this.maNhanVien = maNhanVien;
        this.taiKhoan = taiKhoan;
        this.matKhau = matKhau;
        this.tenNhanVien = tenNhanVien;
        this.gioiTinh = gioiTinh;
        this.diaChi = diaChi;
        this.dienThoai = dienThoai;
        this.email = email;
        this.ngaySinh = ngaySinh;
        this.vaiTro = vaiTro;
        this.trangThai = trangThai;
    }



  

    public String getMaNhanVien() {
        return maNhanVien;
    }

    public void setMaNhanVien(String maNhanVien) {
        this.maNhanVien = maNhanVien;
    }

    public String getTaiKhoan() {
        return taiKhoan;
    }

    public void setTaiKhoan(String taiKhoan) {
        this.taiKhoan = taiKhoan;
    }

    public String getMatKhau() {
        return matKhau;
    }

    public void setMatKhau(String matKhau) {
        this.matKhau = matKhau;
    }

    public String getTenNhanVien() {
        return tenNhanVien;
    }

    public void setTenNhanVien(String tenNhanVien) {
        this.tenNhanVien = tenNhanVien;
    }

    public boolean isGioiTinh() {
        return gioiTinh;
    }

    public void setGioiTinh(boolean gioiTinh) {
        this.gioiTinh = gioiTinh;
    }

    public String getDiaChi() {
        return diaChi;
    }

    public void setDiaChi(String diaChi) {
        this.diaChi = diaChi;
    }

    public String getDienThoai() {
        return dienThoai;
    }

    public void setDienThoai(String dienThoai) {
        this.dienThoai = dienThoai;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Date getNgaySinh() {
        return ngaySinh;
    }

    public void setNgaySinh(Date ngaySinh) {
        this.ngaySinh = ngaySinh;
    }

    public boolean isVaiTro() {
        return vaiTro;
    }

    public void setVaiTro(boolean vaiTro) {
        this.vaiTro = vaiTro;
    }

    public boolean isTrangThai() {
        return trangThai;
    }

    public void setTrangThai(boolean trangThai) {
        this.trangThai = trangThai;
    }

    @Override
    public String toString() {
        return "Staff{" + "maNhanVien=" + maNhanVien + ", taiKhoan=" + taiKhoan + ", matKhau=" + matKhau + ", tenNhanVien=" + tenNhanVien + ", gioiTinh=" + gioiTinh + ", diaChi=" + diaChi + ", dienThoai=" + dienThoai + ", email=" + email + ", ngaySinh=" + ngaySinh + ", vaiTro=" + vaiTro + ", trangThai=" + trangThai + '}';
    }

    
    
    
}
