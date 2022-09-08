package com.example.socialmediaproject.databaseentities;

public class InvoiceAddressLink {

    private int invoiceAddressLinkId;
    private int contactAddressLinkId;
    private int invoiceId;
    private boolean isDeleted;

    public InvoiceAddressLink(int invoiceAddressLinkId, int contactAddressLinkId, int invoiceId, boolean isDeleted) {
        this.invoiceAddressLinkId = invoiceAddressLinkId;
        this.contactAddressLinkId = contactAddressLinkId;
        this.invoiceId = invoiceId;
        this.isDeleted = isDeleted;
    }

    public int getInvoiceAddressLinkId() {
        return invoiceAddressLinkId;
    }

    public void setInvoiceAddressLinkId(int invoiceAddressLinkId) {
        this.invoiceAddressLinkId = invoiceAddressLinkId;
    }

    public int getContactAddressLinkId() {
        return contactAddressLinkId;
    }

    public void setContactAddressLinkId(int contactAddressLinkId) {
        this.contactAddressLinkId = contactAddressLinkId;
    }

    public int getInvoiceId() {
        return invoiceId;
    }

    public void setInvoiceId(int invoiceId) {
        this.invoiceId = invoiceId;
    }

    public boolean isDeleted() {
        return isDeleted;
    }

    public void setDeleted(boolean deleted) {
        isDeleted = deleted;
    }
}
