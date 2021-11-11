package Animals;

public class Dog extends Animal{

    public Dog(String name, String favouriteFood) {
        super(name, favouriteFood);
    }

    @Override
    protected String explainSelf() {
        return String.format("I am %s and my favourite food is %s DJAAF",
                super.getName(), super.getFavouriteFood());
    }
}
