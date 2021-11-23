package spaceStation.repositories;

import spaceStation.models.planets.Planet;

import java.util.*;

public class PlanetRepository implements Repository<Planet> {
    private List<Planet> planets;


    public PlanetRepository() {
        this.planets = new ArrayList<>();
    }


    @Override
    public Collection<Planet> getModels() {
        return this.planets;
    }

    @Override
    public void add(Planet model) {
        planets.add(model);
    }

    @Override
    public boolean remove(Planet model) {
        return planets.remove(model);
    }

    @Override
    public Planet findByName(String name) {
        for (Planet p : planets) {
            if (p.getName().equals(name)) {
                return p;

            }
        }
        return null;
    }
}
