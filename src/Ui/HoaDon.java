/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package Ui;

import DAL.Entity.Client;
import DAL.Entity.GiamGia;
import DAL.Entity.InvoiceDetail;
import DAL.Service.ClientService;
import DAL.Service.InvoiceDetailService;
import DAL.Service.InvoiceService;
import Ui.Model.ClientUIMoDel;
import Ui.Model.InvoDtailUI;

import Ui.Model.InvoUI;
import Ui.Model.InvoiceUI;
import Ui.Model.ProductDetailUI;
import Utils.DateHelper;
import java.sql.Connection;

import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
//clearSelection()  bỏ chọn hàng
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author pc
 */
public class HoaDon extends javax.swing.JFrame {

    /**
     * Creates new form adMin
     */
    private static String driver = "com.microsoft.sqlserver.jdbc.SQLServerDriver";
    private static String dburl = "jdbc:sqlserver://DESKTOP-1T2BB3E\\SQLEXPRESS:1433;databaseName=QLBH1";
    private static String username = "sa";
    private static String password = "12345";
    DefaultTableModel model;
    List<ProductDetailUI> list;
    int index1;
    DefaultTableModel model2;
    List<ProductDetailUI> listTam = new ArrayList<>();
    InvoiceDetailService srInvD;

    ClientService srClientService;
    InvoiceService srInv;
    DateHelper DATE_FORMATER;
    public List<ClientUIMoDel> list1;
    public List<InvoUI> list2;
    int idValue;
    List<InvoiceUI> list3;
    DefaultTableModel model3;
    int index2;
    int index3;
    List<InvoDtailUI> list4;
    int a = 1;
    int idValue2;
    float tong = 0;
    public static String taiKhoan, maNV, tenNV, passNV;

    public HoaDon(String taiKhoan, String maNV, String tenNV, String passNV) {
        initComponents();
        setLocationRelativeTo(null);
        this.taiKhoan = taiKhoan;
        this.maNV = maNV;
        this.tenNV = tenNV;
        this.passNV = passNV;
        jLabel1.setText(tenNV);
        srInvD = new InvoiceDetailService();
        srInv = new InvoiceService();
        srClientService = new ClientService();
        model = (DefaultTableModel) jTable1.getModel();
        model2 = (DefaultTableModel) jTable2.getModel();
        model3 = (DefaultTableModel) jTable3.getModel();
        model2.setRowCount(0);
        
        try {
            list = getListSP();
            list3 = getList3();
            lbGiamGia.setText(String.valueOf(list.get(1).getPhanTram()) + " %");
//        model.setColumnIdentifiers(new Object[]{
//            "STT", "ID", "Full Name", "Date of birth", "Address", "Phone number", "Email", "AVG"
//        });
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(HoaDon.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(HoaDon.class.getName()).log(Level.SEVERE, null, ex);
        }
        showTable();
        showTable2();

    }

//    public List<ProductDetailUI> getListSP() throws ClassNotFoundException, SQLException {
//        Class.forName(driver);
//        Connection conn = DriverManager.getConnection(dburl, username, password);
//        List<ProductDetailUI> list = new ArrayList<>();
//        String sql = "	select e.MaTheLoai, TenTheLoai, a.MaSanPham, a.TenSanPham, MaSanPhamChiTiet, c.maMau, c.temMau, d.maSize, d.TenSize, SoLuong, GiaBan\n"
//                + "			from Product a inner join Product_Detail b on a.MaSanPham = b.MaSanPham\n"
//                + "						   inner join Color c on c.maMau = b.maMau\n"
//                + "						   inner join Size d on d.maSize = b.maSize\n"
//                + "						   inner join Category e on e.MaTheLoai = b.MaTheLoai";
//        try {
//            PreparedStatement ps = conn.prepareStatement(sql);
//            ResultSet rs = ps.executeQuery();
//            while (rs.next()) {
//                ProductDetailUI sp = new ProductDetailUI();
//                sp.setMaLoaiSp(rs.getString("MaTheLoai"));
//                sp.setTenLoai(rs.getString("TenTheLoai"));
//                sp.setMaSP(rs.getString("MaSanPham"));
//                sp.setTenSP(rs.getString("TenSanPham"));
//                sp.setMaSPCT(rs.getString("MaSanPhamChiTiet"));
//                sp.setMaMau(rs.getInt("maMau"));
//                sp.setMau(rs.getString("temMau"));
//                sp.setMaSize(rs.getInt("maSize"));
//                sp.setSize(rs.getString("TenSize"));
//                sp.setSoLuong(rs.getInt("SoLuong"));
//                sp.setGiaBan(rs.getDouble("GiaBan"));
//
//                list.add(sp);
//
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        return list;
//    }
    public List<ProductDetailUI> getListSP() throws ClassNotFoundException, SQLException {
        Class.forName(driver);
        Connection conn = DriverManager.getConnection(dburl, username, password);
        List<ProductDetailUI> list = new ArrayList<>();
        String sql = "	select e.MaTheLoai, TenTheLoai, a.MaSanPham, a.TenSanPham,"
                + " MaSanPhamChiTiet, c.maMau, c.temMau, d.maSize, d.TenSize, SoLuong, GiaBan,"
                + " a.MaGiamGia, PhanTram,NgayBatDau,NgayKetThuc\n"
                + "			from Product a inner join Product_Detail b on a.MaSanPham = b.MaSanPham\n"
                + "						   inner join Color c on c.maMau = b.maMau\n"
                + "						   inner join Size d on d.maSize = b.maSize\n"
                + "						   inner join Category e on e.MaTheLoai = b.MaTheLoai\n"
                + "						   inner join GiamGia f on f.MaGiamGia = a.MaGiamGia";
        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                ProductDetailUI sp = new ProductDetailUI();
                sp.setMaLoaiSp(rs.getString("MaTheLoai"));
                sp.setTenLoai(rs.getString("TenTheLoai"));
                sp.setMaSP(rs.getString("MaSanPham"));
                sp.setTenSP(rs.getString("TenSanPham"));
                sp.setMaSPCT(rs.getString("MaSanPhamChiTiet"));
                sp.setMaMau(rs.getInt("maMau"));
                sp.setMau(rs.getString("temMau"));
                sp.setMaSize(rs.getInt("maSize"));
                sp.setSize(rs.getString("TenSize"));
                sp.setSoLuong(rs.getInt("SoLuong"));
                sp.setGiaBan(rs.getDouble("GiaBan"));
                sp.setMaGiamGia(rs.getByte("MaGiamGia"));
                sp.setPhanTram(rs.getFloat("PhanTram"));
                sp.setNgayBatDau(rs.getDate("NgayBatDau"));
                sp.setNgayKetThuc(rs.getDate("NgayKetThuc"));

                list.add(sp);

            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    public void showTable() {
        model.setRowCount(0);
        for (ProductDetailUI sp : list) {

            model.addRow(new Object[]{
                sp.getMaSP(), sp.getTenSP(), sp.getTenLoai(), sp.getSize(), sp.getMau(), sp.getGiaBan(), sp.getSoLuong(),});
        }
    }

    public void checkChonSp(int index) {
        if (index < 0) {
            JOptionPane.showMessageDialog(rootPane, "Bạn chưa chọn sản phẩm cần bán");
            return;
        }

    }

    public void checkHD(int index) {
        if (index < 0) {
            JOptionPane.showMessageDialog(rootPane, "Bạn chưa chọn sản phẩm cần bán");
            return;
        }

    }

    public void getListTam(int index, int sl) {

        ProductDetailUI sp = new ProductDetailUI();
        sp.setMaLoaiSp(list.get(index).getMaLoaiSp());
        sp.setTenLoai(list.get(index).getTenLoai());
        sp.setMaSP(list.get(index).getMaSP());
        sp.setTenSP(list.get(index).getTenSP());
        sp.setMaSPCT(list.get(index).getMaSPCT());
        sp.setMaMau(list.get(index).getMaMau());
        sp.setMau(list.get(index).getMau());
        sp.setMaSize(list.get(index).getMaSize());
        sp.setSize(list.get(index).getSize());
        sp.setSoLuong(list.get(index).getSoLuong());
        sp.setGiaBan(list.get(index).getGiaBan());
        sp.setMaGiamGia(list.get(index).getMaGiamGia());
        sp.setPhanTram(list.get(index).getPhanTram());
        sp.setNgayBatDau(list.get(index).getNgayBatDau());
        sp.setNgayKetThuc(list.get(index).getNgayKetThuc());
        sp.setSoLuongBan(sl);
        listTam.add(sp);
    }

    public float inHD() {

        int a;
        float b;
        float c;
        float tong = 0;
        for (int i = 0; i < listTam.size(); i++) {
            float d = listTam.get(i).getPhanTram();
            float e = 100 - d;
            if (d == 0) {

                a = (int) listTam.get(i).getGiaBan();
                b = (float) listTam.get(i).getSoLuongBan();
                c = a * b;
                tong = tong + c;
                System.out.println("d = 0");
            } else {
                a = (int) listTam.get(i).getGiaBan();
                b = (float) listTam.get(i).getSoLuongBan();
                c = a * b * e / 100;
                tong = tong + c;
                System.out.println("d >0");
            }

        }

        return tong;
    }

//    Client getModel() {
//        Client entity = new Client();
//        entity.setTenKhachHang(tfTenKH.getText());
//        entity.setDiaChi(tfDC.getText());
//        entity.setDienThoai(tfSĐT.getText());
//
//        return entity;
//
//    }
    // add hd
//    Invoice getModel1() {
//
//        Invoice entity = new Invoice();
//        entity.setMaNhanVien(tfMaNV.getText());
//        entity.setNgayBan(dateN.getDate());
//        entity.setMaKhachHang(tfMaKH.getText());
//        entity.setTongTien(inHD());
//
//        return entity;
    public void addInvoice() throws SQLException, ClassNotFoundException {
        Class.forName(driver);
        Connection conn = DriverManager.getConnection(dburl, username, password);
        String sql = "INSERT INTO Invoice(MaNhanVien, NgayBan, MaKhachHang, TongTien, TrangThai, GhiChu) "
                + "VALUES(?,?,?,?,?,?)";

        PreparedStatement pstm = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);

//        pstm.setString(1, tfMaNV.getText());
        pstm.setString(1, maNV);
        pstm.setDate(2, new java.sql.Date(System.currentTimeMillis()));
        pstm.setString(3, String.valueOf(idValue2));
        pstm.setFloat(4, inHD());
        pstm.setInt(5, 1);
        pstm.setString(6, tfGhiChu.getText());

        pstm.execute();

        ResultSet rs = pstm.getGeneratedKeys();

        if (rs.next()) {
            // Giá trị của ID.
            // Chú ý với một số DB, tên cột phân biệt chữ hoa chữ thường.
            // (Ví dụ Postgres, tên cột luôn luôn là chữ thường).
            idValue = rs.getInt(1);
        }

        System.out.println("ID value: " + idValue);
//        System.out.println(listTam.toString());

    }

    public void addClient() throws SQLException, ClassNotFoundException {
        Class.forName(driver);
        Connection conn = DriverManager.getConnection(dburl, username, password);
        String sql = "insert into Client( TenKhachHang, DiaChi, DienThoai) values ( ?,?,?);";

        PreparedStatement pstm = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);

        pstm.setString(1, tfTenKH.getText());
        pstm.setString(2, "");
        pstm.setString(3, tfSĐT.getText());
        pstm.execute();

        ResultSet rs = pstm.getGeneratedKeys();

        if (rs.next()) {
            // Giá trị của ID.
            // Chú ý với một số DB, tên cột phân biệt chữ hoa chữ thường.
            // (Ví dụ Postgres, tên cột luôn luôn là chữ thường).
            idValue2 = rs.getInt(1);
        }

        System.out.println("ID value2: " + idValue2);
//        System.out.println(listTam.toString());

    }

//    }
    InvoiceDetail getModel2(int index) {
        InvoiceDetail entity = new InvoiceDetail();
        entity.setMaHoaDon(idValue);

        entity.setMaSanPhamChiTiet(listTam.get(index).getMaSPCT());

        entity.setSoLuong(listTam.get(index).getSoLuongBan());

        entity.setDonGia((float) listTam.get(index).getGiaBan());

        entity.setGiamGia(0);

        int a;
        float b;
        float c;

        a = (int) listTam.get(index).getGiaBan();
        b = (float) listTam.get(index).getSoLuongBan();
        c = a * b;
        entity.setTong(c);

        return entity;

    }

    public void showTable2() {
        model3.setRowCount(0);
        for (InvoiceUI sp : list3) {

            model3.addRow(new Object[]{
                sp.getMaHoaDon(), sp.getTenKhachHang(), sp.getDiaChi(), sp.getDienThoai(), sp.getNgayBan(), sp.getMaNhanVien(), sp.getGhiChu(),});
        }
    }

    public void showTable3() {
        model2.setRowCount(0);
        for (ProductDetailUI sp : listTam) {

            model2.addRow(new Object[]{
                sp.getMaSP(), sp.getTenSP(), sp.getTenLoai(), sp.getGiaBan(),
                sp.getSize(), sp.getMau(), sp.getSoLuongBan(),});

        }
    }

    public List<InvoiceUI> getList3() throws ClassNotFoundException, SQLException {
        Class.forName(driver);
        Connection conn = DriverManager.getConnection(dburl, username, password);
        List<InvoiceUI> list = new ArrayList<>();
        String sql = "select MaHoaDon, a.MaNhanVien,b.TenNhanVien,NgayBan, a.MaKhachHang,c.TenKhachHang,c.DiaChi,c.DienThoai, TongTien, a.TrangThai, GhiChu\n"
                + "from Invoice a inner join Staff b on a.MaNhanVien = b.MaNhanVien \n"
                + "				inner join Client c on c.MaKhachHang = a.MaKhachHang";
        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {

                InvoiceUI x = new InvoiceUI();
                x.setMaHoaDon(rs.getInt("MaHoaDon"));
                x.setMaNhanVien(rs.getString("MaNhanVien"));
                x.setTenNhanVien(rs.getString("TenNhanVien"));
                x.setMaKhachHang(rs.getString("MaKhachHang"));
                x.setTenKhachHang(rs.getString("TenKhachHang"));
                x.setDiaChi(rs.getString("DiaChi"));
                x.setDienThoai(rs.getString("DienThoai"));
                x.setNgayBan(rs.getDate("NgayBan"));
                x.setTongTien(rs.getFloat("TongTien"));
                x.setTrangThai(rs.getInt("TrangThai"));
                x.setGhiChu(rs.getString("GhiChu"));

                list.add(x);

            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    public List<InvoDtailUI> getListTam2(int a) throws ClassNotFoundException, SQLException {
        Class.forName(driver);
        Connection conn = DriverManager.getConnection(dburl, username, password);
        List<InvoDtailUI> list = new ArrayList<>();
        String sql = "     select a.MaHoaDon,g.MaTheLoai, g.TenTheLoai, d.MaSanPham, d.TenSanPham, c.SoLuong, a.GhiChu,\n"
                + "		 e.MaNhanVien,e.TenNhanVien,f.MaKhachHang, f.TenKhachHang,f.DiaChi,f.DienThoai,a.NgayBan,a.TongTien,\n"
                + "		 a.TrangThai, c.MaSanPhamChiTiet, DonGia, c.maMau, h.temMau, c.maSize, i.TenSize\n"
                + "         from Invoice a inner join Detailed_Invoice b on a.MaHoaDon = b.MaHoaDon\n"
                + "						inner join Product_Detail c on c.MaSanPhamChiTiet = b.MaSanPhamChiTiet \n"
                + "						inner join Product d on d.MaSanPham = c.MaSanPham\n"
                + "						inner join Staff e on e.MaNhanVien = a.MaNhanVien\n"
                + "						inner join Client f on f.MaKhachHang = a.MaKhachHang\n"
                + "						inner join Category g on g.MaTheLoai = d.MaTheLoai\n"
                + "						inner join Color h on h.maMau = c.maMau\n"
                + "						inner join Size i on i.maSize = c.maSize\n"
                + "						where a.MaHoaDon = ?";
        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, a);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                InvoDtailUI sp = new InvoDtailUI();

                sp.setMaHoaDon(rs.getInt("MaHoaDon"));
                sp.setMaTheLoai(rs.getString("MaTheLoai"));
                sp.setTenTheLoai(rs.getString("TenTheLoai"));
                sp.setMaSP(rs.getString("MaSanPham"));
                sp.setTenSP(rs.getString("TenSanPham"));
                sp.setSoLuong(rs.getInt("SoLuong"));
                sp.setGhiChu(rs.getString("GhiChu"));
                sp.setMaNhanVien(rs.getString("MaNhanVien"));
                sp.setTenNhanVien(rs.getString("TenNhanVien"));
                sp.setMaKhachHang(rs.getString("MaKhachHang"));
                sp.setTenKhachHang(rs.getString("TenKhachHang"));
                sp.setDiaChi(rs.getString("DiaChi"));
                sp.setDienThoai(rs.getString("DienThoai"));
                sp.setNgayBan(rs.getDate("NgayBan"));
                sp.setTongTien(rs.getFloat("TongTien"));
                sp.setTrangThai(rs.getInt("TrangThai"));
                sp.setMaSanPhamChiTiet(rs.getString("MaSanPhamChiTiet"));
                sp.setDonGia(rs.getFloat("DonGia"));
                sp.setMaMau(rs.getInt("maMau"));
                sp.setTemMau(rs.getString("temMau"));
                sp.setMaSize(rs.getInt("maSize"));
                sp.setTenSize(rs.getString("TenSize"));

                list.add(sp);

            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        jPanel3 = new javax.swing.JPanel();
        tfTim = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        btnTim = new javax.swing.JButton();
        jComboBox1 = new javax.swing.JComboBox<>();
        jLabel14 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        tfSoLuong = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        jButton5 = new javax.swing.JButton();
        jPanel4 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTable2 = new javax.swing.JTable();
        jScrollPane3 = new javax.swing.JScrollPane();
        tfGhiChu = new javax.swing.JTextArea();
        jLabel5 = new javax.swing.JLabel();
<<<<<<< HEAD
        lbThanhTIen = new javax.swing.JLabel();
=======
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
>>>>>>> b13c9903dfa06dc1dce80d1b8ffbc8b91489c75b
        jButton7 = new javax.swing.JButton();
        btnXoa = new javax.swing.JButton();
        jLabel10 = new javax.swing.JLabel();
        jButton9 = new javax.swing.JButton();
<<<<<<< HEAD
        tfTong = new javax.swing.JLabel();
        jPanel6 = new javax.swing.JPanel();
        dateN = new com.toedter.calendar.JDateChooser();
        tfSĐT = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        dateN1 = new com.toedter.calendar.JDateChooser();
        jPanel7 = new javax.swing.JPanel();
        jLabel7 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        tfTenKH = new javax.swing.JTextField();
        tfMaKH = new javax.swing.JTextField();
        lbTienCu = new javax.swing.JLabel();
        lbTieMoi = new javax.swing.JLabel();
        lbVND1 = new javax.swing.JLabel();
        lbVND2 = new javax.swing.JLabel();
        lbTienCu1 = new javax.swing.JLabel();
        lbTienMoi2 = new javax.swing.JLabel();
        jButton8 = new javax.swing.JButton();
        jButton10 = new javax.swing.JButton();
        btnHoanThanh = new javax.swing.JButton();
        jPanel5 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jPanel8 = new javax.swing.JPanel();
        jScrollPane4 = new javax.swing.JScrollPane();
        jTable3 = new javax.swing.JTable();
        lbGiamGia = new javax.swing.JLabel();
        btnTest = new javax.swing.JButton();
=======
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        jTextField5 = new javax.swing.JTextField();
        jTextField6 = new javax.swing.JTextField();
        jTextField7 = new javax.swing.JTextField();
        jTextField8 = new javax.swing.JTextField();
        jTextField9 = new javax.swing.JTextField();
        jDateChooser1 = new com.toedter.calendar.JDateChooser();
        jLabel15 = new javax.swing.JLabel();
        jPanel5 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jPanel6 = new javax.swing.JPanel();
        jScrollPane4 = new javax.swing.JScrollPane();
        jTable3 = new javax.swing.JTable();
>>>>>>> b13c9903dfa06dc1dce80d1b8ffbc8b91489c75b
        jButton4 = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(74, 31, 61));

        jButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/banhang.png"))); // NOI18N
        jButton1.setText("Bán Hàng");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jButton2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/nhanviens.png"))); // NOI18N
        jButton2.setText("Thông tin tài khoản");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jButton3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/products.png"))); // NOI18N
        jButton3.setText("Sản phẩm");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        jPanel2.setBorder(javax.swing.BorderFactory.createEtchedBorder(new java.awt.Color(102, 255, 255), null));

        jPanel3.setBackground(new java.awt.Color(74, 31, 61));

        tfTim.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tfTimActionPerformed(evt);
            }
        });

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(204, 204, 204));
        jLabel2.setText("Thanh toán bán hàng");

        btnTim.setText("Tìm kiếm");
        btnTim.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnTimActionPerformed(evt);
            }
        });

        jComboBox1.setFont(new java.awt.Font("Tahoma", 1, 13)); // NOI18N
        jComboBox1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "All", "Đã thanh toán", "Đang chờ", "Đổi/trả", "Hủy" }));

        jLabel14.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel14.setForeground(new java.awt.Color(204, 204, 204));
        jLabel14.setText("Tình trạng hóa đơn");

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel2)
                .addGap(44, 44, 44)
                .addComponent(tfTim, javax.swing.GroupLayout.PREFERRED_SIZE, 293, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnTim)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel14)
                .addGap(39, 39, 39)
                .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, 128, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(156, 156, 156))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(tfTim, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2)
                    .addComponent(btnTim))
                .addContainerGap(16, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel14))
                .addContainerGap())
        );

        jLabel3.setText("Số lượng bán:");

        tfSoLuong.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tfSoLuongActionPerformed(evt);
            }
        });

        jLabel4.setText("Giảm giá :");

        jButton5.setText("Lưu tạm");
        jButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton5ActionPerformed(evt);
            }
        });

        jPanel4.setBorder(javax.swing.BorderFactory.createTitledBorder("Thông tin hóa đơn"));

        jTable2.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
<<<<<<< HEAD
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null}
            },
            new String [] {
                "Mã sản phẩm", "Tên sản phẩm", "Loại", "Đơn giá", "Size", "Màu sắc", "Số lượng bán"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, true
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
=======
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null}
            },
            new String [] {
                "Mã sản phẩm", "Tên sản phẩm", "Loại", "Đơn giá", "Giảm giá thêm", "Size", "Màu sắc", "Số lượng bán"
>>>>>>> b13c9903dfa06dc1dce80d1b8ffbc8b91489c75b
            }
        });
        jTable2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTable2MouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(jTable2);

        tfGhiChu.setColumns(20);
        tfGhiChu.setRows(5);
        jScrollPane3.setViewportView(tfGhiChu);

        jLabel5.setText("Ghi chú");

<<<<<<< HEAD
        lbThanhTIen.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        lbThanhTIen.setText("Thành tiền : ");

        jButton7.setText("Đổi");
        jButton7.setEnabled(false);
        jButton7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton7ActionPerformed(evt);
            }
        });

        btnXoa.setText("Xóa");
        btnXoa.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnXoaActionPerformed(evt);
            }
        });

        jLabel10.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel10.setText("VNĐ");

        jButton9.setText("In");
        jButton9.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton9ActionPerformed(evt);
            }
        });

        tfTong.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        tfTong.setText("....................................");
=======
        jLabel6.setText("Địa chỉ:");

        jLabel7.setText("SĐT:");

        jLabel9.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel9.setText("Thành tiền:");
>>>>>>> b13c9903dfa06dc1dce80d1b8ffbc8b91489c75b

        dateN.setDateFormatString("yyyy-MM-dd");

        jLabel8.setText("Thời gian : ");
        jLabel8.setEnabled(false);

        jLabel11.setText("SĐT : ");

        dateN1.setDateFormatString("yyyy-MM-dd");
        dateN1.setEnabled(false);

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel8)
                    .addComponent(jLabel11))
                .addGap(16, 16, 16)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addComponent(dateN1, javax.swing.GroupLayout.DEFAULT_SIZE, 155, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(dateN, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addComponent(tfSĐT, javax.swing.GroupLayout.PREFERRED_SIZE, 155, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addGap(29, 29, 29)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addComponent(dateN, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel8))
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addGap(6, 6, 6)
                        .addComponent(dateN1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(21, 21, 21)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel11)
                    .addComponent(tfSĐT, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(31, Short.MAX_VALUE))
        );

        jLabel7.setText("Tên KH : ");

        jLabel12.setText("Mã KH : ");
        jLabel12.setEnabled(false);

        tfMaKH.setEnabled(false);
        tfMaKH.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tfMaKHActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addGap(23, 23, 23)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel7)
                    .addComponent(jLabel12))
                .addGap(18, 18, 18)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(tfTenKH)
                    .addComponent(tfMaKH, javax.swing.GroupLayout.DEFAULT_SIZE, 157, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addGap(32, 32, 32)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel12)
                    .addComponent(tfMaKH, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(28, 28, 28)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7)
                    .addComponent(tfTenKH, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(19, Short.MAX_VALUE))
        );

        lbTienCu.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        lbTienCu.setText("Tiền hóa đơn cũ :");
        lbTienCu.setEnabled(false);

        lbTieMoi.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        lbTieMoi.setText("Tiền hóa đơn mới :");
        lbTieMoi.setEnabled(false);

        lbVND1.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        lbVND1.setText("VNĐ");
        lbVND1.setEnabled(false);

        lbVND2.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        lbVND2.setText("VNĐ");
        lbVND2.setEnabled(false);

        lbTienCu1.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        lbTienCu1.setText("...................................................");
        lbTienCu1.setEnabled(false);

        lbTienMoi2.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        lbTienMoi2.setText("..................................................");
        lbTienMoi2.setEnabled(false);

        jButton8.setText("Hủy");
        jButton8.setEnabled(false);
        jButton8.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton8ActionPerformed(evt);
            }
        });

        jButton10.setText("Mới");
        jButton10.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton10ActionPerformed(evt);
            }
        });

        btnHoanThanh.setText("Hoàn Thành");
        btnHoanThanh.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnHoanThanhActionPerformed(evt);
            }
        });

        jLabel11.setText("Thời gian:");

        jLabel12.setText("Mã KH:");

        jLabel13.setText("Tên KH:");

        jLabel14.setText("Mã NV:");

        jDateChooser1.setEnabled(false);

        jLabel15.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel15.setText("....................................");

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
<<<<<<< HEAD
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel5)
                        .addGap(18, 18, 18)
                        .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 481, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(38, 38, 38)
                        .addComponent(btnXoa)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnHoanThanh))
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 749, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(15, 15, 15)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 8, Short.MAX_VALUE)
                        .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(36, Short.MAX_VALUE))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(jPanel4Layout.createSequentialGroup()
                                .addGap(3, 3, 3)
                                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(lbTienCu)
                                    .addComponent(lbTienCu1, javax.swing.GroupLayout.PREFERRED_SIZE, 169, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(lbVND1)
                                .addGap(64, 64, 64)
                                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel4Layout.createSequentialGroup()
                                        .addGap(197, 197, 197)
                                        .addComponent(jLabel10))
                                    .addComponent(lbTieMoi)
                                    .addGroup(jPanel4Layout.createSequentialGroup()
                                        .addComponent(lbTienMoi2, javax.swing.GroupLayout.PREFERRED_SIZE, 169, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(18, 18, 18)
                                        .addComponent(lbVND2))))
                            .addGroup(jPanel4Layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(lbThanhTIen)
                                    .addComponent(jButton9))
                                .addGap(18, 18, 18)
                                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addGroup(jPanel4Layout.createSequentialGroup()
                                        .addComponent(tfTong, javax.swing.GroupLayout.PREFERRED_SIZE, 188, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(87, 87, 87))
                                    .addGroup(jPanel4Layout.createSequentialGroup()
                                        .addGap(10, 10, 10)
                                        .addComponent(jButton10, javax.swing.GroupLayout.PREFERRED_SIZE, 73, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(jButton8, javax.swing.GroupLayout.PREFERRED_SIZE, 73, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(18, 18, 18)
                                        .addComponent(jButton7)))))
                        .addGap(22, 22, 22))))
=======
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(jLabel5)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 613, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel4Layout.createSequentialGroup()
                                .addGap(64, 64, 64)
                                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addGroup(jPanel4Layout.createSequentialGroup()
                                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jLabel6)
                                            .addComponent(jLabel11))
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                            .addComponent(jTextField5)
                                            .addComponent(jDateChooser1, javax.swing.GroupLayout.PREFERRED_SIZE, 131, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                                        .addComponent(jLabel7)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(jTextField6, javax.swing.GroupLayout.PREFERRED_SIZE, 131, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel4Layout.createSequentialGroup()
                                        .addComponent(jLabel14)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(jTextField9, javax.swing.GroupLayout.PREFERRED_SIZE, 131, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(jPanel4Layout.createSequentialGroup()
                                        .addComponent(jLabel12)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(jTextField7, javax.swing.GroupLayout.PREFERRED_SIZE, 131, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(jPanel4Layout.createSequentialGroup()
                                        .addComponent(jLabel13)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(jTextField8, javax.swing.GroupLayout.PREFERRED_SIZE, 131, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addGap(10, 10, 10))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jButton6)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jButton9)
                                .addGap(33, 33, 33)
                                .addComponent(jButton7))))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(jLabel9)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel15)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel10)))
                .addContainerGap())
            .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel4Layout.createSequentialGroup()
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 669, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 489, Short.MAX_VALUE)))
>>>>>>> b13c9903dfa06dc1dce80d1b8ffbc8b91489c75b
        );

        jPanel4Layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {jButton6, jButton7, jButton9});

        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
<<<<<<< HEAD
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                .addGap(20, 20, 20)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lbThanhTIen)
                            .addComponent(tfTong)
                            .addComponent(jLabel10))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jButton7)
                            .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jButton9)
                                .addComponent(jButton10)
                                .addComponent(jButton8)
                                .addComponent(btnXoa)
                                .addComponent(btnHoanThanh))))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(jLabel5)
                        .addGap(43, 43, 43))
                    .addComponent(jScrollPane3, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)))
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanel7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(60, 60, 60)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lbTieMoi)
                    .addComponent(lbTienCu))
                .addGap(18, 18, 18)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(lbVND1)
                        .addComponent(lbVND2)
                        .addComponent(lbTienMoi2))
                    .addComponent(lbTienCu1))
                .addContainerGap(100, Short.MAX_VALUE))
=======
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel11)
                            .addComponent(jDateChooser1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel6)
                            .addComponent(jTextField5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel7)
                            .addComponent(jTextField6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel12)
                            .addComponent(jTextField7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel13)
                            .addComponent(jTextField8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel14)
                            .addComponent(jTextField9, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel9)
                    .addComponent(jLabel10)
                    .addComponent(jLabel15))
                .addGap(10, 10, 10)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jButton6)
                        .addComponent(jButton7)
                        .addComponent(jButton9))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                            .addComponent(jLabel5)
                            .addGap(36, 36, 36)))))
            .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel4Layout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 223, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(100, Short.MAX_VALUE)))
>>>>>>> b13c9903dfa06dc1dce80d1b8ffbc8b91489c75b
        );

        jPanel5.setBorder(javax.swing.BorderFactory.createTitledBorder("Thông tin sản phẩm"));

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null}
            },
            new String [] {
                "Mã sản phẩm", "Tên sản phẩm", "Loại", "Size", "Màu", "Giá", "Số lượng"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jTable1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTable1MouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(jTable1);

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
<<<<<<< HEAD
            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 742, Short.MAX_VALUE)
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                .addContainerGap())
        );

        jPanel8.setBorder(javax.swing.BorderFactory.createTitledBorder("Danh sách hóa đơn"));

        jTable3.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null}
            },
            new String [] {
                "Mã hóa đơn", "Tên khách", "SĐT", "Ngày tạo", "Mã nhân viên", "Trạng thái", "Ghi chú"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jTable3.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTable3MouseClicked(evt);
            }
        });
        jScrollPane4.setViewportView(jTable3);

        javax.swing.GroupLayout jPanel8Layout = new javax.swing.GroupLayout(jPanel8);
        jPanel8.setLayout(jPanel8Layout);
        jPanel8Layout.setHorizontalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane4)
        );
        jPanel8Layout.setVerticalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane4, javax.swing.GroupLayout.DEFAULT_SIZE, 229, Short.MAX_VALUE)
                .addContainerGap())
        );

        lbGiamGia.setText("lbGiamGia");

        btnTest.setText("xong");
        btnTest.setEnabled(false);
        btnTest.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnTestActionPerformed(evt);
            }
        });

=======
            .addGap(0, 629, Short.MAX_VALUE)
            .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel5Layout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 577, Short.MAX_VALUE)
                    .addContainerGap()))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 230, Short.MAX_VALUE)
            .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel5Layout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 164, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
        );

        jPanel6.setBorder(javax.swing.BorderFactory.createTitledBorder("Danh sách hóa đơn"));

        jTable3.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null}
            },
            new String [] {
                "Tên khách hàng", "Số điện thoại", "Địa chỉ", "Tên nhân viên", "Ngày tạo"
            }
        ));
        jScrollPane4.setViewportView(jTable3);

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 511, Short.MAX_VALUE)
            .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel6Layout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(jScrollPane4, javax.swing.GroupLayout.DEFAULT_SIZE, 491, Short.MAX_VALUE)
                    .addContainerGap()))
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 230, Short.MAX_VALUE)
            .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel6Layout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 164, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(42, Short.MAX_VALUE)))
        );

>>>>>>> b13c9903dfa06dc1dce80d1b8ffbc8b91489c75b
        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
<<<<<<< HEAD
                        .addComponent(jPanel8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(jLabel3)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(tfSoLuong, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(28, 28, 28)
                                .addComponent(jLabel4)
                                .addGap(18, 18, 18)
                                .addComponent(lbGiamGia)
                                .addGap(47, 47, 47)
                                .addComponent(jButton5)
                                .addGap(27, 27, 27)
                                .addComponent(btnTest)
                                .addGap(0, 0, Short.MAX_VALUE)))
                        .addContainerGap())))
=======
                        .addComponent(jTextField3, javax.swing.GroupLayout.PREFERRED_SIZE, 66, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(42, 42, 42)
                        .addComponent(jButton5))
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addComponent(jPanel4, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(jPanel2Layout.createSequentialGroup()
                            .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(20, Short.MAX_VALUE))
>>>>>>> b13c9903dfa06dc1dce80d1b8ffbc8b91489c75b
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(25, 25, 25)
<<<<<<< HEAD
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(tfSoLuong, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel3))
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel4)
                        .addComponent(jButton5)
                        .addComponent(lbGiamGia)
                        .addComponent(btnTest)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
=======
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(26, 26, 26)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(jTextField2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel4)
                    .addComponent(jTextField3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton5))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(28, 28, 28))
>>>>>>> b13c9903dfa06dc1dce80d1b8ffbc8b91489c75b
        );

        jPanel8.getAccessibleContext().setAccessibleName("Danh sách hóa đơn");

        jPanel2Layout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {jPanel5, jPanel6});

        jButton4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/exits.png"))); // NOI18N
        jButton4.setText("Exit");
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });

        jLabel1.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(204, 204, 204));
        jLabel1.setText("Nhân Viên");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jButton3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jButton1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jButton2, javax.swing.GroupLayout.DEFAULT_SIZE, 159, Short.MAX_VALUE)
                            .addComponent(jButton4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(40, 40, 40)
                        .addComponent(jLabel1)))
                .addGap(18, 18, 18)
                .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addGap(26, 26, 26)
                .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton4, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(563, Short.MAX_VALUE))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 16, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 9, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton3ActionPerformed

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton4ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton2ActionPerformed

    private void tfTimActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tfTimActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_tfTimActionPerformed

    private void tfSoLuongActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tfSoLuongActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_tfSoLuongActionPerformed

    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed

//        lbGiamGia.setText(maNV);
        if (tfSoLuong.getText().equals("")) {
            JOptionPane.showMessageDialog(rootPane, "Bạn chưa nhập số lượng cần bán!");
            return;
        }
        tfSoLuong.requestFocus();

        checkChonSp(index1);

        int soLuong = Integer.parseInt(tfSoLuong.getText());

        getListTam(index1, soLuong);

        System.out.println(list.get(index1).getMaSPCT());
        showTable3();

//        model2.addRow(new Object[]{
//            list.get(index1).getMaSP(),
//            list.get(index1).getTenSP(),
//            list.get(index1).getTenLoai(),
//            list.get(index1).getGiaBan(),
//            list.get(index1).getSize(),
//            list.get(index1).getMau(),
//            soLuong
//        });

    }//GEN-LAST:event_jButton5ActionPerformed

    private void jTable1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable1MouseClicked
        jTable2.clearSelection();
//        table.getModel().getValueAt(row_index, col_index);
        index1 = jTable1.getSelectedRow();
        tfSoLuong.requestFocus();
        for (int i = 0; i < listTam.size(); i++) {
            if (list.get(index1).getMaSPCT().equalsIgnoreCase(listTam.get(i).getMaSPCT())) {

                listTam.get(i).setSoLuongBan(Integer.parseInt(tfSoLuong.getText()));
                btnTest.setEnabled(true);
                jButton5.setEnabled(false);
            }
            break;
        }


    }//GEN-LAST:event_jTable1MouseClicked

    private void jButton9ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton9ActionPerformed
        // TODO add your handling code here:
//        if (click == 1) {
//            JOptionPane.showMessageDialog(rootPane, "Bạn phải điền đầy đủ thông tin khách hàng");
//            click = 2;
//            client();            
//        }
//        

        listTam.forEach(System.out::println);
        if (a == 1) {

//            Client clnt = getModel();
//            srClientService.insert(clnt);
            try {
                addClient();
                addInvoice();

            } catch (SQLException ex) {
                Logger.getLogger(HoaDon.class
                        .getName()).log(Level.SEVERE, null, ex);

            } catch (ClassNotFoundException ex) {
                Logger.getLogger(HoaDon.class
                        .getName()).log(Level.SEVERE, null, ex);
            }

            System.out.println(listTam.size());

            // insertSevice thừa "sql"
            for (int i = 0; i < listTam.size(); i++) {
                InvoiceDetail inVDT = getModel2(i);
                srInvD.insert(inVDT);
                System.out.println(listTam.get(i).getMaSPCT());
            }

            try {
                list3 = getList3();

            } catch (ClassNotFoundException ex) {
                Logger.getLogger(HoaDon.class
                        .getName()).log(Level.SEVERE, null, ex);

            } catch (SQLException ex) {
                Logger.getLogger(HoaDon.class
                        .getName()).log(Level.SEVERE, null, ex);
            }
            tong = inHD();
            tfTong.setText(String.valueOf(tong));

            showTable2();
            System.out.println(list3.size());

        }

        if (a == 2) {

            float d = Float.parseFloat(lbTienCu1.getText());
            lbTienMoi2.setText(String.valueOf(tong));
            if (tong > d) {
                lbThanhTIen.setText("Tiền khách phải trả : ");
                tfTong.setText(String.valueOf(tong - d));
            } else {
                lbThanhTIen.setText("Tiền trả lại khách : ");
                tfTong.setText(String.valueOf(d - tong));

            }

//            Client clnt = getModel();
//            srClientService.insert(clnt);
            try {
                addClient();
                addInvoice();

            } catch (SQLException ex) {
                Logger.getLogger(HoaDon.class
                        .getName()).log(Level.SEVERE, null, ex);

            } catch (ClassNotFoundException ex) {
                Logger.getLogger(HoaDon.class
                        .getName()).log(Level.SEVERE, null, ex);
            }

            System.out.println(listTam.size());

            // insertSevice thừa "sql"
            for (int i = 0; i < listTam.size(); i++) {
                InvoiceDetail inVDT = getModel2(i);
                srInvD.insert(inVDT);
                System.out.println(listTam.get(i).getMaSPCT());
            }

            try {
                list3 = getList3();

            } catch (ClassNotFoundException ex) {
                Logger.getLogger(HoaDon.class
                        .getName()).log(Level.SEVERE, null, ex);

            } catch (SQLException ex) {
                Logger.getLogger(HoaDon.class
                        .getName()).log(Level.SEVERE, null, ex);
            }
            tong = inHD();

            showTable2();
            System.out.println(list3.size());

        }
    }//GEN-LAST:event_jButton9ActionPerformed

    private void jButton7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton7ActionPerformed
        model2.setRowCount(0);
        a = 2;
//        
//    public void getListTam(int index, int sl) {
//
//        ProductDetailUI sp = new ProductDetailUI();
//        sp.setMaLoaiSp(list.get(index).getMaLoaiSp());
//        sp.setTenLoai(list.get(index).getTenLoai());
//        sp.setMaSP(list.get(index).getMaSP());
//        sp.setTenSP(list.get(index).getTenSP());
//        sp.setMaSPCT(list.get(index).getMaSPCT());
//        sp.setMaMau(list.get(index).getMaMau());
//        sp.setMau(list.get(index).getMau());
//        sp.setMaSize(list.get(index).getMaSize());
//        sp.setSize(list.get(index).getSize());
//        sp.setSoLuong(list.get(index).getSoLuong());
//        sp.setGiaBan(list.get(index).getGiaBan());
//        sp.setSoLuongBan(sl);
//        listTam.add(sp);
//    }

    }//GEN-LAST:event_jButton7ActionPerformed

    private void btnXoaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnXoaActionPerformed

//        Client clnt = getModel();
//        srClientService.insert(clnt);

    }//GEN-LAST:event_btnXoaActionPerformed

    private void tfMaKHActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tfMaKHActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_tfMaKHActionPerformed

    private void btnTimActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTimActionPerformed
        // TODO add your handling code here:

        String shr = tfTim.getText();
        for (int i = 0; i < list.size(); i++) {
            if (shr.equalsIgnoreCase(list.get(i).getTenSP())) {

                model.setRowCount(0);

                model.addRow(new Object[]{
                    list.get(i).getMaSP(), list.get(i).getTenSP(), list.get(i).getTenLoai(), list.get(i).getSize(), list.get(i).getMau(), list.get(i).getSoLuong(),});

            }
        }
        tfTim.requestFocus();
    }//GEN-LAST:event_btnTimActionPerformed

    private void jTable3MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable3MouseClicked
        // TODO add your handling code here:
        index2 = jTable3.getSelectedRow();

        if (index2 >= 0) {
            jButton7.setEnabled(true);
            jButton8.setEnabled(true);
            lbTieMoi.setEnabled(true);
            lbTienCu.setEnabled(true);
            lbTienCu1.setEnabled(true);
            lbTienMoi2.setEnabled(true);
            lbVND1.setEnabled(true);
            lbVND2.setEnabled(true);
            model2.setRowCount(0);
        }

        for (int i = 0; i < list3.size(); i++) {
            if (jTable3.getValueAt(index2, 0).equals(list3.get(i).getMaHoaDon())) {
                lbTienCu1.setText(String.valueOf(list3.get(i).getTongTien()));
            }
        }
        try {
            list4 = getListTam2((int) jTable3.getValueAt(index2, 0));
            System.out.println(list4.size());

            for (InvoDtailUI sp : list4) {

                model2.addRow(new Object[]{
                    sp.getMaSP(), sp.getTenSP(), sp.getTenTheLoai(), sp.getDonGia(), sp.getTenSize(), sp.getTemMau(), sp.getSoLuong(),});
            }

        } catch (ClassNotFoundException ex) {
            Logger.getLogger(HoaDon.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(HoaDon.class.getName()).log(Level.SEVERE, null, ex);
        }

    }//GEN-LAST:event_jTable3MouseClicked

    private void jButton8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton8ActionPerformed
        // TODO add your handling code here:

        if (index2 < 0) {
            JOptionPane.showMessageDialog(rootPane, "Bạn chưa chọn hóa đơn cần hủy");
            return;
        }
        for (int i = 0; i < list3.size(); i++) {
            if (jTable3.getValueAt(index2, 0).equals(list3.get(i).getMaHoaDon())) {
                lbTienMoi2.setText(String.valueOf(list3.get(i).getTongTien() - list3.get(i).getTongTien()));
            }
        }

        lbThanhTIen.setText("số tiền trả lại cho khách: ");

        for (int i = 0; i < list3.size(); i++) {
            if (jTable3.getValueAt(index2, 0).equals(list3.get(i).getMaHoaDon())) {

                tfTong.setText(String.valueOf(list3.get(i).getTongTien()));
            }
        }

    }//GEN-LAST:event_jButton8ActionPerformed

    private void jTable2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable2MouseClicked
        // TODO add your handling code here:

        if (index3 >= 0) {

            tfSoLuong.setText(String.valueOf(jTable2.getValueAt(index3, 6)));
        }


    }//GEN-LAST:event_jTable2MouseClicked

    private void jButton10ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton10ActionPerformed
        // TODO add your handling code here:

        jButton7.setEnabled(false);
        jButton8.setEnabled(false);
        lbTieMoi.setEnabled(false);
        lbTienCu.setEnabled(false);
        lbTienCu1.setEnabled(false);
        lbTienMoi2.setEnabled(false);
        lbVND1.setEnabled(false);
        lbVND2.setEnabled(false);
        jTable3.clearSelection();
        model2.setRowCount(0);
        listTam.clear();
        tfSoLuong.setText(String.valueOf(0));

    }//GEN-LAST:event_jButton10ActionPerformed

    private void btnTestActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTestActionPerformed
        // TODO add your handling code here:

        for (int i = 0; i < listTam.size(); i++) {
            if (list.get(index1).getMaSPCT().equalsIgnoreCase(listTam.get(i).getMaSPCT())) {

                listTam.get(i).setSoLuongBan(Integer.parseInt(tfSoLuong.getText()));

            }
            break;
        }
        showTable3();
    }//GEN-LAST:event_btnTestActionPerformed

    private void btnHoanThanhActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnHoanThanhActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnHoanThanhActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;

                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(HoaDon.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);

        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(HoaDon.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);

        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(HoaDon.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);

        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(HoaDon.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new HoaDon(taiKhoan, maNV, tenNV, passNV).setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnHoanThanh;
    private javax.swing.JButton btnTest;
    private javax.swing.JButton btnTim;
    private javax.swing.JButton btnXoa;
    private com.toedter.calendar.JDateChooser dateN;
    private com.toedter.calendar.JDateChooser dateN1;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton10;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton5;
    private javax.swing.JButton jButton7;
    private javax.swing.JButton jButton8;
    private javax.swing.JButton jButton9;
<<<<<<< HEAD
    private javax.swing.JComboBox<String> jComboBox1;
=======
    private com.toedter.calendar.JDateChooser jDateChooser1;
>>>>>>> b13c9903dfa06dc1dce80d1b8ffbc8b91489c75b
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
<<<<<<< HEAD
    private javax.swing.JLabel jLabel14;
=======
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
>>>>>>> b13c9903dfa06dc1dce80d1b8ffbc8b91489c75b
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel7;
<<<<<<< HEAD
    private javax.swing.JLabel jLabel8;
=======
    private javax.swing.JLabel jLabel9;
>>>>>>> b13c9903dfa06dc1dce80d1b8ffbc8b91489c75b
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
<<<<<<< HEAD
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
=======
>>>>>>> b13c9903dfa06dc1dce80d1b8ffbc8b91489c75b
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JTable jTable1;
    private javax.swing.JTable jTable2;
    private javax.swing.JTable jTable3;
<<<<<<< HEAD
    private javax.swing.JLabel lbGiamGia;
    private javax.swing.JLabel lbThanhTIen;
    private javax.swing.JLabel lbTieMoi;
    private javax.swing.JLabel lbTienCu;
    private javax.swing.JLabel lbTienCu1;
    private javax.swing.JLabel lbTienMoi2;
    private javax.swing.JLabel lbVND1;
    private javax.swing.JLabel lbVND2;
    private javax.swing.JTextArea tfGhiChu;
    private javax.swing.JTextField tfMaKH;
    private javax.swing.JTextField tfSoLuong;
    private javax.swing.JTextField tfSĐT;
    private javax.swing.JTextField tfTenKH;
    private javax.swing.JTextField tfTim;
    private javax.swing.JLabel tfTong;
=======
    private javax.swing.JTextArea jTextArea1;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JTextField jTextField2;
    private javax.swing.JTextField jTextField3;
    private javax.swing.JTextField jTextField5;
    private javax.swing.JTextField jTextField6;
    private javax.swing.JTextField jTextField7;
    private javax.swing.JTextField jTextField8;
    private javax.swing.JTextField jTextField9;
>>>>>>> b13c9903dfa06dc1dce80d1b8ffbc8b91489c75b
    // End of variables declaration//GEN-END:variables

}
