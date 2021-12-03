package bakery.entities.drinks.interfaces;

public class Water extends BaseDrink{
    private static final double PRISE = 1.50;

    public Water(String name, int portion, String brand) {
        super(name, portion, PRISE, brand);
    }
}
