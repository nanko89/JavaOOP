package Vehicles;

import java.text.DecimalFormat;

public class Car extends VehiclesImpl{

    private static final double AIR_CONDITIONAL_SUMMER = 0.9;

    public Car(double fuel, double consumption) {
        super(fuel, consumption + AIR_CONDITIONAL_SUMMER);
    }


    @Override
    public String driven(double distance) {
        double fuelNeeded = distance * getConsumption();
        if (fuelNeeded > getFuel() ){
            return "Car needs refueling";
        }
        setFuel(getFuel() - fuelNeeded);
        String pattern = "0.##";
        DecimalFormat decimalFormat = new DecimalFormat(pattern);
        return String.format("Car travelled %s km", decimalFormat.format(distance));
    }

    @Override
    public void refuel(double liters) {
        setFuel(liters + getFuel());
    }

    @Override
    public String toString() {
        return String.format("Car: %.2f", getFuel());
    }
}
