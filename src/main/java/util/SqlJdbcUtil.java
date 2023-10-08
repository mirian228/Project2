package util;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class SqlJdbcUtil {
    public static Connection getConnection() {
        Connection connection = null;

        try (FileInputStream file = new FileInputStream("src/main/resources/db.properties")) {
            Properties properties = new Properties();
            properties.load(file);

            String user = properties.getProperty("user");
            String password = properties.getProperty("password");
            String url = properties.getProperty("url");

            connection = DriverManager.getConnection(url, user, password);
        } catch (IOException | SQLException e) {
            System.out.println(e.getMessage());
        }
        return connection;
    }
}
