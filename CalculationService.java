package com.company;

import com.company.model.CelestialBody;
import com.company.model.StarSystem;
import com.company.model.StarSystemCalculationsModel;

public class CalculationService {

    public int getCelestialBodiesNumber(StarSystem starSystem) {
        int starsNumber = starSystem.getStars().size();
        int planetsNumber = starSystem.getStars().stream()
                .mapToInt(star -> star.getPlanets().size())
                .sum();
        int satellitesNumber = starSystem.getStars().stream()
                .flatMap(star -> star.getPlanets().stream())
                .mapToInt(planet -> planet.getSatellites().size())
                .sum();

        return starsNumber + planetsNumber + satellitesNumber;
    }

    public double getSystemWeight(StarSystem starSystem) {
        double starsWeight = starSystem.getStars().stream().mapToDouble(CelestialBody::getWeight).sum();
        double planetsWeight = starSystem.getStars().stream()
                .flatMap(star -> star.getPlanets().stream())
                .mapToDouble(CelestialBody::getWeight)
                .sum();
        double satellitesWeight = starSystem.getStars().stream()
                .flatMap(star -> star.getPlanets().stream())
                .flatMap(planet -> planet.getSatellites().stream())
                .mapToDouble(CelestialBody::getWeight)
                .sum();

        return starsWeight + planetsWeight + satellitesWeight;
    }

    public StarSystemCalculationsModel getCalculations(StarSystem starSystem) {
        return new StarSystemCalculationsModel(starSystem.getName(),
                getCelestialBodiesNumber(starSystem),
                getSystemWeight(starSystem));
    }
}
