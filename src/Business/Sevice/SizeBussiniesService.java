/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Business.Sevice;

import DAL.Entity.Size;
import DAL.Service.SizeService;
import Utils.JdbcHelper;
import java.util.List;

/**
 *
 * @author Minh
 */
public class SizeBussiniesService {
    SizeService service = new SizeService();
    public List<Size> getAllSize() {
        
        List<Size> list = service.selectALL();
        for(Size x: list){
            if(x.isTrangThai() ==false){
                list.remove(x);
            }
        }
        return list;
    }
    
    public void insertSize(Size s){
        s.setTrangThai(true);
        service.insert(s);
    }
    
    public void updateSize(Size s){
        s.setTrangThai(true);
        service.update(s);
    }
    
    public void softDelete(int id){
        String sql  = "update size set trangthai = 0 where maSize = ?";
        JdbcHelper.executeUpdate(sql, id);
        
    }
    
}
