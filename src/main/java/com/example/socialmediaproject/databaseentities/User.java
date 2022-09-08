package com.example.socialmediaproject.databaseentities;

import java.sql.Date;
import java.sql.Time;

public class User {
    private int userId;
    private char[] username;
    private char[] passwordHash;
    private boolean hasViewRole;
    private boolean hasEditRole;
    private boolean hasDeleteRole;
    private boolean isActive;
    private Date creationDate;
    private Time creationTime;
    private Date lastLoginDate;
    private Time lastLoginTime;

    public User(int id, char[] username, char[] passwordHash, boolean hasViewRole, boolean hasEditRole,
                boolean hasDeleteRole, boolean isActive, Date creationDate, Time creationTime, Date lastLoginDate,
                Time lastLoginTime) {
        this.userId = id;
        this.username = username;
        this.passwordHash = passwordHash;
        this.hasViewRole = hasViewRole;
        this.hasEditRole = hasEditRole;
        this.hasDeleteRole = hasDeleteRole;
        this.isActive = isActive;
        this.creationDate = creationDate;
        this.creationTime = creationTime;
        this.lastLoginDate = lastLoginDate;
        this.lastLoginTime = lastLoginTime;
    }

    public int getUserId() {
        return userId;
    }

    public char[] getUsername() {
        return username;
    }

    public char[] getPasswordHash() {
        return passwordHash;
    }

    public boolean getViewRole() {
        return hasViewRole;
    }

    public boolean getEditRole() {
        return hasEditRole;
    }

    public boolean getDeleteRole() {
        return hasDeleteRole;
    }

    public boolean getIsActive() {
        return isActive;
    }

    public Date getCreationDate() {
        return creationDate;
    }

    public Time getCreationTime() {
        return creationTime;
    }

    public Date getLastLoginDate() {
        return lastLoginDate;
    }

    public Time getLastLoginTime() {
        return lastLoginTime;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public void setUsername(char[] username) {
        this.username = username;
    }

    public void setPasswordHash(char[] passwordHash) {
        this.passwordHash = passwordHash;
    }

    public void setViewRole(boolean hasViewRole) {
        this.hasViewRole = hasViewRole;
    }

    public void setEditRole(boolean hasEditRole) {
        this.hasEditRole = hasEditRole;
    }

    public void setDeleteRole(boolean hasDeleteRole) {
        this.hasDeleteRole = hasDeleteRole;
    }

    public void setIsActive(boolean isActive) {
        this.isActive = isActive;
    }

    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
    }

    public void setCreationTime(Time creationTime) {
        this.creationTime = creationTime;
    }

    public void setLastLoginDate(Date lastLoginDate) {
        this.lastLoginDate = lastLoginDate;
    }

    public void setLastLoginTime(Time lastLoginTime) {
        this.lastLoginTime = lastLoginTime;
    }
}
