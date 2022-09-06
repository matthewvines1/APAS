package com.example.socialmediaproject;

import com.example.socialmediaproject.databaseentities.User;

import java.sql.*;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Calendar;

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
        log("setConnection", Arrays.toString(jdbcUrl) + "\n" + Arrays.toString(sqlUsername) + "\n" + Arrays.toString(sqlPassword));
    }

    public User getUser(char[] username, char[] passwordHash) {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        currentUser = null;
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
            log("getUser", stringBuilderUrl + "\n" + stringBuilderUsername + "\n" + stringBuilderPassword);
            connection = DriverManager.getConnection(stringBuilderUrl.toString(), stringBuilderUsername.toString(), stringBuilderPassword.toString());
            statement = connection.prepareStatement(SQL_GET_USER);
            statement.setString(1, stringBuilderUserUsername.toString());
            statement.setString(2, stringBuilderUserPasswordHash.toString());
            try {
                resultSet = statement.executeQuery();
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
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeDatabase(resultSet, statement, connection);
        }
        stringBuilderUrl.setLength(0);
        stringBuilderUsername.setLength(0);
        stringBuilderPassword.setLength(0);
        stringBuilderUserUsername.setLength(0);
        stringBuilderUserPasswordHash.setLength(0);
        return currentUser;
    }

    public boolean newUser(User user) {
        boolean isSuccess = false;
        if (currentUser.getIsActive() && currentUser.getEditRole()) {
            Connection connection = null;
            PreparedStatement statement = null;
            StringBuilder stringBuilderUrl = new StringBuilder();
            stringBuilderUrl.append(jdbcUrl);
            StringBuilder stringBuilderUsername = new StringBuilder();
            stringBuilderUsername.append(sqlUsername);
            StringBuilder stringBuilderPassword = new StringBuilder();
            stringBuilderPassword.append(sqlPassword);
            try {
                connection = DriverManager.getConnection(stringBuilderUrl.toString(), stringBuilderUsername.toString(), stringBuilderPassword.toString());
                statement = connection.prepareStatement(SQL_SET_USER);
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
                e.printStackTrace();
            } finally {
                closeDatabase(null, statement, connection);
            }
            stringBuilderUrl.setLength(0);
            stringBuilderUsername.setLength(0);
            stringBuilderPassword.setLength(0);
        }
        return isSuccess;
    }

    public boolean newUser(char[] username, char[] passwordHash) {
        Connection connection = null;
        PreparedStatement statement = null;
        boolean isSuccess = false;
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
            connection = DriverManager.getConnection(stringBuilderUrl.toString(), stringBuilderUsername.toString(), stringBuilderPassword.toString());
            statement = connection.prepareStatement(SQL_SET_USER);
            String creationDateTime = (new SimpleDateFormat("yyyy-MM-dd HH:mm:ss")).format(Calendar.getInstance().getTime());
            String lastLoginDateTime = (new SimpleDateFormat("yyyy-MM-dd HH:mm:ss")).format(Calendar.getInstance().getTime());
            statement.setString(1, stringBuilderUserUsername.toString());
            statement.setString(2, stringBuilderUserPasswordHash.toString());
            statement.setBoolean(3, false);
            statement.setBoolean(4, false);
            statement.setBoolean(5, false);
            statement.setBoolean(6, true);
            statement.setString(7, creationDateTime);
            statement.setString(8, lastLoginDateTime);
            statement.executeUpdate();
            isSuccess = true;
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeDatabase(null, statement, connection);
        }
        stringBuilderUrl.setLength(0);
        stringBuilderUsername.setLength(0);
        stringBuilderPassword.setLength(0);
        stringBuilderUserUsername.setLength(0);
        stringBuilderUserPasswordHash.setLength(0);
        return isSuccess;
    }
    private void closeDatabase(ResultSet resultSet, PreparedStatement preparedStatement, Connection connection) {
        if(resultSet != null) {
            try {
                resultSet.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        if(preparedStatement != null) {
            try {
                preparedStatement.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        if(connection != null) {
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    private void log(String functionName, String message) {
        Global.log("DatabaseConnector", functionName, message);
    }
}
