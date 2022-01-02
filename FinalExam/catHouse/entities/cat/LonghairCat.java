package catHouse.entities.cat;

public class LonghairCat extends BaseCat{
    private static final int INITIAL_KILOGRAM = 9;
    private static final int INCREASE_KILOGRAM = 3;

    public LonghairCat(String name, String breed, double price) {
        super(name, breed, price);
    }

    @Override
    public void eating() {
        setKilograms(getKilograms() + INCREASE_KILOGRAM);
    }
}
