package Vehicles;

import java.text.DecimalFormat;

public class Vehicles {
    private double fuel;
    private double consumption;

    protected Vehicles(double fuel, double fuelConsumption) {
        this.fuel = fuel;
        this.consumption = fuelConsumption;
    }

    public String drive(double distance) {
        double fuelNeeded = distance * this.consumption;

        if (fuelNeeded > this.fuel) {
            return String.format("%s needs refueling",
                    this.getClass().getSimpleName());
        }

        this.fuel -= fuelNeeded;

        DecimalFormat decimalFormat = new DecimalFormat("#.##");

        return String.format("%s travelled %s km",
                this.getClass().getSimpleName(), decimalFormat.format(distance));
    }

    public void refuel(double liters) {
        setFuel(liters + getFuel());
    }

    public String toString() {
        return String.format("%s: %.2f",
                this.getClass().getSimpleName(), getFuel());
    }

    public double getConsumption() {
        return consumption;
    }

    public double getFuel() {
        return fuel;
    }

    protected void setFuel(double fuel) {
        this.fuel = fuel;
    }

}
