package Vehicles;

public abstract class VehiclesImpl implements  Vehicles{
    private double fuel;
    private double consumption;

    protected VehiclesImpl(double fuel, double fuelConsumption) {
        this.fuel = fuel;
        this.consumption = fuelConsumption;
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
