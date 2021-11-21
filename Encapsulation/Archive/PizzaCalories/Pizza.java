package Archive.PizzaCalories;

import java.util.ArrayList;
import java.util.List;

public class Pizza {
    private String name;
    private Dough dough;
    private List<Topping> toppingList;

    public Pizza(String name, int numberOfTopping) {
        setName(name);
        setToppingList(numberOfTopping);
    }

    public void setDough(Dough dough) {
        this.dough = dough;
    }

    public void setToppingList(int numberOfTopping) {
        if (numberOfTopping < 0 || numberOfTopping > 10) {
            throw new IllegalArgumentException("Number of toppings should be in range [0..10].");
        }
        this.toppingList = new ArrayList<>(numberOfTopping);
    }

    public void setName(String name) {
        if (name.trim().length() <= 0 || name.trim().length() > 15) {
            throw new IllegalArgumentException("Pizza name should be between 1 and 15 symbols.");
        }
        this.name = name;
    }

    public String getName() {
        return name;
    }
    public void addTopping(Topping topping){
        toppingList.add(topping);
    }

    public double totalCalories(){
        return dough.calculateCalories() + toppingList.stream().mapToDouble(Topping::calculatesCalorie).sum();
    }

}
