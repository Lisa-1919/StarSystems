package com.company.model;

import java.util.List;

public class Planet extends CelestialBody {

    private final List<Satellite> satellites;

    public Planet(String name, Double weight, List<Satellite> satellites) {
        super(name, weight);
        this.satellites = satellites;
    }

    public List<Satellite> getSatellites() {
        return satellites;
    }

}