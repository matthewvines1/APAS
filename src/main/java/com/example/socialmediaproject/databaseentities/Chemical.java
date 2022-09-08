package com.example.socialmediaproject.databaseentities;

public class Chemical {

    private int chemicalId;
    private String description;
    private String name;
    private String referenceUrl;

    public Chemical(int chemicalId, String description, String name, String referenceUrl) {
        this.chemicalId = chemicalId;
        this.description = description;
        this.name = name;
        this.referenceUrl = referenceUrl;
    }

    public int getChemicalId() {
        return chemicalId;
    }

    public void setChemicalId(int chemicalId) {
        this.chemicalId = chemicalId;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getReferenceUrl() {
        return referenceUrl;
    }

    public void setReferenceUrl(String referenceUrl) {
        this.referenceUrl = referenceUrl;
    }
}
