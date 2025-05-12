package dev;

import java.sql.*;

public class MainApp {

    // Cấu hình kết nối
    private static final String DB_URL = "jdbc:mysql://localhost:3306/shop_db";
    private static final String DB_USER = "root";         // tài khoản của bạn
    private static final String DB_PASSWORD = "";         // mật khẩu nếu có

    public static void main(String[] args) {
        Connection conn = null;
        Statement stmt = null;
        ResultSet rs = null;

        try {
            // Tải driver JDBC
            Class.forName("com.mysql.cj.jdbc.Driver");

            // Kết nối tới MySQL
            conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
            System.out.println("Kết nối CSDL thành công!");

            // Tạo truy vấn
            String sql = "SELECT id, name, description, price, quantity FROM products";
            stmt = conn.createStatement();
            rs = stmt.executeQuery(sql);

            // Tiêu đề bảng
            System.out.printf("\n%-5s %-20s %-30s %-10s %-10s\n", "ID", "Tên", "Mô tả", "Giá", "SL");
            System.out.println("----------------------------------------------------------------------------");

            // Duyệt và in từng dòng
            while (rs.next()) {
                int id = rs.getInt("id");
                String name = rs.getString("name");
                String description = rs.getString("description");
                double price = rs.getDouble("price");
                int quantity = rs.getInt("quantity");

                // In sản phẩm
                System.out.printf("%-5d %-20s %-30s %-10.0f %-10d\n",
                        id, name, description.length() > 28 ? description.substring(0, 27) + "…" : description, price, quantity);
            }

        } catch (Exception e) {
            System.out.println("❌ Lỗi: " + e.getMessage());
            e.printStackTrace();
        } finally {
            // Đóng kết nối
            try {
                if (rs != null) rs.close();
                if (stmt != null) stmt.close();
                if (conn != null) conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}