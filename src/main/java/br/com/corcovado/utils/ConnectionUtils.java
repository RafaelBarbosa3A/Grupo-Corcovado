package br.com.corcovado.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

/**
 *
 * @author wesley
 */
public class ConnectionUtils {

    private final static String DB_URI = "jdbc:mysql://localhost:3306/corcovado";
    private final static String DB_USER = "corcovado";
    private final static String DB_PASSWORD = "senhacorcovado";

    public static Connection getConnection() {
        Connection connection = null;

        try {
            Properties properties = new Properties();
            properties.put("user", DB_USER);
            properties.put("password", DB_PASSWORD);
            connection = DriverManager.getConnection(DB_URI, properties);
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

        return connection;
    }
}
