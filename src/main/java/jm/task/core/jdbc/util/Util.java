package jm.task.core.jdbc.util;

import jm.task.core.jdbc.model.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import java.sql.Connection;
import java.sql.DriverManager;

public final class Util {
    // реализуйте настройку соеденения с БД

    private static String URL_KEY = "db.url";
    private static String USERNAME_KEY = "db.username";
    private static String PASSWORD_KEY = "db.password";
    private Util() {}

    public static Connection openConnection() {
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

    public static SessionFactory getSessionFactory() {
        org.hibernate.cfg.Configuration configuration = new org.hibernate.cfg.Configuration();

        configuration
                .addPackage("jm.task.core.jdbc.util")
                .setProperty("hibernate.connection.driver_class", "com.mysql.jdbc.Driver")
                .setProperty("hibernate.connection.url", PropertiesUtil.getProperty(URL_KEY))
                .setProperty("hibernate.connection.username", PropertiesUtil.getProperty(USERNAME_KEY))
                .setProperty("hibernate.connection.password", PropertiesUtil.getProperty(PASSWORD_KEY));

        configuration.addAnnotatedClass(User.class);

        try {
            SessionFactory sessionFactory = configuration.buildSessionFactory();
            return sessionFactory;
        } catch (Exception e) {
            throw new ExceptionInInitializerError(e);
        }

    }

}
