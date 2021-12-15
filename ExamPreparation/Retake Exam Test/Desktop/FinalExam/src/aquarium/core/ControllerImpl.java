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

import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.Map;

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
        Aquarium aquarium;
        switch (aquariumType) {
            case "FreshwaterAquarium":
                aquarium = new FreshwaterAquarium(aquariumName);
                break;
            case "SaltwaterAquarium":
                aquarium = new SaltwaterAquarium(aquariumName);
                break;
            default:
                throw new NullPointerException(INVALID_AQUARIUM_TYPE);
        }
        aquariums.put(aquariumName, aquarium);

        return String.format(SUCCESSFULLY_ADDED_AQUARIUM_TYPE,aquariumType );
    }

    @Override
    public String addDecoration(String type) {
        Decoration decoration;
        switch (type) {
            case "Ornament":
                decoration = new Ornament();
                break;
            case "Plant":
                decoration = new Plant();
                break;
            default:
                throw new IllegalArgumentException(INVALID_DECORATION_TYPE);
        }
        decorations.add(decoration);

        return String.format(SUCCESSFULLY_ADDED_DECORATION_TYPE, type);
    }

    @Override
    public String insertDecoration(String aquariumName, String decorationType) {
        Decoration decoration = decorations.findByType(decorationType);
        if (decoration == null) {
            throw new IllegalArgumentException(String.format(NO_DECORATION_FOUND, decorationType));
        }
        Aquarium aquarium = aquariums.get(aquariumName);

        aquarium.addDecoration(decoration);

        decorations.remove(decoration);

        return String.format(SUCCESSFULLY_ADDED_DECORATION_IN_AQUARIUM, decorationType, aquariumName);
    }

    @Override
    public String addFish(String aquariumName, String fishType, String fishName, String fishSpecies,
                          double price) {

        Aquarium aquarium = aquariums.get(aquariumName);

        Fish fish;

        switch (fishType) {
            case "FreshwaterFish":
                fish = new FreshwaterFish(fishName, fishSpecies, price);
                if (aquarium.getClass().getSimpleName().equals("SaltwaterAquarium")) {
                return WATER_NOT_SUITABLE;
            }
            break;
            case "SaltwaterFish":
                if (aquarium.getClass().getSimpleName().equals("FreshwaterAquarium")) {
                    return WATER_NOT_SUITABLE;
                }
                fish = new SaltwaterFish(fishName, fishSpecies, price);
                break;
            default:
                throw new IllegalArgumentException(INVALID_FISH_TYPE);
        }
        if (aquarium.getFish().size() == aquarium.getCapacity()){
            return NOT_ENOUGH_CAPACITY;
        }
        aquarium.addFish(fish);

        return String.format(SUCCESSFULLY_ADDED_FISH_IN_AQUARIUM, fishType, aquariumName);
    }

    @Override
    public String feedFish(String aquariumName) {
        Aquarium aquarium = aquariums.get(aquariumName);
        aquarium.feed();

        return String.format(FISH_FED, aquarium.getFish().size());
    }

    @Override
    public String calculateValue(String aquariumName) {
        Aquarium aquarium = aquariums.get(aquariumName);

        double totalPrice = aquarium.getDecorations().stream().mapToDouble(Decoration::getPrice).sum()
                + aquarium.getFish().stream().mapToDouble(Fish::getPrice).sum();

        return String.format(VALUE_AQUARIUM, aquariumName, totalPrice);
    }

    @Override
    public String report() {
       StringBuilder sb = new StringBuilder();
        for (Aquarium aquarium : aquariums.values()) {
            sb.append(aquarium.getInfo());
        }
        return sb.toString().trim();
    }
}
