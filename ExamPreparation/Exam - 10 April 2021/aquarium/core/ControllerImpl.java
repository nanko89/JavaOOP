package aquarium.core;

import aquarium.entities.aquariums.Aquarium;
import aquarium.entities.aquariums.FreshwaterAquarium;
import aquarium.entities.aquariums.SaltwaterAquarium;
import aquarium.entities.decorations.Decoration;
import aquarium.entities.decorations.Ornament;
import aquarium.entities.decorations.Plant;
import aquarium.entities.fish.Fish;
import aquarium.entities.fish.FreshwaterFish;
import aquarium.entities.fish.SaltwaterFish;
import aquarium.repositories.DecorationRepository;
import aquarium.repositories.Repository;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.stream.Collectors;

import static aquarium.common.ConstantMessages.*;
import static aquarium.common.ExceptionMessages.*;

public class ControllerImpl implements Controller {
    private Repository decorations;
    private Map<String, Aquarium> aquariums;


    public ControllerImpl() {
        this.decorations = new DecorationRepository();
        this.aquariums = new LinkedHashMap<>();
    }

    @Override
    public String addAquarium(String aquariumType, String aquariumName) {
        if (aquariumType.equals("FreshwaterAquarium")) {
            this.aquariums.put(aquariumName, new FreshwaterAquarium(aquariumName));
        } else if (aquariumType.equals("SaltwaterAquarium")) {
            this.aquariums.put(aquariumName, new SaltwaterAquarium(aquariumName));
        } else {
            throw new NullPointerException(INVALID_AQUARIUM_TYPE);
        }
        return String.format(SUCCESSFULLY_ADDED_AQUARIUM_TYPE, aquariumType);
    }

    @Override
    public String addDecoration(String type) {
        if (type.equals("Ornament")) {
            this.decorations.add(new Ornament());
        } else if (type.equals("Plant")) {
            this.decorations.add(new Plant());
        } else {
            throw new IllegalArgumentException(INVALID_DECORATION_TYPE);
        }
        return String.format(SUCCESSFULLY_ADDED_DECORATION_TYPE, type);
    }

    @Override
    public String insertDecoration(String aquariumName, String decorationType) {
        Decoration decorations = this.decorations.findByType(decorationType);
        if (decorations == null) {
            throw new IllegalArgumentException(String.format(NO_DECORATION_FOUND, decorationType));
        }
        this.decorations.remove(decorations);
        aquariums.get(aquariumName).addDecoration(decorations);
        return String.format(SUCCESSFULLY_ADDED_DECORATION_IN_AQUARIUM, decorationType, aquariumName);
    }

    @Override
    public String addFish(String aquariumName, String fishType, String fishName,
                          String fishSpecies, double price) {
        Fish fish;

        if (fishType.equals("FreshwaterFish")) {
            fish = new FreshwaterFish(fishName, fishSpecies, price);
        } else if (fishType.equals("SaltwaterFish")) {
            fish = new SaltwaterFish(fishName, fishSpecies, price);
        } else {
            throw new IllegalArgumentException(INVALID_FISH_TYPE);
        }

        try {
            Aquarium aquarium = aquariums.get(aquariumName);
            aquarium.addFish(fish);
        } catch (IllegalStateException e){
            e.getMessage();
        }


        return String.format(SUCCESSFULLY_ADDED_FISH_IN_AQUARIUM, fishType, aquariumName);
    }

    @Override
    public String feedFish(String aquariumName) {
        aquariums.get(aquariumName).feed();
        return String.format(FISH_FED, aquariums.get(aquariumName).getFish().size());
    }

    @Override
    public String calculateValue(String aquariumName) {
        double fishPrice = aquariums.get(aquariumName).getFish().stream().mapToDouble(Fish::getPrice).sum();
        double decorationPrice = aquariums.get(aquariumName).getDecorations().stream().mapToDouble(Decoration::getPrice).sum();
        return String.format(VALUE_AQUARIUM, aquariumName, decorationPrice + fishPrice);
    }

    @Override
    public String report() {
//        StringBuilder sb = new StringBuilder();
//        for (Aquarium aquarium : aquariums.values()) {
//            sb.append(aquarium.getInfo()).append(System.lineSeparator());
//        }
//        return sb.toString().trim();
        return aquariums.values()
                .stream()
                .map(Aquarium::getInfo)
                .collect(Collectors.joining(System.lineSeparator()));
    }
}
