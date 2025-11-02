package jm.task.core.jdbc.util;

import java.sql.Connection;
import java.sql.DriverManager;

public final class ConnectionManager {
    private static String URL_KEY = "db.url";
    private static String USERNAME_KEY = "db.username";
    private static String PASSWORD_KEY = "db.password";
    private ConnectionManager() {}

    public static Connection open() {
        try {
            return DriverManager.getConnection(
//                    "jdbc:mysql://localhost:3306/",
//                    "root", "root");
                    PropertiesUtil.getProperty(URL_KEY),
                    PropertiesUtil.getProperty(USERNAME_KEY),
                    PropertiesUtil.getProperty(PASSWORD_KEY));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

}
