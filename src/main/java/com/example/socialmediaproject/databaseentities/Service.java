package com.example.socialmediaproject.databaseentities;

import java.math.BigDecimal;

public class Service {

    private int serviceId;
    private String description;
    private boolean isActive;
    private String name;
    private int standardCompletionTime;
    private BigDecimal standardCost;

    public Service(int serviceId, String description, boolean isActive, String name, int standardCompletionTime, BigDecimal standardCost) {
        this.serviceId = serviceId;
        this.description = description;
        this.isActive = isActive;
        this.name = name;
        this.standardCompletionTime = standardCompletionTime;
        this.standardCost = standardCost;
    }

    public int getServiceId() {
        return serviceId;
    }

    public void setServiceId(int serviceId) {
        this.serviceId = serviceId;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        isActive = active;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getStandardCompletionTime() {
        return standardCompletionTime;
    }

    public void setStandardCompletionTime(int standardCompletionTime) {
        this.standardCompletionTime = standardCompletionTime;
    }

    public BigDecimal getStandardCost() {
        return standardCost;
    }

    public void setStandardCost(BigDecimal standardCost) {
        this.standardCost = standardCost;
    }
}
