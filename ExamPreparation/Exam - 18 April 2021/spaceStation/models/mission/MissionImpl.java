package spaceStation.models.mission;

import spaceStation.models.astronauts.Astronaut;
import spaceStation.models.planets.Planet;

import java.util.List;

public class MissionImpl implements  Mission{
    @Override
    public void explore(Planet planet, List<Astronaut> astronauts) {
        for (int astronaut = 0; astronaut < astronauts.size(); astronaut++) {
            Astronaut currentAstronaut = astronauts.get(astronaut);
            for (int item = 0; item < planet.getItems().size(); item++) {
                String currentItem = planet.getItems().get(item);
                currentAstronaut.getBag().getItems().add(currentItem);
                planet.getItems().remove(currentItem);
                item--;
                currentAstronaut.breath();

                if (!currentAstronaut.canBreath()){
                    astronauts.remove(currentAstronaut);
                    astronaut--;
                    break;
                }
            }
        }
    }
}
