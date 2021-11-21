package Archive.PizzaCalories;

public class Topping {
    private String topping;
    private double weight;

    public Topping(String topping, double weight) {
       setTopping(topping);
       setWeight(weight);

    }

    private void setWeight(double weight) {
        if (weight <= 0 || weight > 50) {
            throw new IllegalArgumentException(topping + " weight should be in the range [1..50].");
        }
        this.weight = weight;
    }

    public void setTopping(String topping) {

        if (!topping.equals("Meat") && !topping.equals("Sauce")
                && !topping.equals("Cheese") && !topping.equals("Veggies")) {
            throw new IllegalArgumentException("Cannot place " + topping + " on top of your pizza.");
        }
        this.topping = topping;
    }

    public double calculatesCalorie() {
        double modifier = 0;
        switch (topping) {
            case "Meat":
                modifier = 1.2;
                break;
            case "Veggies":
                modifier = 0.8;
                break;
            case "Cheese":
                modifier = 1.1;
                break;
            case "Sauce":
                modifier = 0.9;
                break;
        }
        return 2 * weight * modifier;
    }
}
