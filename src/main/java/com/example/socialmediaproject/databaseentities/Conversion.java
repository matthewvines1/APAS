package com.example.socialmediaproject.databaseentities;

import java.math.BigDecimal;

public class Conversion {

    private int conversionId;
    private BigDecimal conversionRatio;
    private int conversionToUnit;
    private boolean isDeleted;
    private int mainUnit;

    public Conversion(int conversionId, BigDecimal conversionRatio, int conversionToUnit, boolean isDeleted, int mainUnit) {
        this.conversionId = conversionId;
        this.conversionRatio = conversionRatio;
        this.conversionToUnit = conversionToUnit;
        this.isDeleted = isDeleted;
        this.mainUnit = mainUnit;
    }

    public int getConversionId() {
        return conversionId;
    }

    public void setConversionId(int conversionId) {
        this.conversionId = conversionId;
    }

    public BigDecimal getConversionRatio() {
        return conversionRatio;
    }

    public void setConversionRatio(BigDecimal conversionRatio) {
        this.conversionRatio = conversionRatio;
    }

    public int getConversionToUnit() {
        return conversionToUnit;
    }

    public void setConversionToUnit(int conversionToUnit) {
        this.conversionToUnit = conversionToUnit;
    }

    public boolean isDeleted() {
        return isDeleted;
    }

    public void setDeleted(boolean deleted) {
        isDeleted = deleted;
    }

    public int getMainUnit() {
        return mainUnit;
    }

    public void setMainUnit(int mainUnit) {
        this.mainUnit = mainUnit;
    }
}
