/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Ui.Model;

import java.util.Date;

/**
 *
 * @author tiennv
 */
public class DetailInvoiceUI {

    private int MaHoaDon;
    private String MaSanPham;
    private String TenSanPham;
    private int SoLuong;
    private String MaNhanVien;
    private String TenNhanVien;
    private String MaKhachHang;
    private String TenKhachHang;
    private String DiaChi;
    private String SDT;
    private Date NgayBan;
    private float TongTien;
    private int TrangThai;

    private String GhiChu;

    public DetailInvoiceUI() {
    }

    public DetailInvoiceUI(int MaHoaDon, String MaSanPham, String TenSanPham, int SoLuong, String MaNhanVien, String TenNhanVien, String MaKhachHang, String TenKhachHang, Date NgayBan, float TongTien, int TrangThai) {
        this.MaHoaDon = MaHoaDon;
        this.MaSanPham = MaSanPham;
        this.TenSanPham = TenSanPham;
        this.SoLuong = SoLuong;
        this.MaNhanVien = MaNhanVien;
        this.TenNhanVien = TenNhanVien;
        this.MaKhachHang = MaKhachHang;
        this.TenKhachHang = TenKhachHang;
        this.NgayBan = NgayBan;
        this.TongTien = TongTien;
        this.TrangThai = TrangThai;
    }

    public DetailInvoiceUI(int MaHoaDon, String MaSanPham, String TenSanPham, int SoLuong, String MaNhanVien, String TenNhanVien, String MaKhachHang, String TenKhachHang, String DiaChi, String SDT, Date NgayBan, float TongTien, int TrangThai, String GhiChu) {
        this.MaHoaDon = MaHoaDon;
        this.MaSanPham = MaSanPham;
        this.TenSanPham = TenSanPham;
        this.SoLuong = SoLuong;
        this.MaNhanVien = MaNhanVien;
        this.TenNhanVien = TenNhanVien;
        this.MaKhachHang = MaKhachHang;
        this.TenKhachHang = TenKhachHang;
        this.DiaChi = DiaChi;
        this.SDT = SDT;
        this.NgayBan = NgayBan;
        this.TongTien = TongTien;
        this.TrangThai = TrangThai;
        this.GhiChu = GhiChu;
    }

    public int getMaHoaDon() {
        return MaHoaDon;
    }

    public void setMaHoaDon(int MaHoaDon) {
        this.MaHoaDon = MaHoaDon;
    }

    public String getMaSanPham() {
        return MaSanPham;
    }

    public void setMaSanPham(String MaSanPham) {
        this.MaSanPham = MaSanPham;
    }

    public String getTenSanPham() {
        return TenSanPham;
    }

    public void setTenSanPham(String TenSanPham) {
        this.TenSanPham = TenSanPham;
    }

    public int getSoLuong() {
        return SoLuong;
    }

    public void setSoLuong(int SoLuong) {
        this.SoLuong = SoLuong;
    }

    public String getMaNhanVien() {
        return MaNhanVien;
    }

    public void setMaNhanVien(String MaNhanVien) {
        this.MaNhanVien = MaNhanVien;
    }

    public String getTenNhanVien() {
        return TenNhanVien;
    }

    public void setTenNhanVien(String TenNhanVien) {
        this.TenNhanVien = TenNhanVien;
    }

    public String getMaKhachHang() {
        return MaKhachHang;
    }

    public void setMaKhachHang(String MaKhachHang) {
        this.MaKhachHang = MaKhachHang;
    }

    public String getTenKhachHang() {
        return TenKhachHang;
    }

    public void setTenKhachHang(String TenKhachHang) {
        this.TenKhachHang = TenKhachHang;
    }

    public String getDiaChi() {
        return DiaChi;
    }

    public void setDiaChi(String DiaChi) {
        this.DiaChi = DiaChi;
    }

    public String getSDT() {
        return SDT;
    }

    public void setSDT(String SDT) {
        this.SDT = SDT;
    }

    public Date getNgayBan() {
        return NgayBan;
    }

    public void setNgayBan(Date NgayBan) {
        this.NgayBan = NgayBan;
    }

    public float getTongTien() {
        return TongTien;
    }

    public void setTongTien(float TongTien) {
        this.TongTien = TongTien;
    }

    public int getTrangThai() {
        return TrangThai;
    }

    public void setTrangThai(int TrangThai) {
        this.TrangThai = TrangThai;
    }

    public String getGhiChu() {
        return GhiChu;
    }

    public void setGhiChu(String GhiChu) {
        this.GhiChu = GhiChu;
    }

   
    
}
