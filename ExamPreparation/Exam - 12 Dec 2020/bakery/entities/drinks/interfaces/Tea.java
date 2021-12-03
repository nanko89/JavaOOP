package bakery.entities.drinks.interfaces;

public class Tea extends BaseDrink{
    private static final double PRISE = 2.50;

    public Tea(String name, int portion, String brand) {
        super(name, portion, PRISE, brand);
    }
}
