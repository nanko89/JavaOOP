package spaceStation.repositories;

import spaceStation.models.astronauts.Astronaut;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

public class AstronautRepository implements Repository<Astronaut> {
    private List<Astronaut> astronauts;

    public AstronautRepository() {
        this.astronauts = new ArrayList<>();
    }

    @Override
    public Collection<Astronaut> getModels() {

        return this.astronauts;
    }

    @Override
    public void add(Astronaut model) {
        if (this.astronauts.stream().noneMatch(a -> a.getName().equals(model.getName()))) {
            astronauts.add(model);
        }
    }

    @Override
    public boolean remove(Astronaut model) {
        return this.astronauts.remove(model);
    }

    @Override
    public Astronaut findByName(String name) {
        return astronauts.stream().filter(a -> a.getName().equals(name)).findFirst().orElse(null);
//        for (Astronaut a : astronauts) {
//            if (a.getName().equals(name)) {
//                return a;
//
//            }
//        }
//        return null;
    }
}
