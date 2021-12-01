/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Business.DTO;

/**
 *
 * @author Hoang Tue
 */
public class CategoryDTO {

    private String maTheloai;
    private String tenTheloai;

    public CategoryDTO() {
    }

    public CategoryDTO(String maTheloai, String tenTheloai) {
        this.maTheloai = maTheloai;
        this.tenTheloai = tenTheloai;
    }

    public String getMaTheloai() {
        return maTheloai;
    }

    public void setMaTheloai(String maTheloai) {
        this.maTheloai = maTheloai;
    }

    public String getTenTheloai() {
        return tenTheloai;
    }

    public void setTenTheloai(String tenTheloai) {
        this.tenTheloai = tenTheloai;
    }

    



    @Override
    public String toString() {
        return "CategoryDTO{" + "tenTheloai=" + tenTheloai + '}';
    }

}
