/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Business.DTO;

/**
 *
 * @author Minh
 */
public class ProductDTO {
    private String id; 
    private String tenSanPham;
    private String loaiSanPham;
    private String anh;

    public ProductDTO() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTenSanPham() {
        return tenSanPham;
    }

    public void setTenSanPham(String tenSanPham) {
        this.tenSanPham = tenSanPham;
    }

    public String getLoaiSanPham() {
        return loaiSanPham;
    }

    public void setLoaiSanPham(String loaiSanPham) {
        this.loaiSanPham = loaiSanPham;
    }

    public ProductDTO(String id, String tenSanPham, String loaiSanPham, String anh) {
        this.id = id;
        this.tenSanPham = tenSanPham;
        this.loaiSanPham = loaiSanPham;
        this.anh = anh;
    }

    public ProductDTO(String id, String tenSanPham, String loaiSanPham) {
        this.id = id;
        this.tenSanPham = tenSanPham;
        this.loaiSanPham = loaiSanPham;
       
    }
    public String getAnh() {
        return anh;
    }

    public void setAnh(String anh) {
        this.anh = anh;
    }

    
    
    
    
}
