package com.company.model;

public class StarSystemCalculationsModel {

    private final String systemName;
    private final int bodiesNumber;
    private final double bodiesWeight;

    public StarSystemCalculationsModel(String systemName, int bodiesNumber, double bodiesWeight) {
        this.systemName = systemName;
        this.bodiesNumber = bodiesNumber;
        this.bodiesWeight = bodiesWeight;
    }

    public String getSystemName() {
        return systemName;
    }

    public int getBodiesNumber() {
        return bodiesNumber;
    }

    public double getBodiesWeight() {
        return bodiesWeight;
    }
}
