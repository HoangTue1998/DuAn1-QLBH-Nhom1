/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Business.Sevice;

import DAL.Entity.Category;
import DAL.Service.CategoryService;
import Utils.JdbcHelper;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Minh
 */
public class CategoryBussinesService {
    public CategoryService serviceCategory  = new CategoryService();
    
    public List<Category> getlistCategory(){
        List<Category> list  = new ArrayList<Category>();
       for(Category x:  serviceCategory.selectALL()){
           if(x.isTrangThai()){
               list.add(x);
           }
       }
       return list;
    } ;
   
    public void creatNewService(Category e){
        e.setTrangThai(true);
        serviceCategory.insert(e);
    }
    
    public void updateNewService(Category e){
        serviceCategory.update(e);
    }
    
    public void deleteSoft(String id){
        String sql = "Update category set trangThai = 0 where MaTheLoai =?";
        JdbcHelper.executeUpdate(sql, id);
    }
}
