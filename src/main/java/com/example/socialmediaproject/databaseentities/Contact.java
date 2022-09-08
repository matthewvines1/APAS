package com.example.socialmediaproject.databaseentities;

public class Contact {

    private int contactId;
    private String firstName;
    private boolean isActive;
    private String lastName;
    private String middleName;

    public Contact(int contactId, String firstName, boolean isActive, String lastName, String middleName) {
        this.contactId = contactId;
        this.firstName = firstName;
        this.isActive = isActive;
        this.lastName = lastName;
        this.middleName = middleName;
    }

    public int getContactId() {
        return contactId;
    }

    public void setContactId(int contactId) {
        this.contactId = contactId;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        isActive = active;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getMiddleName() {
        return middleName;
    }

    public void setMiddleName(String middleName) {
        this.middleName = middleName;
    }
}
