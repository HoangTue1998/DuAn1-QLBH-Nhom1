/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Business.Sevice;

import DAL.Entity.Color;
import DAL.Service.ColorService;
import Utils.JdbcHelper;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Minh
 */
public class ColorBussinesService {
    ColorService service = new ColorService();
    public List<Color> loadAllColor(){
        List<Color> listColors = new ArrayList<>();
        for(Color x: service.selectALL()){
            if(x.isTrangThai()){
                listColors.add(x);
            }
        }
        return listColors;
    }
    
    public void insertColor(Color e){
        e.setTrangThai(true);
        service.insert(e);
    }
    
    public void updateColor(Color e){
        e.setTrangThai(true);
        service.update(e);
    }
    
    public void softDelete(int id){
        String sql ="update Color set TrangThai = 0 where maMau =?";
        JdbcHelper.executeUpdate(sql, id);
    }
    
}
