package com.company.model;

import java.util.List;

public class Star extends CelestialBody {

    private final List<Planet> planets;

    public Star(String name, Double weight, List<Planet> planets) {
        super(name, weight);
        this.planets = planets;
    }

    public List<Planet> getPlanets() {
        return planets;
    }
}
