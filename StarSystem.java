package com.company.model;

import java.util.List;

public class StarSystem {

    private final String name;
    private final List<Star> stars;

    public StarSystem(String name, List<Star> stars) {
        this.name = name;
        this.stars = stars;
    }

    public String getName() {
        return name;
    }

    public List<Star> getStars() {
        return stars;
    }

}
