package com.example.socialmediaproject;

import com.example.socialmediaproject.databaseentities.User;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;

public class DatabaseConnector {
    private Connection connection;
    private User currentUser;

    public DatabaseConnector(String jdbcURL, String username, String password) {
        try (Connection newConnection = DriverManager.getConnection(jdbcURL, username, password)) {
            connection = newConnection;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public User getUser(String username, String passwordHash) {
        try(Statement statement = connection.createStatement()) {
            String sql = "SELECT * FROM users WHERE username='"+username+"' AND password_hash='"+passwordHash+"'";
                ResultSet resultSet = statement.executeQuery(sql);
                while(resultSet.next()) {
                    currentUser = new User(username, passwordHash,
                            resultSet.getBoolean("has_view_role"),
                            resultSet.getBoolean("has_edit_role"),
                            resultSet.getBoolean("has_delete_role"),
                            resultSet.getBoolean("is_active"),
                            resultSet.getDate("creation_date_time"),
                            resultSet.getTime("creation_date_time"),
                            resultSet.getDate("last_login_date_time"),
                            resultSet.getTime("last_login_date_time"));
                    return currentUser;
                }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return null;
    }

    public void setUser(User user) {
        if (currentUser.getIsActive() && currentUser.getEditRole()) {
            try (Statement statement = connection.createStatement()) {
                String creationDateTime = (new SimpleDateFormat("yyyy-MM-dd")).format(user.getCreationDate()) +
                        " " + (new SimpleDateFormat("HH:mm:ss")).format(user.getCreationTime());
                String lastLoginDateTime = (new SimpleDateFormat("yyyy-MM-dd")).format(user.getLastLoginDate()) +
                        " " + (new SimpleDateFormat("HH:mm:ss")).format(user.getLastLoginTime());
                String sql = "INSERT INTO users (name, username, password_hash, has_view_role, has_edit_role, has_delete_role, is_active, creation_date_time, last_login_date_time) " +
                        "VALUES ('" + user.getUsername() + "', '" + user.getPasswordHash() + "', '" +
                        (user.getViewRole() && currentUser.getViewRole()) + "', '" + (user.getEditRole()) + "', " +
                        (user.getDeleteRole() && currentUser.getDeleteRole()) + "', '" + user.getIsActive() + "', " +
                        creationDateTime + "', " + lastLoginDateTime;
                statement.executeUpdate(sql);
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
