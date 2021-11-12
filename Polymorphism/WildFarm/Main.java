package WildFarm;

import WildFarm.animal.*;
import WildFarm.food.Food;
import WildFarm.food.Meat;
import WildFarm.food.Vegetable;

import java.util.*;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        String input = scanner.nextLine();

        List<Animal> animals = new ArrayList<>();

        while (!input.equals("End")) {
            String[] infoAnimal = input.split("\\s+");

            Animal animal = createAnimal(infoAnimal);

            String[] infoFood = scanner.nextLine().split("\\s+");

            Food food = createFood(infoFood);

            animal.makeSound();

            try {
                animal.eat(food);
            }catch (IllegalArgumentException e){
                System.out.println(e.getMessage());
            }

            animals.add(animal);
            input = scanner.nextLine();

        }

        animals.forEach(System.out::println);
    }

    private static Food createFood(String[] infoFood) {
        int quantity = Integer.parseInt(infoFood[1]);
        return infoFood[0].equals("Meat")
                ? new Meat(quantity)
                : new Vegetable(quantity);
    }

    private static Animal createAnimal(String[] token) {
        switch (token[0]){
            case "Cat":
                return new Cat(token[0], token[1], Double.parseDouble(token[2]), token[3], token[4]);
            case "Zebra":
                return new Zebra(token[0], token[1], Double.parseDouble(token[2]), token[3]);
            case "Tiger":
                return new Tiger(token[0], token[1], Double.parseDouble(token[2]), token[3]);
            case "Mouse":
                return new Mouse(token[0], token[1], Double.parseDouble(token[2]), token[3]);
            default:
                throw new IllegalArgumentException("Unknown animal type " + token[0]);
        }
    }
}
