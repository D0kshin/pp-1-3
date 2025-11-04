package jm.task.core.jdbc;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.service.UserServiceImpl;

public class Main {
    public static void main(String[] args) {
        // реализуйте алгоритм здесь
        UserServiceImpl userService = new UserServiceImpl();
        userService.createUsersTable();



        userService.saveUser("Ilya", "Abraamov", (byte) 12);
        userService.saveUser("Adolf", "Abraamov", (byte) 16);
        userService.saveUser("Vasya", "Abraamov", (byte) 20);
        userService.saveUser("Ibragim", "Abraamov", (byte) 39);
        userService.saveUser("Egor", "Abraamov", (byte) 3);


        for(User user : userService.getAllUsers()){
            System.out.println(user);
        }
        userService.cleanUsersTable();
        userService.dropUsersTable();

    }
}
