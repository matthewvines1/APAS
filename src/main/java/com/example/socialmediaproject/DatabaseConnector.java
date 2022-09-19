package com.example.socialmediaproject;

import com.example.socialmediaproject.databaseentities.Contact;
import com.example.socialmediaproject.databaseentities.User;

import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Calendar;

public class DatabaseConnector {

    private final String DB_NAME = "apas";
    private final String SQL_GET_USER = "SELECT * FROM "+DB_NAME+".users WHERE username=? AND password_hash=?";
    private final String SQL_SET_USER = "INSERT INTO "+DB_NAME+".users (username, password_hash, has_view_role, " +
            "has_edit_role, has_delete_role, is_active, creation_date_time, last_login_date_time) VALUES (" +
            "?, ?, ?, ?, ?, ?, ?, ?)";
    private final String SQL_SET_CONTACT = "INSERT INTO "+DB_NAME+".contacts (first_name, is_active, last_name, " +
            "middle_name) VALUES (?, ?, ?, ?)";
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
            connection = DriverManager.getConnection(stringBuilderUrl.toString(), stringBuilderUsername.toString(), stringBuilderPassword.toString());
            statement = connection.prepareStatement(SQL_GET_USER);
            statement.setString(1, stringBuilderUserUsername.toString());
            statement.setString(2, stringBuilderUserPasswordHash.toString());
            try {
                resultSet = statement.executeQuery();
                while (resultSet.next()) {
                    currentUser = new User(resultSet.getInt("user_id"), username, passwordHash,
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
                log("getUser", e.toString());
            }
        } catch (SQLException e) {
            log("getUser", e.toString());
        } finally {
            closeDatabase(resultSet, statement, connection);
            Global.clearStringBuilders(new StringBuilder[]{stringBuilderUrl, stringBuilderUsername, stringBuilderPassword,
                    stringBuilderUserUsername, stringBuilderUserPasswordHash});
        }
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
            StringBuilder stringBuilderUserUsername = new StringBuilder();
            stringBuilderUserUsername.append(user.getUsername());
            StringBuilder stringBuilderUserPasswordHash = new StringBuilder();
            stringBuilderUserPasswordHash.append(user.getPasswordHash());
            try {
                connection = DriverManager.getConnection(stringBuilderUrl.toString(), stringBuilderUsername.toString(), stringBuilderPassword.toString());
                statement = connection.prepareStatement(SQL_SET_USER);
                String creationDateTime = (new SimpleDateFormat("yyyy-MM-dd")).format(user.getCreationDate()) +
                        " " + (new SimpleDateFormat("HH:mm:ss")).format(user.getCreationTime());
                String lastLoginDateTime = (new SimpleDateFormat("yyyy-MM-dd")).format(user.getLastLoginDate()) +
                        " " + (new SimpleDateFormat("HH:mm:ss")).format(user.getLastLoginTime());
                statement.setString(1, stringBuilderUserUsername.toString());
                statement.setString(2, stringBuilderUserPasswordHash.toString());
                statement.setBoolean(3, user.getViewRole() && currentUser.getViewRole());
                statement.setBoolean(4, user.getEditRole());
                statement.setBoolean(5, user.getDeleteRole() && currentUser.getDeleteRole());
                statement.setBoolean(6, user.getIsActive());
                statement.setString(7, creationDateTime);
                statement.setString(8, lastLoginDateTime);
                statement.executeUpdate();
                Global.clearStringBuilders(new StringBuilder[]{stringBuilderUserUsername,
                        stringBuilderUserPasswordHash});
                isSuccess = true;
            } catch (SQLException e) {
                log("newUser", e.toString());
            } finally {
                closeDatabase(null, statement, connection);
                Global.clearStringBuilders(new StringBuilder[]{stringBuilderUrl, stringBuilderUsername,
                        stringBuilderPassword, stringBuilderUserUsername, stringBuilderUserPasswordHash});
            }
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
            log("newUser", e.toString());
        } finally {
            closeDatabase(null, statement, connection);
            Global.clearStringBuilders(new StringBuilder[]{stringBuilderUrl, stringBuilderUsername, stringBuilderPassword,
                    stringBuilderUserUsername, stringBuilderUserPasswordHash});
        }
        return isSuccess;
    }

    public boolean createContact(Contact contact) {
        Connection connection = null;
        PreparedStatement statement = null;
        boolean isSuccess = false;
        if(currentUser.getIsActive() && currentUser.getEditRole()) {
            StringBuilder stringBuilderUrl = new StringBuilder();
            stringBuilderUrl.append(jdbcUrl);
            StringBuilder stringBuilderUsername = new StringBuilder();
            stringBuilderUsername.append(sqlUsername);
            StringBuilder stringBuilderPassword = new StringBuilder();
            stringBuilderPassword.append(sqlPassword);
            try {
                connection = DriverManager.getConnection(stringBuilderUrl.toString(), stringBuilderUsername.toString(),
                        stringBuilderPassword.toString());
                statement = connection.prepareStatement(SQL_SET_CONTACT);
                statement.setString(1, contact.getFirstName());
                statement.setBoolean(2, contact.isActive());
                statement.setString(3, contact.getLastName());
                statement.setString(4, contact.getMiddleName());
                statement.executeUpdate();
                isSuccess = true;
            } catch (Exception e) {
                log("createContact", e.toString());
            } finally {
                closeDatabase(null, statement, connection);
                Global.clearStringBuilders(new StringBuilder[]{stringBuilderUrl, stringBuilderUsername,
                        stringBuilderPassword});
            }
        }
        return isSuccess;
    }

    public boolean createContactBulk(Contact[] contacts) {
        Connection connection = null;
        PreparedStatement statement = null;
        boolean isSuccess = false;
        if(currentUser.getIsActive() && currentUser.getEditRole()) {
            StringBuilder stringBuilderUrl = new StringBuilder();
            stringBuilderUrl.append(jdbcUrl);
            StringBuilder stringBuilderUsername = new StringBuilder();
            stringBuilderUsername.append(sqlUsername);
            StringBuilder stringBuilderPassword = new StringBuilder();
            stringBuilderPassword.append(sqlPassword);
            try {
                connection = DriverManager.getConnection(stringBuilderUrl.toString(), stringBuilderUsername.toString(),
                        stringBuilderPassword.toString());
                statement = connection.prepareStatement(SQL_SET_CONTACT);
                for(int i = 0; i < contacts.length; i++) {
                    isSuccess = false;
                    statement.setString(1, contacts[i].getFirstName());
                    statement.setBoolean(2, contacts[i].isActive());
                    statement.setString(3, contacts[i].getLastName());
                    statement.setString(4, contacts[i].getMiddleName());
                    statement.executeUpdate();
                    isSuccess = true;
                }
            } catch (Exception e) {
                log("createContactBulk", e.toString());
            } finally {
                closeDatabase(null, statement, connection);
                Global.clearStringBuilders(new StringBuilder[]{stringBuilderUrl, stringBuilderUsername,
                        stringBuilderPassword});
            }
        }
        return isSuccess;
    }

    private void closeDatabase(ResultSet resultSet, PreparedStatement preparedStatement, Connection connection) {
        if(resultSet != null) {
            try {
                resultSet.close();
            } catch (SQLException e) {
                log("closeDatabase", e.toString());
            }
        }
        if(preparedStatement != null) {
            try {
                preparedStatement.close();
            } catch (SQLException e) {
                log("closeDatabase", e.toString());
            }
        }
        if(connection != null) {
            try {
                connection.close();
            } catch (SQLException e) {
                log("closeDatabase", e.toString());
            }
        }
    }

    private void log(String functionName, String message) {
        Global.log("DatabaseConnector", functionName, message);
    }
}
