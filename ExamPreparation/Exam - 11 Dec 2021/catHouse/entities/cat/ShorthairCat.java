package catHouse.entities.cat;

public class ShorthairCat extends BaseCat{
    private static final int INITIAL_KILOGRAM = 7;
    private static final int INCREASE_KILOGRAM = 1;

    public ShorthairCat(String name, String breed, double price) {
        super(name, breed, price);
        setKilograms(INITIAL_KILOGRAM);
    }

    @Override
    public void eating() {
        setKilograms(getKilograms() + INCREASE_KILOGRAM);
    }
}
