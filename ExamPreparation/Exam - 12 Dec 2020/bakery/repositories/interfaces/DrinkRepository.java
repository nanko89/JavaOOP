package bakery.repositories.interfaces;

import bakery.entities.drinks.interfaces.BaseDrink;

public interface DrinkRepository<Drink> extends Repository<Drink> {
    Drink getByNameAndBrand(String drinkName,String drinkBrand);
}
