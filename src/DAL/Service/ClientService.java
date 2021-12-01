/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAL.Service;

import DAL.Entity.Client;
import Utils.JdbcHelper;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Minh
 */
public class ClientService implements WareHouse<Client, String>{

    @Override
    public void insert(Client entity) {
        String sql ="insert into Client( TenKhachHang, DiaChi, DienThoai) values ( ?,?,?);";
        JdbcHelper.executeUpdate(sql, 
                                        entity.getTenKhachHang(),
                                        entity.getDiaChi(),
                                        entity.getDienThoai());
    }

    @Override
    public void update(Client entity) {
        String sql ="update update Client set MaKhachHang='2', TenKhachHang='2', DiaChi='2', DienThoai='2' where MaKhachHang ='1' set TenKhachHang=?, DiaChi=?, DienThoai=? where MaKhachHang =?";
        JdbcHelper.executeUpdate(sql, entity.getTenKhachHang(),
                                        entity.getDiaChi(),
                                        entity.getDienThoai(),
                                        entity.getTenKhachHang());
    }

    @Override
    public void delete(String key) {
        String sql ="delete from client where MaKhachHang=?";
        JdbcHelper.executeUpdate(sql, key);
        }

    @Override
    public List<Client> selectALL() {
        return selectbySQL("select * from Client");
    }

    @Override
    public Client select_by_id(String key) {
        String sql ="Select * from Client where MaKhachHang=?";
        List<Client> list = selectbySQL(sql, key);
        if(list.isEmpty()){
        return null;
         };
        return list.get(0);
    }

    @Override
    public List<Client> selectbySQL(String sql, Object... arg) {
        List<Client> listClient = new ArrayList<>();
        try {
           
            ResultSet rs = JdbcHelper.executeQuery(sql, arg);
            while (rs.next()) {
                Client client = new Client();
                client.setMaKhachHang(rs.getInt("MaKhachHang"));
//                client.setMaKhachHang(rs.getString("TenKhachHang"));
//                client.setMaKhachHang(rs.getString("DiaChi"));
//                client.setMaKhachHang(rs.getString("DienThoai"));
                listClient.add(client); 
            }
        } catch (SQLException ex) {
            Logger.getLogger(ProductService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return listClient;
    }

  
    
    
}
