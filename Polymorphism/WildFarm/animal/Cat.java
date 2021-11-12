package WildFarm.animal;

import WildFarm.animal.Felime;

import java.text.DecimalFormat;

public class Cat extends Felime {
    private String breed;

    public Cat(String animalType, String animalName, Double animalWeight, String livingRegion, String breed) {
        super(animalType, animalName, animalWeight, livingRegion);
        this.breed = breed;
    }

    @Override
    public void makeSound() {
        System.out.println("Meowwww");
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(String.format("%s[%s, %s, %s, %s, %d]", super.getAnimalType(), super.getAnimalName(), this.breed,
                new DecimalFormat("#.##").format(super.getAnimalWeight()), super.getLivingRegion(), super.getFoodEaten()));
        return sb.toString();
    }
}
