package com.example.socialmediaproject.databaseentities;

import java.math.BigDecimal;
import java.sql.Date;
import java.sql.Time;

public class Invoice {

    private int invoiceId;
    private BigDecimal amountCollected;
    private Date creationDate;
    private Time creationTime;
    private boolean isDeleted;
    private String notes;
    private BigDecimal serviceCharge;
    private Date serviceDateIn;
    private Time serviceTimeIn;
    private Date serviceDateOut;
    private Time serviceTimeOut;
    private int userId;

    public Invoice(int invoiceId, BigDecimal amountCollected, Date creationDate, Time creationTime, boolean isDeleted,
                   String notes, BigDecimal serviceCharge, Date serviceDateIn, Time serviceTimeIn, Date serviceDateOut,
                   Time serviceTimeOut, int userId) {
        this.invoiceId = invoiceId;
        this.amountCollected = amountCollected;
        this.creationDate = creationDate;
        this.creationTime = creationTime;
        this.isDeleted = isDeleted;
        this.notes = notes;
        this.serviceCharge = serviceCharge;
        this.serviceDateIn = serviceDateIn;
        this.serviceTimeIn = serviceTimeIn;
        this.serviceDateOut = serviceDateOut;
        this.serviceTimeOut = serviceTimeOut;
        this.userId = userId;
    }

    public int getInvoiceId() {
        return invoiceId;
    }

    public void setInvoiceId(int invoiceId) {
        this.invoiceId = invoiceId;
    }

    public BigDecimal getAmountCollected() {
        return amountCollected;
    }

    public void setAmountCollected(BigDecimal amountCollected) {
        this.amountCollected = amountCollected;
    }

    public Date getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
    }

    public Time getCreationTime() {
        return creationTime;
    }

    public void setCreationTime(Time creationTime) {
        this.creationTime = creationTime;
    }

    public boolean isDeleted() {
        return isDeleted;
    }

    public void setDeleted(boolean deleted) {
        isDeleted = deleted;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public BigDecimal getServiceCharge() {
        return serviceCharge;
    }

    public void setServiceCharge(BigDecimal serviceCharge) {
        this.serviceCharge = serviceCharge;
    }

    public Date getServiceDateIn() {
        return serviceDateIn;
    }

    public void setServiceDateIn(Date serviceDateIn) {
        this.serviceDateIn = serviceDateIn;
    }

    public Time getServiceTimeIn() {
        return serviceTimeIn;
    }

    public void setServiceTimeIn(Time serviceTimeIn) {
        this.serviceTimeIn = serviceTimeIn;
    }

    public Date getServiceDateOut() {
        return serviceDateOut;
    }

    public void setServiceDateOut(Date serviceDateOut) {
        this.serviceDateOut = serviceDateOut;
    }

    public Time getServiceTimeOut() {
        return serviceTimeOut;
    }

    public void setServiceTimeOut(Time serviceTimeOut) {
        this.serviceTimeOut = serviceTimeOut;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }
}
