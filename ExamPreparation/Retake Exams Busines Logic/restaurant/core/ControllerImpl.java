package restaurant.core;

import restaurant.core.interfaces.Controller;
import restaurant.entities.drinks.Fresh;
import restaurant.entities.drinks.Smoothie;
import restaurant.entities.healthyFoods.Food;
import restaurant.entities.healthyFoods.Salad;
import restaurant.entities.healthyFoods.VeganBiscuits;
import restaurant.entities.healthyFoods.interfaces.HealthyFood;
import restaurant.entities.drinks.interfaces.Beverages;
import restaurant.entities.tables.InGarden;
import restaurant.entities.tables.Indoors;
import restaurant.entities.tables.interfaces.Table;
import restaurant.repositories.interfaces.*;

import static restaurant.common.ExceptionMessages.*;
import static restaurant.common.OutputMessages.*;

public class ControllerImpl implements Controller {
    private HealthFoodRepository<HealthyFood> healthFoodRepository;
    private BeverageRepository<Beverages> beverageRepository;
    private TableRepository<Table> tableRepository;
    private double totalSum;


    public ControllerImpl(HealthFoodRepository<HealthyFood> healthFoodRepository,
                          BeverageRepository<Beverages> beverageRepository,
                          TableRepository<Table> tableRepository) {
        this.tableRepository = tableRepository;
        this.beverageRepository = beverageRepository;
        this.healthFoodRepository = healthFoodRepository;
        this.totalSum = 0;

    }

    @Override
    public String addHealthyFood(String type, double price, String name) {

        Food food;
       if(type.equals("Salad")){
           food = new Salad(name,price);
       }else if (type.equals("VeganBiscuits")){
           food = new VeganBiscuits(name, price);
       }else {
           return null;
       }

        if (healthFoodRepository.foodByName(name) != null) {
            throw new IllegalArgumentException(String.format(FOOD_EXIST, name));
        }
            healthFoodRepository.add(food);
            return String.format(FOOD_ADDED, name);
    }

    @Override
    public String addBeverage(String type, int counter, String brand, String name) {
       Beverages beverages;

        if ("Fresh".equals(type)) {
            beverages = new Fresh(name,counter,brand);
        } else if ("Smoothie".equals(type)) {
            beverages = new Smoothie(name, counter, brand);
        }else {
            return null;
        }

        if (beverageRepository.beverageByName(name,brand) != null){
            throw new IllegalArgumentException(String.format(BEVERAGE_EXIST, name));
        }

        beverageRepository.add(beverages);

        return String.format(BEVERAGE_ADDED, type,brand);
    }

    @Override
    public String addTable(String type, int tableNumber, int capacity) {
        Table table;

        if ("Indoors".equals(type)) {
            table = new Indoors(tableNumber,capacity);
        } else if ("InGarden".equals(type)) {
            table = new InGarden(tableNumber,capacity);
        }else {
            return null;
        }

        if (tableRepository.byNumber(tableNumber) != null){
            throw new IllegalArgumentException(String.format(TABLE_IS_ALREADY_ADDED, tableNumber));
        }

        tableRepository.add(table);

        return String.format(TABLE_ADDED, tableNumber);
    }

    @Override
    public String reserve(int numberOfPeople) {
        Table table = tableRepository.getAllEntities()
                .stream().filter(t -> !t.isReservedTable() && t.getSize() >= numberOfPeople)
                .findFirst()
                .orElse(null);

        if (table == null){
            return String.format(RESERVATION_NOT_POSSIBLE, numberOfPeople);
        }

        table.reserve(numberOfPeople);

        return String.format(TABLE_RESERVED, table.getTableNumber(), numberOfPeople);
    }

    @Override
    public String orderHealthyFood(int tableNumber, String healthyFoodName) {
        Table table = tableRepository.byNumber(tableNumber);
        if (table == null ){
            return String.format(WRONG_TABLE_NUMBER, tableNumber);
        }
        HealthyFood food = healthFoodRepository.foodByName(healthyFoodName);

        if (food == null){
            return String.format(NONE_EXISTENT_FOOD, healthyFoodName);
        }

        if (!table.isReservedTable()){
            return String.format(WRONG_TABLE_NUMBER, tableNumber);
        }

        table.orderHealthy(food);

        return String.format(FOOD_ORDER_SUCCESSFUL, healthyFoodName, tableNumber);
    }

    @Override
    public String orderBeverage(int tableNumber, String name, String brand) {
        Table table = tableRepository.byNumber(tableNumber);
        if (table == null ){
            return String.format(WRONG_TABLE_NUMBER, tableNumber);
        }
        Beverages beverages = beverageRepository.beverageByName(name,brand);

        if (beverages == null){
            return String.format(NON_EXISTENT_DRINK, name,brand);
        }

        if (!table.isReservedTable()){
            return String.format(WRONG_TABLE_NUMBER, tableNumber);

        }
        table.orderBeverages(beverages);

        return String.format(BEVERAGE_ORDER_SUCCESSFUL, name, tableNumber);
    }

    @Override
    public String closedBill(int tableNumber) {
        Table table = tableRepository.byNumber(tableNumber);
        double bill = table.bill();
        this.totalSum += bill;
        table.clear();
        return String.format(BILL, tableNumber, bill);
    }


    @Override
    public String totalMoney() {
        return String.format(TOTAL_MONEY, totalSum);
    }
}
