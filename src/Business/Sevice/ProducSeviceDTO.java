/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Business.Sevice;

import Business.DTO.ProducDetailDTO;
import Business.DTO.ProductDTO;
import DAL.Entity.Category;
import DAL.Entity.Color;
import DAL.Entity.Product;
import DAL.Entity.ProductDetail;
import DAL.Entity.Size;

import java.util.ArrayList;
import java.util.List;
import DAL.Service.ProductService;
import DAL.Service.CategoryService;
import DAL.Service.ColorService;
import DAL.Service.ProductDetailService;
import DAL.Service.SizeService;
import Utils.JdbcHelper;

/**
 *
 * @author Hoang Tue
 */
public class ProducSeviceDTO {

   ProductService service = new ProductService();
    ProductDetailService productDetailService = new ProductDetailService();
    CategoryService categoryService = new CategoryService();
    SizeService sizeService = new SizeService();
    ColorService colorService = new ColorService();
    
    
    public List<ProductDTO> getAll() {
        List<Product> listProduct = service.selectALL();
        List<ProductDTO> lisProDTO = new ArrayList<>();
        
        for (Product x : listProduct) {          
            if(x.getTrangThai() == 1){
            ProductDTO proDTO = new ProductDTO();
            proDTO.setId(x.getMaSanPham());
            proDTO.setTenSanPham(x.getTenSanPham());
            proDTO.setLoaiSanPham(findCategory(x.getMaTheLoai()).getTenTheLoai());
            lisProDTO.add(proDTO);
            }
            
        }
        return lisProDTO;
    }
    
    public Category findCategory(String id){
        Category category = categoryService.select_by_id(id);
        System.out.print(category.getMaTheLoai());
        System.out.print(category.getTenTheLoai());
        return category;
                
    }
    
    public List<Category> loadCategery(){
        List<Category> list = categoryService.selectALL();
        List<Category> listNewList= new ArrayList<Category>();
       for(Category x: list){
           if(x.isTrangThai()== true){
               listNewList.add(x);
           }
       }
       return listNewList;
    }
    
    public void createProduct(Product p){
        service.insert(p);
    }
    
    public void UpdateProduct(Product p){
        service.update(p);
    }
    
    public void deleteProduct(String id){
        Product p = findById(id);
        p.setTrangThai(0);
        service.update(p);
        
    }
    
    public Product findById(String id){
        return service.select_by_id(id);
    }
    
    public List<ProducDetailDTO> getProductDetai(String idProduct){
        List<ProductDetail> list1 = productDetailService.selectbySQL("select * from Product_Detail where MaSanPham =?", idProduct);
        List<ProducDetailDTO> lis2 = new ArrayList<>();
        for(ProductDetail x: list1){
            if(x.isTrangThai()==true){
            ProducDetailDTO e = new ProducDetailDTO();
            e.setSize(findSize(x.getMaSize()).getTenSize());
            e.setMau(findColor(x.getMaMau()).getTenMau());
            e.setSoLuong(x.getSoLuong());
            e.setGiaNhap(x.getGiaNhap());
            e.setGiaBan(x.getGiaBan());
            e.setMaSPCT(x.getMaSanPhamChiTiet());
            System.out.print(e.getGiaBan());
            lis2.add(e);
        }
        }
        return lis2;
        
    }
    
    public Size findSize(int id){
        return sizeService.select_by_id(id);
    }
    
    public Color findColor(int id){
        return colorService.select_by_id(id);
    }
    
    public List<Size> loadSize(){
        return sizeService.selectALL();
    }
    
    public List<Color> loadColor(){
        return colorService.selectALL();
    }
    
    public void inSertProductDetail(ProductDetail p){
        
        productDetailService.insert(p);
        
    }
    
    public void updateProductDetail(ProductDetail p){
        
        productDetailService.update(p);
       
    }
    
    public void deleteSoft(String id){
        String sql  = "	update product set trangThai = 0 where MaSanPham = ?";
        JdbcHelper.executeUpdate(sql, id);  
    }
    
    public List<Product> listBin(){
        String sql =  "Select * from product where trangThai = 0";
        return service.selectbySQL(sql);
    }
    
    public void restoreProduct(String id){
         String sql  = "update product set trangThai = 1 where MaSanPham = ?";
        JdbcHelper.executeUpdate(sql, id);  
    }  
    
    public void deleteSoftProductDetail(String id){
               String sql  = "update Product_Detail set trangThai = 0 where MaSanPhamChiTiet = ?";
                       JdbcHelper.executeUpdate(sql, id);  
    }
    public List<Product> finbyName(String name ){
        String sql = "select * from Product where TenSanPham like concat('%',?,'%') and trangThai =1";
        return service.selectbySQL(sql, name);
    }

}
