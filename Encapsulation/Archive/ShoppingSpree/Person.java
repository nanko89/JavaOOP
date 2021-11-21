package Archive.ShoppingSpree;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Person {

    private String name;
    private int money;
    private List<Product> bagOfProduct;

    public Person(String name, int money) {
        setName(name);
        setMoney(money);
        this.bagOfProduct = new ArrayList<>();
    }

    private void setName(String name) {
        if (name == null || name.trim().length() == 0) {
            throw new IllegalArgumentException("Name cannot be empty");
        }
        this.name = name;
    }

    private void setMoney(int money) {
        if (money < 0) {
            throw new IllegalArgumentException("Money cannot be negative");
        }
        this.money = money;
    }

    public void addProduct(Product product) {
        if (product.getCost() > this.money) {
            throw new IllegalArgumentException(this.name + " can't afford " + product.getName());
        }
            bagOfProduct.add(product);
            setMoney(money - product.getCost());
            System.out.println(this.name + " bought " + product.getName());

    }

    public String getName() {
        return name;
    }

    public List<Product> getBagOfProduct() {
        return bagOfProduct;
    }

    @Override
    public String toString() {
        if (bagOfProduct.isEmpty()){
            return name + " - Nothing bought";
        }
        return name + " - " +bagOfProduct.stream()
                .map(Product::getName)
                .collect(Collectors.joining(", "));
    }
}
