package Archive.ShoppingSpree;

import java.util.*;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String[] peopleInput = scanner.nextLine().split(";");
        String[] productsInput = scanner.nextLine().split(";");

        Map<String, Person> personList = new LinkedHashMap<>();
        Map<String, Product> productList = new LinkedHashMap<>();

        for (String p : peopleInput) {
            try {
                String name = p.split("=")[0];
                int money = Integer.parseInt(p.split("=")[1]);
                Person person = new Person(name, money);
                personList.put(name, person);
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
                return;
            }
        }


        for (String p : productsInput) {
            try {
                String name = p.split("=")[0];
                int cost = Integer.parseInt(p.split("=")[1]);
                Product product = new Product(name, cost);
                productList.put(name, product);
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
                return;
            }
        }

        String input = scanner.nextLine();

        while (!input.equals("END")) {

            try {
            Person person = personList.get(input.split(" ")[0]);
            Product product = productList.get(input.split(" ")[1]);
            person.addProduct(product);
            }catch (IllegalArgumentException e){
                System.out.println(e.getMessage());
            }

            input = scanner.nextLine();

        }
        for (Person person : personList.values()) {
            System.out.println(person);
        }
    }
}
