package Vehicles;

import java.text.DecimalFormat;

public class Truck extends VehiclesImpl{
    private static final double AIR_CONDITIONAL_SUMMER = 1.6;

    public Truck(double fuel, double consumption) {
        super(fuel, consumption + AIR_CONDITIONAL_SUMMER);
    }

    @Override
    public String driven(double distance) {
        double fuelNeeded = distance * getConsumption();
        if (fuelNeeded > getFuel() ){
            return "Truck needs refueling";
        }
        setFuel(getFuel() - fuelNeeded);
        String pattern = "0.##";
        DecimalFormat decimalFormat = new DecimalFormat(pattern);
        return String.format("Truck travelled %s km", decimalFormat.format(distance));
    }

    @Override
    public void refuel(double liters) {
        double truckFuel = liters * 0.95;
        setFuel(truckFuel + getFuel());
    }

    @Override
    public String toString() {
        return String.format("Truck: %.2f", getFuel());
    }
}
