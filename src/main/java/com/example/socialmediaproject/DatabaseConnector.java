package com.example.socialmediaproject;

import com.example.socialmediaproject.databaseentities.User;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Date;

public class DatabaseConnector {
    private Connection connection;

    public DatabaseConnector(String jdbcURL, String username, String password) {
        try (Connection newConnection = DriverManager.getConnection(jdbcURL, username, password)) {
            connection = newConnection;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public User getUserForAuthentication(String username, String passwordHash) {
        return new User("", "", "", false, false, false, false, null, null, null, null);
    }

    public void setUser(User user, String passwordHash) {

    }

    public void getUserByPasswordHash() {
        try(Statement statement = connection.createStatement()) {

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
