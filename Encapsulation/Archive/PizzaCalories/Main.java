package Archive.PizzaCalories;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        String[] pizzaData = scanner.nextLine().split("\\s+");

        String name = pizzaData[1];
        int toppings = Integer.parseInt(pizzaData[2]);

        String[] doughData = scanner.nextLine().split("\\s+");

        String flourType = doughData[1];
        String bakingTechnique = doughData[2];
        double weight = Double.parseDouble(doughData[3]);
        try {
            Pizza pizza = new Pizza(name, toppings);

            Dough dough = new Dough(flourType, bakingTechnique, weight);

            pizza.setDough(dough);

            String input = scanner.nextLine();

            while (!input.equals("END")) {
                String nameOfTopping = input.split("\\s+")[1];
                double weightOfTopping = Double.parseDouble(input.split("\\s+")[2]);

                Topping topping = new Topping(nameOfTopping, weightOfTopping);
                pizza.addTopping(topping);
                input = scanner.nextLine();

            }
                System.out.printf("%s - %.2f", pizza.getName(),pizza.totalCalories());
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }
    }
}
