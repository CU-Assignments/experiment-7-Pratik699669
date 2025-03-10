import java.sql.*;
import java.util.Scanner;

class MediumLevel {
    public static void main(String[] args) {
        try (Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/testdb", "root", "password")) {
            Scanner sc = new Scanner(System.in);
            while (true) {
                System.out.println("1. Insert 2. Read 3. Update 4. Delete 5. Exit");
                int choice = sc.nextInt();
                switch (choice) {
                    case 1:
                        System.out.print("Enter ProductID, ProductName, Price, Quantity: ");
                        try (PreparedStatement ps = conn.prepareStatement("INSERT INTO Product VALUES (?, ?, ?, ?)");) {
                            ps.setInt(1, sc.nextInt());
                            ps.setString(2, sc.next());
                            ps.setDouble(3, sc.nextDouble());
                            ps.setInt(4, sc.nextInt());
                            ps.executeUpdate();
                        }
                        break;
                    case 2:
                        try (Statement stmt = conn.createStatement(); ResultSet rs = stmt.executeQuery("SELECT * FROM Product")) {
                            while (rs.next()) {
                                System.out.println("Pratik Mishra: " + rs.getInt(1) + " " + rs.getString(2) + " " + rs.getDouble(3) + " " + rs.getInt(4));
                            }
                        }
                        break;
                    case 3:
                        System.out.print("Enter ProductID and new Price: ");
                        try (PreparedStatement ps = conn.prepareStatement("UPDATE Product SET Price=? WHERE ProductID=?")) {
                            ps.setDouble(1, sc.nextDouble());
                            ps.setInt(2, sc.nextInt());
                            ps.executeUpdate();
                        }
                        break;
                    case 4:
                        System.out.print("Enter ProductID to delete: ");
                        try (PreparedStatement ps = conn.prepareStatement("DELETE FROM Product WHERE ProductID=?")) {
                            ps.setInt(1, sc.nextInt());
                            ps.executeUpdate();
                        }
                        break;
                    case 5:
                        sc.close();
                        return;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
