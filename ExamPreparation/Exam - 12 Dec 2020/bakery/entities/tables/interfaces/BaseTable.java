package bakery.entities.tables.interfaces;

import bakery.entities.bakedFoods.interfaces.BakedFood;

import bakery.entities.drinks.interfaces.Drink;

import java.util.ArrayList;
import java.util.List;

import static bakery.common.ExceptionMessages.*;


public abstract class BaseTable implements Table{
    private List<BakedFood> foodOrders;
    private List<Drink> drinkOrders;
    private int tableNumber;
    private int capacity;
    private int numberOfPeople;
    private double pricePerPerson;
    private boolean isReserved;
    private double price;

    protected BaseTable(int tableNumber, int capacity, double pricePerPerson) {
        this.foodOrders = new ArrayList<>();
        this.drinkOrders = new ArrayList<>();
        this.tableNumber = tableNumber;
        this.setCapacity(capacity);
        this.pricePerPerson = pricePerPerson;
        this.isReserved = false;
        this.price = 0;
    }

    public void setCapacity(int capacity) {
        if (capacity <= 0){
            throw new IllegalArgumentException(INVALID_TABLE_CAPACITY);
        }
        this.capacity = capacity;
    }

    public void setNumberOfPeople(int numberOfPeople) {
        if (numberOfPeople <= 0){
            throw new IllegalArgumentException(INVALID_NUMBER_OF_PEOPLE);
        }
        this.numberOfPeople = numberOfPeople;
    }


    @Override
    public int getTableNumber() {
        return this.tableNumber;
    }

    @Override
    public int getCapacity() {
        return this.capacity;
    }

    @Override
    public int getNumberOfPeople() {
        return this.numberOfPeople;
    }

    @Override
    public double getPricePerPerson() {
        return this.pricePerPerson;
    }

    @Override
    public boolean isReserved() {
        return this.isReserved;
    }

    @Override
    public double getPrice() {
        return this.price;
    }

    @Override
    public void reserve(int numberOfPeople) {
        setNumberOfPeople(numberOfPeople);
        this.isReserved = true;

    }

    @Override
    public void orderFood(BakedFood food) {
            this.foodOrders.add(food);
    }

    @Override
    public void orderDrink(Drink drink) {
            this.drinkOrders.add(drink);
    }

    @Override
    public double getBill() {
        double sum = pricePerPerson * numberOfPeople;
        sum += drinkOrders.stream().mapToDouble(Drink::getPrice).sum();
        sum += foodOrders.stream().mapToDouble(BakedFood::getPrice).sum();
        return sum;
    }

    @Override
    public void clear() {
        this.drinkOrders.clear();
        this.foodOrders.clear();
        this.isReserved = false;
    }

    @Override
    public String getFreeTableInfo() {
        return String.format("Table: %d%n" +
                        "Type: %s%n" +
                        "Capacity: %d%n" +
                        "Price per Person: %.2f%n", this.tableNumber, this.getClass().getSimpleName(),
                this.capacity, this.pricePerPerson);
    }
}
