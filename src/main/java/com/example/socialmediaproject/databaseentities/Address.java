package com.example.socialmediaproject.databaseentities;

public class Address {

    private int addressId;
    private String addressLine1;
    private String addressLine2;
    private String city;
    private String country;
    private boolean is_active;
    private String state;
    private String zip;

    public Address(int addressId, String addressLine1, String addressLine2, String city, String country,
                   boolean is_active, String state, String zip) {
        this.addressId = addressId;
        this.addressLine1 = addressLine1;
        this.addressLine2 = addressLine2;
        this.city = city;
        this.country = country;
        this.is_active = is_active;
        this.state = state;
        this.zip = zip;
    }

    public int getAddressId() {
        return addressId;
    }

    public String getAddressLine1() {
        return addressLine1;
    }

    public String getAddressLine2() {
        return addressLine2;
    }

    public String getCity() {
        return city;
    }

    public String getCountry() {
        return country;
    }

    public boolean isIs_active() {
        return is_active;
    }

    public String getState() {
        return state;
    }

    public String getZip() {
        return zip;
    }

    public void setAddressId(int addressId) {
        this.addressId = addressId;
    }

    public void setAddressLine1(String addressLine1) {
        this.addressLine1 = addressLine1;
    }

    public void setAddressLine2(String addressLine2) {
        this.addressLine2 = addressLine2;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public void setIs_active(boolean is_active) {
        this.is_active = is_active;
    }

    public void setState(String state) {
        this.state = state;
    }

    public void setZip(String zip) {
        this.zip = zip;
    }
}
