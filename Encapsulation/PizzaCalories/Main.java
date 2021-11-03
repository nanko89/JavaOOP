package PizzaCalories;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        String[] pizzaInfo = scanner.nextLine().split(" ");

        try {
            Pizza pizza = new Pizza(pizzaInfo[1],Integer.parseInt(pizzaInfo[2]));

            String[] doughInfo = scanner.nextLine().split(" ");

            try {
                Dough dough = new Dough(doughInfo[1], doughInfo[2], Double.parseDouble(doughInfo[3]));
                pizza.setDough(dough);
            }catch (IllegalArgumentException e){
                System.out.println(e.getMessage());
                return;
            }

            String input = scanner.nextLine();

            while (!input.equals("END")){
                String[] toppingInfo = input.split(" ");
                try {
                Topping topping = new Topping(toppingInfo[1], Double.parseDouble(toppingInfo[2]));

                pizza.addTopping(topping);
                }catch (IllegalArgumentException e){
                    System.out.println(e.getMessage());
                    return;
                }

                input = scanner.nextLine();
            }
            System.out.printf("Meatless - %.2f",pizza.getOverallCalories());
        }catch (IllegalArgumentException e){
            System.out.println(e.getMessage());
        }

    }
}
