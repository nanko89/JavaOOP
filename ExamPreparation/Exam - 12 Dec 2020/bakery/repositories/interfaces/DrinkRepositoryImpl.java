package bakery.repositories.interfaces;


import bakery.entities.drinks.interfaces.Drink;

import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.Map;

public class DrinkRepositoryImpl implements DrinkRepository<Drink> {
    private Map<String, Drink> drinks;

    public DrinkRepositoryImpl() {
        this.drinks = new LinkedHashMap<>();
    }

    @Override
    public Drink getByNameAndBrand(String drinkName, String drinkBrand) {
        Drink drink = drinks.get(drinkName);
        if (drink == null){
            return null;
        }else if (drink.getBrand().equals(drinkBrand)) {
            return drink;
        }
        return null;
    }

    @Override
    public Collection<Drink> getAll() {
        return this.drinks.values();
    }

    @Override
    public void add(Drink drink) {
        drinks.put(drink.getName(), drink);
    }
}
