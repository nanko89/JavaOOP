package bakery.repositories.interfaces;

import bakery.entities.bakedFoods.interfaces.BakedFood;

import java.util.*;

public class FoodRepositoryImpl implements FoodRepository<BakedFood>{
    private Map<String, BakedFood> foods;

    public FoodRepositoryImpl() {
        this.foods = new LinkedHashMap<>();
    }

    @Override
    public BakedFood getByName(String name) {
        return this.foods.get(name);
    }

    @Override
    public Collection<BakedFood> getAll() {
        return this.foods.values();
    }

    @Override
    public void add(BakedFood bakedFood) {
        this.foods.putIfAbsent(bakedFood.getName(),bakedFood);
    }
}
