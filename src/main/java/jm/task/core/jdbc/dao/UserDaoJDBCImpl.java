package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;

import java.sql.*;
import java.util.List;

import static jm.task.core.jdbc.util.Util.openConnection;

public class UserDaoJDBCImpl implements UserDao {

    Connection connection = openConnection();

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
        specificExecuteUpdate(sql, name, lastName, age);
    }

    public void removeUserById(long id) {

    }

    public List<User> getAllUsers() {
        return null;
    }

    public void cleanUsersTable() {
        String sql = "DELETE FROM user;";
        specificExecuteQuery(sql);
    }
}
