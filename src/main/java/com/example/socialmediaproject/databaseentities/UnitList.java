package com.example.socialmediaproject.databaseentities;

public class UnitList {

    private int unitListId;
    private int chemicalId;
    private boolean isDeleted;
    private int unitId;

    public UnitList(int unitListId, int chemicalId, boolean isDeleted, int unitId) {
        this.unitListId = unitListId;
        this.chemicalId = chemicalId;
        this.isDeleted = isDeleted;
        this.unitId = unitId;
    }

    public int getUnitListId() {
        return unitListId;
    }

    public void setUnitListId(int unitListId) {
        this.unitListId = unitListId;
    }

    public int getChemicalId() {
        return chemicalId;
    }

    public void setChemicalId(int chemicalId) {
        this.chemicalId = chemicalId;
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
