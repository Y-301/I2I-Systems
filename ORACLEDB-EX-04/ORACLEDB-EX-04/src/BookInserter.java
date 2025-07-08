import java.sql.*;
import java.util.Random;

public class BookInserter {

    public static void main(String[] args) {

        String jdbcUrl = "jdbc:oracle:thin:@13.61.151.190:1521/XEPDB1";
        String username = "SYSTEM"; // or your Oracle user
        String password = "ORACLE"; // your Oracle password

        try {
            Class.forName("oracle.jdbc.driver.OracleDriver");
            Connection conn = DriverManager.getConnection(jdbcUrl, username, password);
            System.out.println("Connected to Oracle DB");

            String sql = "INSERT INTO BOOK (ID, NAME, ISBN, CREATE_DATE) VALUES (?, ?, ?, SYSDATE)";
            PreparedStatement pstmt = conn.prepareStatement(sql);

            Random random = new Random();

            for (int i = 1; i <= 100; i++) {
                pstmt.setInt(1, i);
                pstmt.setString(2, "Book" + random.nextInt(1000));
                pstmt.setString(3, "ISBN" + random.nextInt(100000));
                pstmt.executeUpdate();
            }

            System.out.println("Inserted 100 records into BOOK table.");
            pstmt.close();
            conn.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
