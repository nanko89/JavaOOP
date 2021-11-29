package restaurant.core;

import restaurant.common.enums.BeveragesType;
import restaurant.common.enums.HealthyFoodType;
import restaurant.common.enums.TableType;
import restaurant.core.interfaces.Controller;
import restaurant.entities.drinks.Fresh;
import restaurant.entities.drinks.Smoothie;
import restaurant.entities.healthyFoods.interfaces.HealthyFood;
import restaurant.entities.drinks.interfaces.Beverages;
import restaurant.entities.healthyFoods.Salad;
import restaurant.entities.healthyFoods.VeganBiscuits;
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
    private double totalMoney;


    public ControllerImpl(HealthFoodRepository<HealthyFood> healthFoodRepository,
                          BeverageRepository<Beverages> beverageRepository,
                          TableRepository<Table> tableRepository) {
        this.healthFoodRepository = healthFoodRepository;
        this.beverageRepository = beverageRepository;
        this.tableRepository = tableRepository;
    }

    @Override
    public String addHealthyFood(String type, double price, String name) {
        HealthyFood healthyFood;
        HealthyFoodType healthyFoodType = HealthyFoodType.valueOf(type);

        if (healthyFoodType.equals(HealthyFoodType.Salad)) {
            healthyFood = new Salad(name, price);
            return addFoodToRepository(name, healthyFood);
        } else if (healthyFoodType.equals(HealthyFoodType.VeganBiscuits)) {
            healthyFood = new VeganBiscuits(name, price);
            return addFoodToRepository(name, healthyFood);
        }
        return null;
    }

    private String addFoodToRepository(String name, HealthyFood food) {
        if (healthFoodRepository.foodByName(name) == null) {
            healthFoodRepository.add(food);
            return String.format(FOOD_ADDED, name);
        } else {
            throw new IllegalArgumentException(String.format(FOOD_EXIST, name));
        }
    }

    @Override
    public String addBeverage(String type, int counter, String brand, String name) {
        Beverages beverages;

        BeveragesType beveragesType = BeveragesType.valueOf(type);

        if (beveragesType.equals(BeveragesType.Fresh)) {
            beverages = new Fresh(name, counter, brand);
            return addBeverageToRepository(name, brand, beverages);
        } else if (beveragesType.equals(BeveragesType.Smoothie)) {
            beverages = new Smoothie(name, counter, brand);
            return addBeverageToRepository(name, brand, beverages);
        }
        return null;
    }

    private String addBeverageToRepository(String name, String brand, Beverages beverage) {
        if (beverageRepository.beverageByName(name, brand) == null) {
            beverageRepository.add(beverage);
            return String.format(BEVERAGE_ADDED, beverage.getClass().getSimpleName(), brand);
        } else {
            throw new IllegalArgumentException(String.format(BEVERAGE_EXIST, name));
        }
    }

    @Override
    public String addTable(String type, int tableNumber, int capacity) {
        Table table;

        TableType tableType = TableType.valueOf(type);

        if (tableType.equals(TableType.Indoors)) {
            table = new Indoors(tableNumber, capacity);
            return addTableToRepository(tableNumber, table);
        } else if (tableType.equals(TableType.InGarden)) {
            table = new InGarden(tableNumber, capacity);
            return addTableToRepository(tableNumber, table);
        }
        return null;
    }

    private String addTableToRepository(int tableNumber, Table table) {
        if (tableRepository.byNumber(tableNumber) == null) {
            tableRepository.add(table);
            return String.format(TABLE_ADDED, tableNumber);
        } else {
            throw new IllegalArgumentException(String.format(TABLE_IS_ALREADY_ADDED, tableNumber));
        }
    }

    @Override
    public String reserve(int numberOfPeople) {
        for (Table table : tableRepository.getAllEntities()) {
            if (!table.isReservedTable() && table.getSize() >= numberOfPeople) {
                table.reserve(numberOfPeople);
                return String.format(TABLE_RESERVED, table.getTableNumber(),
                        numberOfPeople);
            }
        }

        return String.format(RESERVATION_NOT_POSSIBLE, numberOfPeople);
    }

    @Override
    public String orderHealthyFood(int tableNumber, String healthyFoodName) {

        Table table = tableRepository.byNumber(tableNumber);

        if (table == null) {
            return String.format(WRONG_TABLE_NUMBER,
                    tableNumber);
        } else {
            HealthyFood food = healthFoodRepository.foodByName(healthyFoodName);

            if (food == null) {
                return String.format(NONE_EXISTENT_FOOD,
                        healthyFoodName);
            } else {
                table.orderHealthy(food);
                return String.format(FOOD_ORDER_SUCCESSFUL,
                        healthyFoodName, tableNumber);
            }
        }
    }

    @Override
    public String orderBeverage(int tableNumber, String name, String brand) {

        Table table = tableRepository.byNumber(tableNumber);

        if (table == null) {
            return String.format(WRONG_TABLE_NUMBER,
                    tableNumber);
        } else {

            Beverages beverage = beverageRepository.beverageByName(name, brand);

            if (beverage == null) {
                return String.format(NON_EXISTENT_DRINK,
                        name, brand);
            } else {
                table.orderBeverages(beverage);

                return String.format(BEVERAGE_ORDER_SUCCESSFUL,
                        name, tableNumber);
            }
        }
    }

    @Override
    public String closedBill(int tableNumber) {
        Table table = tableRepository.byNumber(tableNumber);
        double tableBill = table.bill();
        totalMoney += tableBill;
        table.clear();
        return String.format(BILL, tableNumber, tableBill);
    }

    @Override
    public String totalMoney() {
        return String.format(TOTAL_MONEY, totalMoney);
    }
}
