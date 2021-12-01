/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAL.Entity;

/**
 *
 * @author Minh
 */
public class Product {
    private String maSanPham;
    private String tenSanPham;
    private String maTheLoai;
    private String anh;
    private String ghiChu;
    private int trangThai;
    private boolean isDelete;
    private int maGiamGia;

    public int getMaGiamGia() {
        return maGiamGia;
    }

    public void setMaGiamGia(int maGiamGia) {
        this.maGiamGia = maGiamGia;
    }

    public Product(String maSanPham, String tenSanPham, String maTheLoai, String anh, String ghiChu, int trangThai, boolean isDelete, int maGiamGia) {
        this.maSanPham = maSanPham;
        this.tenSanPham = tenSanPham;
        this.maTheLoai = maTheLoai;
        this.anh = anh;
        this.ghiChu = ghiChu;
        this.trangThai = trangThai;
        this.isDelete = isDelete;
        this.maGiamGia = maGiamGia;
    }

    public String getMaSanPham() {
        return maSanPham;
    }

    public void setMaSanPham(String maSanPham) {
        this.maSanPham = maSanPham;
    }

    public String getTenSanPham() {
        return tenSanPham;
    }

    public void setTenSanPham(String tenSanPham) {
        this.tenSanPham = tenSanPham;
    }

    public String getMaTheLoai() {
        return maTheLoai;
    }

    public void setMaTheLoai(String maTheLoai) {
        this.maTheLoai = maTheLoai;
    }

    public String getAnh() {
        return anh;
    }

    public void setAnh(String anh) {
        this.anh = anh;
    }

    public String getGhiChu() {
        return ghiChu;
    }

    public void setGhiChu(String ghiChu) {
        this.ghiChu = ghiChu;
    }

    public int getTrangThai() {
        return trangThai;
    }

    public void setTrangThai(int trangThai) {
        this.trangThai = trangThai;
    }

    public boolean isIsDelete() {
        return isDelete;
    }

    public void setIsDelete(boolean isDelete) {
        this.isDelete = isDelete;
    }
    
    

    public Product() {
    }

    public Product(String maSanPham, String tenSanPham, String maTheLoai, String anh, String ghiChu, int trangThai, boolean isDelete) {
        this.maSanPham = maSanPham;
        this.tenSanPham = tenSanPham;
        this.maTheLoai = maTheLoai;
        this.anh = anh;
        this.ghiChu = ghiChu;
        this.trangThai = trangThai;
        this.isDelete = isDelete;
    }

    @Override
    public String toString() {
        return "Product{" + "maSanPham=" + maSanPham + ", tenSanPham=" + tenSanPham + ", maTheLoai=" + maTheLoai + ", anh=" + anh + ", ghiChu=" + ghiChu + ", trangThai=" + trangThai + ", isDelete=" + isDelete + '}';
    }
    
    
    
    
    
    
}
