package Connection;

import java.sql.*;
import javax.swing.JOptionPane;

public class JDBC {
    public static Connection con;

    public static Connection getConnection() {
        try {
            String url = "jdbc:mysql://localhost:3306/projectinventory?serverTimezone=UTC";
            String userName = "root";
            String password = "remon123";

            if (con == null)
                con = DriverManager.getConnection(url, userName, password);
        } catch (SQLException ep) {
            System.out.println("Database connnection Error!" + ep);
            JOptionPane.showMessageDialog(null, "Database Connection Failed!" + ep, "Connection Error",
                    JOptionPane.ERROR_MESSAGE);
        }

        return con;
    }
}
