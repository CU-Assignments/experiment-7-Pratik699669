import java.sql.*;
import java.util.Scanner;

class EasyLevel {
    public static void main(String[] args) {
        try (Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/testdb", "root", "password");
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery("SELECT * FROM Employee")) {
            while (rs.next()) {
                System.out.println("Pratik Mishra: " + rs.getInt("EmpID") + " " + rs.getString("Name") + " " + rs.getDouble("Salary"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
