package com.example.socialmediaproject.databaseentities;

public class Unit {

    private int unitId;
    private String abbreviation;
    private boolean isFluid;
    private String name;

    public Unit(int unitId, String abbreviation, boolean isFluid, String name) {
        this.unitId = unitId;
        this.abbreviation = abbreviation;
        this.isFluid = isFluid;
        this.name = name;
    }

    public int getUnitId() {
        return unitId;
    }

    public void setUnitId(int unitId) {
        this.unitId = unitId;
    }

    public String getAbbreviation() {
        return abbreviation;
    }

    public void setAbbreviation(String abbreviation) {
        this.abbreviation = abbreviation;
    }

    public boolean isFluid() {
        return isFluid;
    }

    public void setFluid(boolean fluid) {
        isFluid = fluid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
