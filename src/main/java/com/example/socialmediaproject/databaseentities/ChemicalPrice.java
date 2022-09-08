package com.example.socialmediaproject.databaseentities;

import java.math.BigDecimal;
import java.sql.Date;
import java.sql.Time;

public class ChemicalPrice {

    private int chemicalPriceId;
    private BigDecimal amount;
    private int chemicalId;
    private Date creationDate;
    private Time creationTime;
    private Date effectiveDate;
    private Time effectiveTime;
    private boolean isDeleted;
    private BigDecimal price;
    private int unitId;

    public ChemicalPrice(int chemicalPriceId, BigDecimal amount, int chemicalId, Date creationDate, Time creationTime,
                         Date effectiveDate, Time effectiveTime, boolean isDeleted, BigDecimal price, int unitId) {
        this.chemicalPriceId = chemicalPriceId;
        this.amount = amount;
        this.chemicalId = chemicalId;
        this.creationDate = creationDate;
        this.creationTime = creationTime;
        this.effectiveDate = effectiveDate;
        this.effectiveTime = effectiveTime;
        this.isDeleted = isDeleted;
        this.price = price;
        this.unitId = unitId;
    }

    public int getChemicalPriceId() {
        return chemicalPriceId;
    }

    public void setChemicalPriceId(int chemicalPriceId) {
        this.chemicalPriceId = chemicalPriceId;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public int getChemicalId() {
        return chemicalId;
    }

    public void setChemicalId(int chemicalId) {
        this.chemicalId = chemicalId;
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

    public Date getEffectiveDate() {
        return effectiveDate;
    }

    public void setEffectiveDate(Date effectiveDate) {
        this.effectiveDate = effectiveDate;
    }

    public Time getEffectiveTime() {
        return effectiveTime;
    }

    public void setEffectiveTime(Time effectiveTime) {
        this.effectiveTime = effectiveTime;
    }

    public boolean isDeleted() {
        return isDeleted;
    }

    public void setDeleted(boolean deleted) {
        isDeleted = deleted;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public int getUnitId() {
        return unitId;
    }

    public void setUnitId(int unitId) {
        this.unitId = unitId;
    }
}
