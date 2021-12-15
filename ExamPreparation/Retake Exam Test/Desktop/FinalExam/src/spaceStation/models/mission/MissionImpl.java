package spaceStation.models.mission;

import spaceStation.models.astronauts.Astronaut;
import spaceStation.models.planets.Planet;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

public class MissionImpl implements Mission {

    @Override
    public void explore(Planet planet, List<Astronaut> astronauts) {
        List<Astronaut> astronautsBreath = astronauts.stream()
                .filter(Astronaut::canBreath)
                .collect(Collectors.toList());
        List<String> items = planet.getItems();
        while (astronautsBreath.stream().anyMatch(Astronaut::canBreath) && !items.isEmpty()) {
            for (int j = 0; j < astronautsBreath.size(); j++) {
                Astronaut astronaut = astronautsBreath.get(j);
                if (astronaut.canBreath()) {
                    for (int i = 0; i < items.size(); i++) {
                        String item = items.get(i);
                        astronaut.getBag().getItems().add(item);
                        astronaut.breath();
                        items.remove(i);
                        i--;
                        if (!astronaut.canBreath()) {
                            break;
                        }
                    }
                }
            }
        }
    }
}
