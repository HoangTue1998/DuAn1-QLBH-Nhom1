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
public class ProductDetailUI {

    private String maLoaiSp;
    private String tenLoai;
    private String maSP;
    private String tenSP;
    private String maSPCT;
    private int maMau;
    private String mau;
    private int maSize;
    private String size;
    private int soLuong;
    private double giaBan;
    private int soLuongBan;
    private int maGiamGia;
    private float phanTram;
    private Date ngayBatDau;
    private Date ngayKetThuc;

    public ProductDetailUI() {
    }

    public ProductDetailUI(String maLoaiSp, String tenLoai, String maSP, String tenSP, String maSPCT, int maMau, String mau, int maSize, String size, int soLuong, double giaBan, int soLuongBan) {
        this.maLoaiSp = maLoaiSp;
        this.tenLoai = tenLoai;
        this.maSP = maSP;
        this.tenSP = tenSP;
        this.maSPCT = maSPCT;
        this.maMau = maMau;
        this.mau = mau;
        this.maSize = maSize;
        this.size = size;
        this.soLuong = soLuong;
        this.giaBan = giaBan;
        this.soLuongBan = soLuongBan;
    }

    public ProductDetailUI(String maLoaiSp, String tenLoai, String maSP, String tenSP, String maSPCT, int maMau, String mau, int maSize, String size, int soLuong, double giaBan, int soLuongBan, int maGiamGia, float phanTram, Date ngayBatDau, Date ngayKetThuc) {
        this.maLoaiSp = maLoaiSp;
        this.tenLoai = tenLoai;
        this.maSP = maSP;
        this.tenSP = tenSP;
        this.maSPCT = maSPCT;
        this.maMau = maMau;
        this.mau = mau;
        this.maSize = maSize;
        this.size = size;
        this.soLuong = soLuong;
        this.giaBan = giaBan;
        this.soLuongBan = soLuongBan;
        this.maGiamGia = maGiamGia;
        this.phanTram = phanTram;
        this.ngayBatDau = ngayBatDau;
        this.ngayKetThuc = ngayKetThuc;
    }

    public int getMaGiamGia() {
        return maGiamGia;
    }

    public void setMaGiamGia(int maGiamGia) {
        this.maGiamGia = maGiamGia;
    }

    public float getPhanTram() {
        return phanTram;
    }

    public void setPhanTram(float phanTram) {
        this.phanTram = phanTram;
    }

    public Date getNgayBatDau() {
        return ngayBatDau;
    }

    public void setNgayBatDau(Date ngayBatDau) {
        this.ngayBatDau = ngayBatDau;
    }

    public Date getNgayKetThuc() {
        return ngayKetThuc;
    }

    public void setNgayKetThuc(Date ngayKetThuc) {
        this.ngayKetThuc = ngayKetThuc;
    }

    public int getSoLuongBan() {
        return soLuongBan;
    }

    public void setSoLuongBan(int soLuongBan) {
        this.soLuongBan = soLuongBan;
    }


    public String getMaLoaiSp() {
        return maLoaiSp;
    }

    public void setMaLoaiSp(String maLoaiSp) {
        this.maLoaiSp = maLoaiSp;
    }

    public String getTenLoai() {
        return tenLoai;
    }

    public void setTenLoai(String tenLoai) {
        this.tenLoai = tenLoai;
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

    public String getMaSPCT() {
        return maSPCT;
    }

    public void setMaSPCT(String maSPCT) {
        this.maSPCT = maSPCT;
    }

    public int getMaMau() {
        return maMau;
    }

    public void setMaMau(int maMau) {
        this.maMau = maMau;
    }

    public String getMau() {
        return mau;
    }

    public void setMau(String mau) {
        this.mau = mau;
    }

    public int getMaSize() {
        return maSize;
    }

    public void setMaSize(int maSize) {
        this.maSize = maSize;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public int getSoLuong() {
        return soLuong;
    }

    public void setSoLuong(int soLuong) {
        this.soLuong = soLuong;
    }

    public double getGiaBan() {
        return giaBan;
    }

    public void setGiaBan(double giaBan) {
        this.giaBan = giaBan;
    }

    @Override
    public String toString() {
        return "ProductDetailUI{" + "maLoaiSp=" + maLoaiSp + ", tenLoai=" + tenLoai + ", maSP=" + maSP + ", tenSP=" + tenSP + ", maSPCT=" + maSPCT + ", maMau=" + maMau + ", mau=" + mau + ", maSize=" + maSize + ", size=" + size + ", soLuong=" + soLuong + ", giaBan=" + giaBan + '}';
    }

    
    

}
