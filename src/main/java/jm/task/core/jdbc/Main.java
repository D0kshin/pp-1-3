package jm.task.core.jdbc;

import jm.task.core.jdbc.service.UserServiceImpl;
import jm.task.core.jdbc.util.ConnectionManager;
import jm.task.core.jdbc.util.Util;

import java.sql.DriverManager;

public class Main {
    public static void main(String[] args) {
        // реализуйте алгоритм здесь
        UserServiceImpl userService = new UserServiceImpl();
        userService.createUsersTable();
        userService.saveUser("Daniel", "Daniel", (byte) 3);
        userService.cleanUsersTable();
    }
}
