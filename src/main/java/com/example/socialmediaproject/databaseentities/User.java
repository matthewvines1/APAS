package com.example.socialmediaproject.databaseentities;

import java.sql.Date;
import java.sql.Time;

public class User {

    private String name;
    private String username;
    private String passwordHash;
    private boolean hasViewRole;
    private boolean hasEditRole;
    private boolean hasDeleteRole;
    private boolean isActive;
    private Date creationDate;
    private Time creationTime;
    private Date lastLoginDate;
    private Time lastLoginTime;

    public User(String name, String username, String passwordHash, boolean hasViewRole, boolean hasEditRole,
                boolean hasDeleteRole, boolean isActive, Date creationDate, Time creationTime, Date lastLoginDate,
                Time lastLoginTime) {
        this.name = name;
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

    public String getName() {
        return name;
    }

    public String getUsername() {
        return username;
    }

    public String getPasswordHash() {
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

    public void setName(String name) {
        this.name = name;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPasswordHash(String passwordHash) {
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
