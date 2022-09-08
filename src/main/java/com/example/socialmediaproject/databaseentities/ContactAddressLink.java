package com.example.socialmediaproject.databaseentities;

public class ContactAddressLink {

    private int contactAddressLinkId;
    private int addressId;
    private int contactId;
    private boolean isDeleted;

    public ContactAddressLink(int contactAddressLinkId, int addressId, int contactId, boolean isDeleted) {
        this.contactAddressLinkId = contactAddressLinkId;
        this.addressId = addressId;
        this.contactId = contactId;
        this.isDeleted = isDeleted;
    }

    public int getContactAddressLinkId() {
        return contactAddressLinkId;
    }

    public int getAddressId() {
        return addressId;
    }

    public int getContactId() {
        return contactId;
    }

    public boolean isDeleted() {
        return isDeleted;
    }

    public void setContactAddressLinkId(int contactAddressLinkId) {
        this.contactAddressLinkId = contactAddressLinkId;
    }

    public void setAddressId(int addressId) {
        this.addressId = addressId;
    }

    public void setContactId(int contactId) {
        this.contactId = contactId;
    }

    public void setDeleted(boolean deleted) {
        isDeleted = deleted;
    }
}
