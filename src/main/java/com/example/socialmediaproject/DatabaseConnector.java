package com.example.socialmediaproject;

import com.example.socialmediaproject.databaseentities.User;

import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.Arrays;

public class DatabaseConnector {

    private final String DB_NAME = "apas";
    private final String SQL_GET_USER = "SELECT * FROM "+DB_NAME+".users WHERE username=? AND password_hash=?";
    private final String SQL_SET_USER = "INSERT INTO "+DB_NAME+".users (username, password_hash, has_view_role, " +
            "has_edit_role, has_delete_role, is_active, creation_date_time, last_login_date_time) VALUES (" +
            "?, ?, ?, ?, ?, ?, ?, ?)";
    private char[] jdbcUrl;
    private char[] sqlUsername;
    private char[] sqlPassword;
    private User currentUser;

    public void setConnection(char[] jdbcUrl, char[] sqlUsername, char[] sqlPassword) {
        this.jdbcUrl = jdbcUrl;
        this.sqlUsername = sqlUsername;
        this.sqlPassword = sqlPassword;
    }

    public User getUser(char[] username, char[] passwordHash) {
        StringBuilder stringBuilderUrl = new StringBuilder();
        stringBuilderUrl.append(jdbcUrl);
        StringBuilder stringBuilderUsername = new StringBuilder();
        stringBuilderUsername.append(sqlUsername);
        StringBuilder stringBuilderPassword = new StringBuilder();
        stringBuilderPassword.append(sqlPassword);
        StringBuilder stringBuilderUserUsername = new StringBuilder();
        stringBuilderUserUsername.append(username);
        StringBuilder stringBuilderUserPasswordHash = new StringBuilder();
        stringBuilderUserPasswordHash.append(passwordHash);
        try {
            //System.out.println(Arrays.toString(jdbcUrl) + "\n" + stringBuilderUsername + "\n" + stringBuilderPassword);
            Connection connection = DriverManager.getConnection(stringBuilderUrl.toString(),
                stringBuilderUsername.toString(), stringBuilderPassword.toString());
            PreparedStatement statement = connection.prepareStatement(SQL_GET_USER);
            statement.setString(1, stringBuilderUserUsername.toString());
            statement.setString(2, stringBuilderUserPasswordHash.toString());
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
                    stringBuilderUrl.setLength(0);
                    stringBuilderUsername.setLength(0);
                    stringBuilderPassword.setLength(0);
                    stringBuilderUserUsername.setLength(0);
                    stringBuilderUserPasswordHash.setLength(0);
                    return currentUser;
                }
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        stringBuilderUrl.setLength(0);
        stringBuilderUsername.setLength(0);
        stringBuilderPassword.setLength(0);
        stringBuilderUserUsername.setLength(0);
        stringBuilderUserPasswordHash.setLength(0);
        return null;
    }

    public boolean setUser(User user) {
        boolean isSuccess = false;
        if (currentUser.getIsActive() && currentUser.getEditRole()) {
            StringBuilder stringBuilderUrl = new StringBuilder();
            stringBuilderUrl.append(jdbcUrl);
            StringBuilder stringBuilderUsername = new StringBuilder();
            stringBuilderUsername.append(sqlUsername);
            StringBuilder stringBuilderPassword = new StringBuilder();
            stringBuilderPassword.append(sqlPassword);
            try (Connection connection = DriverManager.getConnection(stringBuilderUrl.toString(),
                    stringBuilderUsername.toString(), stringBuilderPassword.toString());
                 PreparedStatement statement = connection.prepareStatement(SQL_SET_USER)) {
                String creationDateTime = (new SimpleDateFormat("yyyy-MM-dd")).format(user.getCreationDate()) +
                        " " + (new SimpleDateFormat("HH:mm:ss")).format(user.getCreationTime());
                String lastLoginDateTime = (new SimpleDateFormat("yyyy-MM-dd")).format(user.getLastLoginDate()) +
                        " " + (new SimpleDateFormat("HH:mm:ss")).format(user.getLastLoginTime());
                StringBuilder stringBuilderUserUsername = new StringBuilder();
                stringBuilderUserUsername.append(user.getUsername());
                StringBuilder stringBuilderUserPasswordHash = new StringBuilder();
                stringBuilderUserPasswordHash.append(user.getPasswordHash());
                statement.setString(1, stringBuilderUserUsername.toString());
                statement.setString(2, stringBuilderUserPasswordHash.toString());
                statement.setBoolean(3, user.getViewRole() && currentUser.getViewRole());
                statement.setBoolean(4, user.getEditRole());
                statement.setBoolean(5, user.getDeleteRole() && currentUser.getDeleteRole());
                statement.setBoolean(6, user.getIsActive());
                statement.setString(7, creationDateTime);
                statement.setString(8, lastLoginDateTime);
                statement.executeUpdate();
                stringBuilderUserUsername.setLength(0);
                stringBuilderUserPasswordHash.setLength(0);
                isSuccess = true;
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
            stringBuilderUrl.setLength(0);
            stringBuilderUsername.setLength(0);
            stringBuilderPassword.setLength(0);
        }
        return isSuccess;
    }
}
