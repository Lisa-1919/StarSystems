package com.company.model;

public class CelestialBody {

    private final String name;
    private final double weight;

    public CelestialBody(String name, double weight) {
        this.name = name;
        this.weight = weight;
    }

    public String getName() {
        return name;
    }

    public double getWeight() {
        return weight;
    }
}
