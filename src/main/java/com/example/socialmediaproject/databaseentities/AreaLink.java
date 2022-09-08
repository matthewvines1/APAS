package com.example.socialmediaproject.databaseentities;

public class AreaLink {

    private int areaLinkId;
    private int areaId;
    private String customArea;
    private boolean isCustom;
    private boolean isDeleted;
    private int usedChemicalId;

    public AreaLink(int areaLinkId, int areaId, String customArea, boolean isCustom, boolean isDeleted, int usedChemicalId) {
        this.areaLinkId = areaLinkId;
        this.areaId = areaId;
        this.customArea = customArea;
        this.isCustom = isCustom;
        this.isDeleted = isDeleted;
        this.usedChemicalId = usedChemicalId;
    }

    public int getAreaLinkId() {
        return areaLinkId;
    }

    public void setAreaLinkId(int areaLinkId) {
        this.areaLinkId = areaLinkId;
    }

    public int getAreaId() {
        return areaId;
    }

    public void setAreaId(int areaId) {
        this.areaId = areaId;
    }

    public String getCustomArea() {
        return customArea;
    }

    public void setCustomArea(String customArea) {
        this.customArea = customArea;
    }

    public boolean isCustom() {
        return isCustom;
    }

    public void setCustom(boolean custom) {
        isCustom = custom;
    }

    public boolean isDeleted() {
        return isDeleted;
    }

    public void setDeleted(boolean deleted) {
        isDeleted = deleted;
    }

    public int getUsedChemicalId() {
        return usedChemicalId;
    }

    public void setUsedChemicalId(int usedChemicalId) {
        this.usedChemicalId = usedChemicalId;
    }
}
