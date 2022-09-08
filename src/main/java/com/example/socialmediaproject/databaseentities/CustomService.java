package com.example.socialmediaproject.databaseentities;

import java.math.BigDecimal;

public class CustomService {

    private int customServiceId;
    private String description;
    private int estimatedCompletionTime;
    private BigDecimal estimatedCost;
    private int invoiceId;
    private boolean isDeleted;
    private String name;
    private int serviceId;

    public CustomService(int customServiceId, String description, int estimatedCompletionTime, BigDecimal estimatedCost,
                         int invoiceId, boolean isDeleted, String name, int serviceId) {
        this.customServiceId = customServiceId;
        this.description = description;
        this.estimatedCompletionTime = estimatedCompletionTime;
        this.estimatedCost = estimatedCost;
        this.invoiceId = invoiceId;
        this.isDeleted = isDeleted;
        this.name = name;
        this.serviceId = serviceId;
    }

    public int getCustomServiceId() {
        return customServiceId;
    }

    public void setCustomServiceId(int customServiceId) {
        this.customServiceId = customServiceId;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getEstimatedCompletionTime() {
        return estimatedCompletionTime;
    }

    public void setEstimatedCompletionTime(int estimatedCompletionTime) {
        this.estimatedCompletionTime = estimatedCompletionTime;
    }

    public BigDecimal getEstimatedCost() {
        return estimatedCost;
    }

    public void setEstimatedCost(BigDecimal estimatedCost) {
        this.estimatedCost = estimatedCost;
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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getServiceId() {
        return serviceId;
    }

    public void setServiceId(int serviceId) {
        this.serviceId = serviceId;
    }
}
