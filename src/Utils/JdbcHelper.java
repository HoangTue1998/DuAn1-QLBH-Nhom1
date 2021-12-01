/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author Minh
 */
public class JdbcHelper {
     private static String driver = "com.microsoft.sqlserver.jdbc.SQLServerDriver";
//    private static String dburl = "jdbc:sqlserver://localhost;database=QLBH";  
     private static String dburl="jdbc:sqlserver://localhost\\Admin:1433;databaseName=QLBH";
    private static String username = "sa";
    private static String password = "123";

<<<<<<< HEAD
    private static String driver = "com.microsoft.sqlserver.jdbc.SQLServerDriver";
    private static String dburl = "jdbc:sqlserver://DESKTOP-1T2BB3E\\SQLEXPRESS:1433;databaseName=QLBH1";
    private static String username = "sa";
    private static String password = "12345";

    static {
=======
     static {
>>>>>>> b13c9903dfa06dc1dce80d1b8ffbc8b91489c75b
        try {
            Class.forName(driver);
        } catch (ClassNotFoundException ex) {
            throw new RuntimeException(ex);
        }
    }
     
     public static PreparedStatement prepareStatement(String sql, Object... args) throws SQLException {
         Connection connection = DriverManager.getConnection(dburl, username, password);
        PreparedStatement pstmt = null;
        if (sql.trim().startsWith("{")) {
            pstmt = connection.prepareCall(sql);
        } else {
            pstmt = connection.prepareStatement(sql);
        }
        for (int i = 0; i < args.length; i++) {
            pstmt.setObject(i + 1, args[i]);
        }
        return pstmt;
    }
     
     public static void executeUpdate(String sql, Object... args) {
        try {
     
            PreparedStatement stmt = prepareStatement(sql, args);
            try {
                stmt.executeUpdate();
            } finally {
                stmt.getConnection().close();
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        
    }
     
      public static ResultSet executeQuery(String sql, Object... args) {
        try {
            PreparedStatement stmt = prepareStatement(sql, args);
            return stmt.executeQuery();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
