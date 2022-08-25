package com.example.socialmediaproject;

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
            System.out.println("Success!");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
