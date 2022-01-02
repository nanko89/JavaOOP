package catHouse.core;

import catHouse.entities.cat.Cat;
import catHouse.entities.cat.LonghairCat;
import catHouse.entities.cat.ShorthairCat;
import catHouse.entities.houses.House;
import catHouse.entities.houses.LongHouse;
import catHouse.entities.houses.ShortHouse;
import catHouse.entities.toys.Ball;
import catHouse.entities.toys.Mouse;
import catHouse.entities.toys.Toy;
import catHouse.repositories.ToyRepository;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import static catHouse.common.ConstantMessages.*;
import static catHouse.common.ExceptionMessages.*;

public class ControllerImpl implements Controller{
    private ToyRepository toys;
    private Map<String, House> houses;

    public ControllerImpl() {
        this.toys = new ToyRepository();
        this.houses = new LinkedHashMap<>();
    }

    @Override
    public String addHouse(String type, String name) {
        House house;
        switch (type){
            case "LongHouse":
                house = new LongHouse(name);
                break;
            case "ShortHouse":
                house = new ShortHouse(name);
                break;
            default:
                throw new NullPointerException(INVALID_HOUSE_TYPE);
        }
        houses.put(name, house);

        return String.format(SUCCESSFULLY_ADDED_HOUSE_TYPE, type);
    }

    @Override
    public String buyToy(String type) {
        Toy toy;
        switch (type){
            case "Ball":
                toy = new Ball();
                break;
            case "Mouse":
                toy = new Mouse();
                break;
            default:
                throw new IllegalArgumentException(INVALID_TOY_TYPE);
        }
        toys.buyToy(toy);

        return String.format(SUCCESSFULLY_ADDED_TOY_TYPE, type);
    }

    @Override
    public String toyForHouse(String houseName, String toyType) {
        Toy toy = toys.findFirst(toyType);
        if (toy == null){
            throw new IllegalArgumentException(String.format(NO_TOY_FOUND, toyType));
        }

        House house = houses.get(houseName);

        house.buyToy(toy);
        toys.removeToy(toy);
        return String.format(SUCCESSFULLY_ADDED_TOY_IN_HOUSE, toyType,houseName);
    }

    @Override
    public String addCat(String houseName, String catType, String catName, String catBreed, double price) {
        House house = houses.get(houseName);

        Cat cat;
        switch (catType){
            case "LonghairCat":
                cat = new LonghairCat(catName, catBreed, price);
                if (house.getClass().getSimpleName().equals("ShortHouse")){
                    return UNSUITABLE_HOUSE;
                }
                break;
            case "ShorthairCat":
                cat = new ShorthairCat(catName, catBreed, price);
                if (house.getClass().getSimpleName().equals("LongHouse")){
                    return UNSUITABLE_HOUSE;
                }
                break;
            default:
                throw new IllegalArgumentException(INVALID_CAT_TYPE);
        }
        house.addCat(cat);

        return String.format(SUCCESSFULLY_ADDED_CAT_IN_HOUSE, catType, houseName);
    }

    @Override
    public String feedingCat(String houseName) {
        House house = houses.get(houseName);
        house.feeding();

        return String.format(FEEDING_CAT,house.getCats().size()) ;
    }

    @Override
    public String sumOfAll(String houseName) {
        House house = houses.get(houseName);

        double catsSum = house.getCats().stream().mapToDouble(Cat::getPrice).sum();
        double toysSum = house.getToys().stream().mapToDouble(Toy::getPrice).sum();

        return String.format(VALUE_HOUSE, houseName, catsSum + toysSum);
    }

    @Override
    public String getStatistics() {
        StringBuilder sb = new StringBuilder();
        houses.values().forEach(house -> sb.append( house.getStatistics()));
        return sb.toString().trim();
    }
}
