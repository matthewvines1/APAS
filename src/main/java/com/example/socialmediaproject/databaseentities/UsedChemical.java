package com.example.socialmediaproject.databaseentities;

import java.math.BigDecimal;

public class UsedChemical {

    private int usedChemicalId;
    private BigDecimal amount;
    private int chemicalId;
    private int customServiceId;
    private boolean isDeleted;
    private int unitId;

    public UsedChemical(int usedChemicalId, BigDecimal amount, int chemicalId, int customServiceId, boolean isDeleted, int unitId) {
        this.usedChemicalId = usedChemicalId;
        this.amount = amount;
        this.chemicalId = chemicalId;
        this.customServiceId = customServiceId;
        this.isDeleted = isDeleted;
        this.unitId = unitId;
    }

    public int getUsedChemicalId() {
        return usedChemicalId;
    }

    public void setUsedChemicalId(int usedChemicalId) {
        this.usedChemicalId = usedChemicalId;
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

    public int getCustomServiceId() {
        return customServiceId;
    }

    public void setCustomServiceId(int customServiceId) {
        this.customServiceId = customServiceId;
    }

    public boolean isDeleted() {
        return isDeleted;
    }

    public void setDeleted(boolean deleted) {
        isDeleted = deleted;
    }

    public int getUnitId() {
        return unitId;
    }

    public void setUnitId(int unitId) {
        this.unitId = unitId;
    }
}
