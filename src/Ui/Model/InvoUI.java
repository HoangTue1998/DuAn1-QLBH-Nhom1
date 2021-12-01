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
public class InvoUI {
    
    private int maHoaDon;
    private String maNhanVien;
    private Date ngayBan;
    private String MaKhachHang;

    public InvoUI() {
    }

    public InvoUI(int maHoaDon, String maNhanVien, Date ngayBan, String MaKhachHang) {
        this.maHoaDon = maHoaDon;
        this.maNhanVien = maNhanVien;
        this.ngayBan = ngayBan;
        this.MaKhachHang = MaKhachHang;
    }

    public int getMaHoaDon() {
        return maHoaDon;
    }

    public void setMaHoaDon(int maHoaDon) {
        this.maHoaDon = maHoaDon;
    }

    public String getMaNhanVien() {
        return maNhanVien;
    }

    public void setMaNhanVien(String maNhanVien) {
        this.maNhanVien = maNhanVien;
    }

    public Date getNgayBan() {
        return ngayBan;
    }

    public void setNgayBan(Date ngayBan) {
        this.ngayBan = ngayBan;
    }

    public String getMaKhachHang() {
        return MaKhachHang;
    }

    public void setMaKhachHang(String MaKhachHang) {
        this.MaKhachHang = MaKhachHang;
    }
    
}
