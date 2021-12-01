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
public class InvoDtailUI {

    private int MaHoaDon;
    private String MaTheLoai;
    private String TenTheLoai;
    private String maSP;
    private String tenSP;
    private int SoLuong;
    private String GhiChu;
    private String MaNhanVien;
    private String TenNhanVien;
    private String MaKhachHang;
    private String TenKhachHang;
    private String DiaChi;
    private String DienThoai;
    private Date NgayBan;
    private float TongTien;
    private int TrangThai;
    private String MaSanPhamChiTiet;
    private float DonGia;
    private int maMau;
    private String temMau;
    private int maSize;
    private String TenSize;

//     select a.MaHoaDon,g.MaTheLoai, g.TenTheLoai, d.MaSanPham, d.TenSanPham, c.SoLuong, a.GhiChu,
//		 e.MaNhanVien,e.TenNhanVien,f.MaKhachHang, f.TenKhachHang,f.DiaChi,f.DienThoai,a.NgayBan,a.TongTien,
//		 a.TrangThai, c.MaSanPhamChiTiet, DonGia, c.maMau, h.temMau, c.maSize, i.TenSize
//         from Invoice a inner join Detailed_Invoice b on a.MaHoaDon = b.MaHoaDon
//						inner join Product_Detail c on c.MaSanPhamChiTiet = b.MaSanPhamChiTiet 
//						inner join Product d on d.MaSanPham = c.MaSanPham
//						inner join Staff e on e.MaNhanVien = a.MaNhanVien
//						inner join Client f on f.MaKhachHang = a.MaKhachHang
//						inner join Category g on g.MaTheLoai = d.MaTheLoai
//						inner join Color h on h.maMau = c.maMau
//						inner join Size i on i.maSize = c.maSize
//						where a.MaHoaDon = 31

    public InvoDtailUI() {
    }

    public InvoDtailUI(int MaHoaDon, String MaTheLoai, String TenTheLoai, String maSP, String tenSP, int SoLuong, String GhiChu, String MaNhanVien, String TenNhanVien, String MaKhachHang, String TenKhachHang, String DiaChi, String DienThoai, Date NgayBan, float TongTien, int TrangThai, String MaSanPhamChiTiet, float DonGia, int maMau, String temMau, int maSize, String TenSize) {
        this.MaHoaDon = MaHoaDon;
        this.MaTheLoai = MaTheLoai;
        this.TenTheLoai = TenTheLoai;
        this.maSP = maSP;
        this.tenSP = tenSP;
        this.SoLuong = SoLuong;
        this.GhiChu = GhiChu;
        this.MaNhanVien = MaNhanVien;
        this.TenNhanVien = TenNhanVien;
        this.MaKhachHang = MaKhachHang;
        this.TenKhachHang = TenKhachHang;
        this.DiaChi = DiaChi;
        this.DienThoai = DienThoai;
        this.NgayBan = NgayBan;
        this.TongTien = TongTien;
        this.TrangThai = TrangThai;
        this.MaSanPhamChiTiet = MaSanPhamChiTiet;
        this.DonGia = DonGia;
        this.maMau = maMau;
        this.temMau = temMau;
        this.maSize = maSize;
        this.TenSize = TenSize;
    }

    public int getMaHoaDon() {
        return MaHoaDon;
    }

    public void setMaHoaDon(int MaHoaDon) {
        this.MaHoaDon = MaHoaDon;
    }

    public String getMaTheLoai() {
        return MaTheLoai;
    }

    public void setMaTheLoai(String MaTheLoai) {
        this.MaTheLoai = MaTheLoai;
    }

    public String getTenTheLoai() {
        return TenTheLoai;
    }

    public void setTenTheLoai(String TenTheLoai) {
        this.TenTheLoai = TenTheLoai;
    }

    public String getMaSP() {
        return maSP;
    }

    public void setMaSP(String maSP) {
        this.maSP = maSP;
    }

    public String getTenSP() {
        return tenSP;
    }

    public void setTenSP(String tenSP) {
        this.tenSP = tenSP;
    }

    public int getSoLuong() {
        return SoLuong;
    }

    public void setSoLuong(int SoLuong) {
        this.SoLuong = SoLuong;
    }

    public String getGhiChu() {
        return GhiChu;
    }

    public void setGhiChu(String GhiChu) {
        this.GhiChu = GhiChu;
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

    public String getDienThoai() {
        return DienThoai;
    }

    public void setDienThoai(String DienThoai) {
        this.DienThoai = DienThoai;
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

    public String getMaSanPhamChiTiet() {
        return MaSanPhamChiTiet;
    }

    public void setMaSanPhamChiTiet(String MaSanPhamChiTiet) {
        this.MaSanPhamChiTiet = MaSanPhamChiTiet;
    }

    public float getDonGia() {
        return DonGia;
    }

    public void setDonGia(float DonGia) {
        this.DonGia = DonGia;
    }

    public int getMaMau() {
        return maMau;
    }

    public void setMaMau(int maMau) {
        this.maMau = maMau;
    }

    public String getTemMau() {
        return temMau;
    }

    public void setTemMau(String temMau) {
        this.temMau = temMau;
    }

    public int getMaSize() {
        return maSize;
    }

    public void setMaSize(int maSize) {
        this.maSize = maSize;
    }

    public String getTenSize() {
        return TenSize;
    }

    public void setTenSize(String TenSize) {
        this.TenSize = TenSize;
    }

   
    
    
}
