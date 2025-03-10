import java.sql.*;
import java.util.Scanner;

class Student {
    int StudentID;
    String Name, Department;
    double Marks;
    public Student(int id, String name, String dept, double marks) {
        this.StudentID = id;
        this.Name = name;
        this.Department = dept;
        this.Marks = marks;
    }
}

class StudentController {
    private Connection conn;
    public StudentController() throws SQLException {
        conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/testdb", "root", "password");
    }
    public void insertStudent(Student s) throws SQLException {
        try (PreparedStatement ps = conn.prepareStatement("INSERT INTO Students VALUES (?, ?, ?, ?)");) {
            ps.setInt(1, s.StudentID);
            ps.setString(2, s.Name);
            ps.setString(3, s.Department);
            ps.setDouble(4, s.Marks);
            ps.executeUpdate();
        }
    }
    public void displayStudents() throws SQLException {
        try (Statement stmt = conn.createStatement(); ResultSet rs = stmt.executeQuery("SELECT * FROM Students")) {
            while (rs.next()) {
                System.out.println("Pratik Mishra: " + rs.getInt(1) + " " + rs.getString(2) + " " + rs.getString(3) + " " + rs.getDouble(4));
            }
        }
    }
    public void updateStudentMarks(int id, double marks) throws SQLException {
        try (PreparedStatement ps = conn.prepareStatement("UPDATE Students SET Marks=? WHERE StudentID=?")) {
            ps.setDouble(1, marks);
            ps.setInt(2, id);
            ps.executeUpdate();
        }
    }
    public void deleteStudent(int id) throws SQLException {
        try (PreparedStatement ps = conn.prepareStatement("DELETE FROM Students WHERE StudentID=?")) {
            ps.setInt(1, id);
            ps.executeUpdate();
        }
    }
}

class HardLevel {
    public static void main(String[] args) {
        try (Scanner sc = new Scanner(System.in)) {
            StudentController controller = new StudentController();
            while (true) {
                System.out.println("1. Insert 2. Display 3. Update 4. Delete 5. Exit");
                int choice = sc.nextInt();
                switch (choice) {
                    case 1:
                        System.out.print("Enter StudentID, Name, Department, Marks: ");
                        controller.insertStudent(new Student(sc.nextInt(), sc.next(), sc.next(), sc.nextDouble()));
                        break;
                    case 2:
                        controller.displayStudents();
                        break;
                    case 3:
                        System.out.print("Enter StudentID and new Marks: ");
                        controller.updateStudentMarks(sc.nextInt(), sc.nextDouble());
                        break;
                    case 4:
                        System.out.print("Enter StudentID to delete: ");
                        controller.deleteStudent(sc.nextInt());
                        break;
                    case 5:
                        return;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
