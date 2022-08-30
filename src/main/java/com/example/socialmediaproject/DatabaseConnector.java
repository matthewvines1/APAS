package com.example.socialmediaproject;

import com.example.socialmediaproject.databaseentities.User;

import java.sql.*;
import java.text.SimpleDateFormat;

public class DatabaseConnector {

    private final String DB_NAME = "apas";
    private final String SQL_GET_USER = "SELECT * FROM "+DB_NAME+".users WHERE username=? AND password_hash=?";
    private final String SQL_SET_USER = "INSERT INTO "+DB_NAME+".users (username, password_hash, has_view_role, " +
            "has_edit_role, has_delete_role, is_active, creation_date_time, last_login_date_time) VALUES (" +
            "?, ?, ?, ?, ?, ?, ?, ?)";
    private String jdbcUrl;
    private String sqlUsername;
    private String sqlPassword;
    private User currentUser;

    public void setConnection(String jdbcUrl, String sqlUsername, String sqlPassword) {
        this.jdbcUrl = jdbcUrl;
        this.sqlUsername = sqlUsername;
        this.sqlPassword = sqlPassword;
    }

    public User getUser(String username, String passwordHash) {
        try(Connection connection = DriverManager.getConnection(jdbcUrl, sqlUsername, sqlPassword);
            PreparedStatement statement = connection.prepareStatement(SQL_GET_USER)) {
            statement.setString(1, username);
            statement.setString(2, passwordHash);
            try(ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
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
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return null;
    }

    public void setUser(User user) {
        if (currentUser.getIsActive() && currentUser.getEditRole()) {
            try (Connection connection = DriverManager.getConnection(jdbcUrl, sqlUsername, sqlPassword);
                 PreparedStatement statement = connection.prepareStatement(SQL_SET_USER)) {
                String creationDateTime = (new SimpleDateFormat("yyyy-MM-dd")).format(user.getCreationDate()) +
                        " " + (new SimpleDateFormat("HH:mm:ss")).format(user.getCreationTime());
                String lastLoginDateTime = (new SimpleDateFormat("yyyy-MM-dd")).format(user.getLastLoginDate()) +
                        " " + (new SimpleDateFormat("HH:mm:ss")).format(user.getLastLoginTime());
                statement.setString(1, user.getUsername());
                statement.setString(2, user.getPasswordHash());
                statement.setBoolean(3, user.getViewRole() && currentUser.getViewRole());
                statement.setBoolean(4, user.getEditRole());
                statement.setBoolean(5, user.getDeleteRole() && currentUser.getDeleteRole());
                statement.setBoolean(6, user.getIsActive());
                statement.setString(7, creationDateTime);
                statement.setString(8, lastLoginDateTime);
                statement.executeUpdate();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
