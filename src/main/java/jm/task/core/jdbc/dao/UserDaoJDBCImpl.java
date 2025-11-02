package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import static jm.task.core.jdbc.util.Util.openConnection;

public class UserDaoJDBCImpl implements UserDao {

    private Connection connection = openConnection();

    public UserDaoJDBCImpl() {

    }

    private boolean specificExecuteQuery(String sql) {
        try (Statement statement = connection.createStatement()){
            return statement.execute(sql);
        } catch ( SQLException e ) {
            e.printStackTrace();
            return false;
        }
    }

    private int specificExecuteUpdate(String sql, String name, String lastName, byte age) {
        try (PreparedStatement statement = connection.prepareStatement(sql)){
            statement.setString(1, name);
            statement.setString(2, lastName);
            statement.setByte(3, age);
            return statement.executeUpdate();
        } catch ( SQLException e ) {
            e.printStackTrace();
            return -1;
        }
    }

    private int specificExecuteUpdate(String sql,long id) {
        try (PreparedStatement statement = connection.prepareStatement(sql)){
            statement.setLong(1, id);
            return statement.executeUpdate();
        } catch ( SQLException e ) {
            e.printStackTrace();
            return -1;
        }
    }

    public void createUsersTable() {
        String sql = """
            CREATE TABLE IF NOT EXISTS user (
              id INT NOT NULL AUTO_INCREMENT,
              name VARCHAR(45) NOT NULL,
              last_name VARCHAR(45) NOT NULL,
              age INT(3) NOT NULL,
              PRIMARY KEY (id));
        """;
        specificExecuteQuery(sql);
    }

    public void dropUsersTable() {
        String sql = "DROP TABLE IF EXISTS user;";
        specificExecuteQuery(sql);
    }

    public void saveUser(String name, String lastName, byte age) {
        String sql = "INSERT INTO user (name, last_name, age) VALUES (?, ?, ?);";
        if (specificExecuteUpdate(sql, name, lastName, age) != -1) {
            System.out.println(String.format("User with name %s created successfully", name));
        }
    }

    public void removeUserById(long id) {
        String sql = "DELETE FROM user WHERE id = ?;";
        specificExecuteUpdate(sql, id);
    }

    public List<User> getAllUsers() {
        String sql = "SELECT * FROM user;";
        try (Statement statement = connection.createStatement()) {
            ResultSet resultSet = statement.executeQuery(sql);
            List<User> users = new ArrayList<>();
            while(resultSet.next()) {
                User user = new User();
                user.setId(resultSet.getLong("id"));
                user.setName(resultSet.getString("name"));
                user.setLastName(resultSet.getString("last_name"));
                user.setAge(resultSet.getByte("age"));
                users.add(user);
            }
            return users;
        } catch (SQLException e ) {
            e.printStackTrace();
            return null;
        }
    }

    public void cleanUsersTable() {
        String sql = "DELETE FROM user;";
        specificExecuteQuery(sql);
    }
}
