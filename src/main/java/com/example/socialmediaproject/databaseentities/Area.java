package com.example.socialmediaproject.databaseentities;

public class Area {

    private int areaId;
    private boolean isDeleted;
    private String name;

    public Area(int areaId, boolean isDeleted, String name) {
        this.areaId = areaId;
        this.isDeleted = isDeleted;
        this.name = name;
    }

    public int getAreaId() {
        return areaId;
    }

    public void setAreaId(int areaId) {
        this.areaId = areaId;
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
}
