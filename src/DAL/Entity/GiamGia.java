/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAL.Entity;

import java.util.Date;

/**
 *
 * @author tiennv
 */
public class GiamGia {
    
   private int  maGiamGia;
   private float phanTram;
   private Date ngayBatDau;
   private Date ngayKetthuc;
   private int trangThai;

    public GiamGia() {
    }

    public GiamGia(int maGiamGia, float phanTram, Date ngayBatDau, Date ngayKetthuc, int trangThai) {
        this.maGiamGia = maGiamGia;
        this.phanTram = phanTram;
        this.ngayBatDau = ngayBatDau;
        this.ngayKetthuc = ngayKetthuc;
        this.trangThai = trangThai;
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

    public Date getNgayKetthuc() {
        return ngayKetthuc;
    }

    public void setNgayKetthuc(Date ngayKetthuc) {
        this.ngayKetthuc = ngayKetthuc;
    }

    public int getTrangThai() {
        return trangThai;
    }

    public void setTrangThai(int trangThai) {
        this.trangThai = trangThai;
    }
    
    

}
