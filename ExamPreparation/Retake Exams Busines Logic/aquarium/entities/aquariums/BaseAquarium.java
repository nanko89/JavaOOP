package aquarium.entities.aquariums;

import aquarium.entities.decorations.Decoration;
import aquarium.entities.fish.Fish;

import java.util.ArrayList;
import java.util.Collection;
import java.util.stream.Collectors;

import static aquarium.common.ConstantMessages.NOT_ENOUGH_CAPACITY;
import static aquarium.common.ExceptionMessages.*;

public abstract class BaseAquarium implements Aquarium {
    private String name;
    private int capacity;
    private Collection<Decoration> decorations;
    private Collection<Fish> fish;

    protected BaseAquarium(String name, int capacity) {
        this.setName(name);
        this.capacity = capacity;
        this.decorations = new ArrayList<>();
        this.fish = new ArrayList<>();
    }

    public int getCapacity() {
        return capacity;
    }

    public void setName(String name) {
        if (name == null || name.trim().isEmpty()) {
            throw new NullPointerException(AQUARIUM_NAME_NULL_OR_EMPTY);
        }
        this.name = name;
    }

    @Override
    public int calculateComfort() {
        return decorations.stream().mapToInt(Decoration::getComfort).sum();
    }

    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public void addFish(Fish fish) {
        if (this.fish.size() == capacity) {
            throw new IllegalArgumentException(NOT_ENOUGH_CAPACITY);
        }
        this.fish.add(fish);
    }

    @Override
    public void removeFish(Fish fish) {
        this.fish.remove(fish);
    }

    @Override
    public void addDecoration(Decoration decoration) {
        this.decorations.add(decoration);
    }

    @Override
    public void feed() {
        for (Fish f : fish) {
            f.eat();
        }
    }

    @Override
    public String getInfo() {
        String infoFish = fish.isEmpty()
                ? "none"
                : fish.stream().map(Fish::getName).collect(Collectors.joining(" "));

        StringBuilder sb = new StringBuilder();

        sb.append(String.format("%s (%s):", this.name, this.getClass().getSimpleName()))
                .append(System.lineSeparator());
        sb.append(String.format("Fish: %s", infoFish)).append(System.lineSeparator());
        sb.append(String.format("Decorations: %d", decorations.size()))
                .append(System.lineSeparator());
        sb.append(String.format("Comfort: %d", calculateComfort())).append(System.lineSeparator());

        return sb.toString();
    }

    @Override
    public Collection<Fish> getFish() {
        return this.fish;
    }

    @Override
    public Collection<Decoration> getDecorations() {
        return this.decorations;
    }
}
