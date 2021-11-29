package restaurant.entities.healthyFoods;

import restaurant.entities.healthyFoods.Food;

public class Salad extends Food {
    private static final double INITIAL_SALAD_PORTION = 150;

    public Salad(String name, double price) {
        super(name, INITIAL_SALAD_PORTION, price);
    }
}
