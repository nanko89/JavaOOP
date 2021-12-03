package bakery.repositories.interfaces;

public interface FoodRepository<Food> extends Repository<Food> {
    Food getByName(String name);
}
