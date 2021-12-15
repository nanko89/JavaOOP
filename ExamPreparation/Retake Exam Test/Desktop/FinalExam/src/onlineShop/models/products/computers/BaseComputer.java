package onlineShop.models.products.computers;

import onlineShop.models.products.BaseProduct;
import onlineShop.models.products.Product;
import onlineShop.models.products.components.Component;
import onlineShop.models.products.peripherals.Peripheral;

import java.util.ArrayList;
import java.util.List;

import static onlineShop.common.constants.ExceptionMessages.*;
import static onlineShop.common.constants.OutputMessages.*;

public abstract class BaseComputer extends BaseProduct implements Computer {
    private List<Component> components;

    private List<Peripheral> peripherals;


    protected BaseComputer(int id, String manufacturer, String model, double price, double overallPerformance) {
        super(id, manufacturer, model, price, overallPerformance);
        this.components = new ArrayList<>();
        this.peripherals = new ArrayList<>();
    }

    @Override
    public List<Component> getComponents() {
        return this.components;
    }

    @Override
    public List<Peripheral> getPeripherals() {
        return this.peripherals;
    }

    @Override
    public double getOverallPerformance() {
        double overallPerformanceAverage = components.stream()
                .mapToDouble(Component::getOverallPerformance)
                .average().orElse(0);
        return super.getOverallPerformance() + overallPerformanceAverage;
    }

    @Override
    public double getPrice() {
        return super.getPrice()
                + components.stream().mapToDouble(Component::getPrice).sum()
                + peripherals.stream().mapToDouble(Peripheral::getPrice).sum();
    }

    @Override
    public void addComponent(Component component) {

        if (components.stream().anyMatch(c -> component.getClass().getSimpleName()
                .equals(c.getClass().getSimpleName()))){
            throw new IllegalArgumentException(String.format(EXISTING_COMPONENT,
                    component.getClass().getSimpleName(), this.getClass().getSimpleName(), this.getId()));
        }
        components.add(component);
    }

    @Override
    public Component removeComponent(String componentType) {
        Component component = components.stream().filter(c -> c.getClass().getSimpleName().equals(componentType)).findFirst().orElse(null);
        if (components.isEmpty() || component == null){
            throw new IllegalArgumentException(String.format(NOT_EXISTING_COMPONENT,
                    componentType, this.getClass().getSimpleName(), this.getId()));
        }
        components.remove(component);

        return component;

    }

    @Override
    public void addPeripheral(Peripheral peripheral) {
        if (peripherals.stream().anyMatch(c -> peripheral.getClass().getSimpleName()
                .equals(c.getClass().getSimpleName()))){
            throw new IllegalArgumentException(String.format(EXISTING_PERIPHERAL,
                    peripheral.getClass().getSimpleName(), this.getClass().getSimpleName(), this.getId()));
        }
        peripherals.add(peripheral);
    }

    @Override
    public Peripheral removePeripheral(String peripheralType) {
        Peripheral peripheral = peripherals.stream()
                .filter(c -> c.getClass().getSimpleName().equals(peripheralType))
                .findFirst().orElse(null);
        if (peripherals.isEmpty() || peripheral == null){
            throw new IllegalArgumentException(String.format(NOT_EXISTING_PERIPHERAL,
                    peripheralType, this.getClass().getSimpleName(), this.getId()));
        }
        peripherals.remove(peripheral);

        return peripheral;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(String.format(PRODUCT_TO_STRING, getOverallPerformance(),
               getPrice(), this.getClass().getSimpleName(), getManufacturer(), getModel(), getId())).append(System.lineSeparator());
        sb.append(" ").append(String.format(COMPUTER_COMPONENTS_TO_STRING, components.size()))
                .append(System.lineSeparator());
        for (Component component : components) {
            sb.append("  ").append(component.toString()).append(System.lineSeparator());
        }
        double averagePeripheral = peripherals.stream().mapToDouble(Product::getOverallPerformance).average().orElse(0);
        sb.append(" ").append(String.format(COMPUTER_PERIPHERALS_TO_STRING, peripherals.size(), averagePeripheral))
                .append(System.lineSeparator());
        for (Peripheral peripheral : peripherals) {
            sb.append("  ").append(peripheral.toString()).append(System.lineSeparator());
        }
        return sb.toString().trim();
    }
}
