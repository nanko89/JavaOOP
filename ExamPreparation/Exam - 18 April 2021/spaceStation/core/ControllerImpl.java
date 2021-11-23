package spaceStation.core;

import spaceStation.models.astronauts.Astronaut;
import spaceStation.models.astronauts.Biologist;
import spaceStation.models.astronauts.Geodesist;
import spaceStation.models.astronauts.Meteorologist;
import spaceStation.models.mission.Mission;
import spaceStation.models.mission.MissionImpl;
import spaceStation.models.planets.Planet;
import spaceStation.models.planets.PlanetImpl;
import spaceStation.repositories.AstronautRepository;
import spaceStation.repositories.PlanetRepository;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import static spaceStation.common.ConstantMessages.*;
import static spaceStation.common.ExceptionMessages.*;

public class ControllerImpl implements Controller {
    private final AstronautRepository astronautRepository;
    private final PlanetRepository planetRepository;
    private  int planetExplored;

    public ControllerImpl() {
        this.astronautRepository = new AstronautRepository();
        this.planetRepository = new PlanetRepository();
    }

    @Override
    public String addAstronaut(String type, String astronautName) {
        Astronaut astronaut;
        if (type.equals("Biologist")) {
            astronaut = new Biologist(astronautName);
        } else if (type.equals("Geodesist")) {
            astronaut = new Geodesist(astronautName);
        } else if (type.equals("Meteorologist")) {
            astronaut = new Meteorologist(astronautName);
        } else {
            throw new IllegalArgumentException(ASTRONAUT_INVALID_TYPE);
        }
        this.astronautRepository.getModels().add(astronaut);

        return String.format(ASTRONAUT_ADDED, type, astronautName);
    }

    @Override
    public String addPlanet(String planetName, String... items) {
        String[] list = items;
        Planet planet = new PlanetImpl(planetName);
        for (String s : list) {
            planet.getItems().add(s);
        }

        planetRepository.add(planet);
        return String.format(PLANET_ADDED, planetName);
    }

    @Override
    public String retireAstronaut(String astronautName) {
        if (this.astronautRepository.getModels().stream().noneMatch(a -> a.getName().equals(astronautName))){
            throw new IllegalArgumentException(String.format(ASTRONAUT_DOES_NOT_EXIST, astronautName));
        }
        Astronaut byName = astronautRepository.findByName(astronautName);
        this.astronautRepository.remove(byName);
        return String.format(ASTRONAUT_RETIRED, astronautName);
    }

    @Override
    public String explorePlanet(String planetName) {
        Planet planet = planetRepository.findByName(planetName);
        List<Astronaut> astronautList = astronautRepository.getModels().stream()
                .filter(a -> a.getOxygen() > 60)
                .collect(Collectors.toList());

        if (astronautList.isEmpty()) {
            throw new IllegalArgumentException(PLANET_ASTRONAUTS_DOES_NOT_EXISTS);
        }
        int size = astronautList.size();

        Mission mission = new MissionImpl();
        mission.explore(planet, astronautList);
        planetExplored++;
        return String.format(PLANET_EXPLORED, planetName, size - astronautList.size());
    }

    @Override
    public String report() {
        StringBuilder sb = new StringBuilder();
        sb.append(String.format(REPORT_PLANET_EXPLORED, planetExplored))
                .append(System.lineSeparator());
        sb.append(REPORT_ASTRONAUT_INFO)
                .append(System.lineSeparator());
        for (Astronaut model : astronautRepository.getModels()) {
            String item = model.getBag().getItems().isEmpty()
                    ? String.format(REPORT_ASTRONAUT_BAG_ITEMS , "none")
                    : String.format(REPORT_ASTRONAUT_BAG_ITEMS, model.getBag().getItems()
                    .stream().map(String::new)
                    .collect(Collectors.joining(REPORT_ASTRONAUT_BAG_ITEMS_DELIMITER)));

            sb.append(String.format(REPORT_ASTRONAUT_NAME, model.getName()))
                    .append(System.lineSeparator());
            sb.append(String.format(REPORT_ASTRONAUT_OXYGEN, model.getOxygen()))
                    .append(System.lineSeparator());
            sb.append(item).append(System.lineSeparator());
        }
        return sb.toString().trim();
    }
}
