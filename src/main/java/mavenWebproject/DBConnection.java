package mavenWebproject;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {
    public static Connection getConnection() throws SQLException {
        String jdbcUrl = System.getenv("host"); // Use your own environment variable name
        String username = System.getenv("user"); // Use your own environment variable name
        String password = System.getenv("pass"); // Use your own environment variable name
       

        String fullJdbcUrl = jdbcUrl + "/" + mydb;
        

        return DriverManager.getConnection(fullJdbcUrl, username, password);
    }
}
