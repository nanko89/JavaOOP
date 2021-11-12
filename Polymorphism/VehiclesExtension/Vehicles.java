package VehiclesExtension;

import java.text.DecimalFormat;

public class Vehicles {
    private double fuel;
    private double consumption;
    private double tankCapacity;

    protected Vehicles(double fuel, double fuelConsumption, double tankCapacity) {
        this.tankCapacity = tankCapacity;
        setFuel(fuel);
        this.consumption = fuelConsumption;
    }

    public String drive(double distance) {
        double fuelNeeded = distance * this.consumption;

        if (fuelNeeded > this.fuel) {
            return String.format("%s needs refueling",
                    this.getClass().getSimpleName());
        }

        this.setFuel(this.fuel - fuelNeeded);

        DecimalFormat decimalFormat = new DecimalFormat("#.##");

        return String.format("%s travelled %s km",
                this.getClass().getSimpleName(), decimalFormat.format(distance));
    }

    private void validateHasCapacity(double fuel){
        if (fuel > this.tankCapacity){
            throw new IllegalArgumentException("Cannot fit fuel in tank");
        }
    }

    private void validateNonNegativeFuel(double fuel){
        if (fuel <= 0) {
            throw new IllegalArgumentException("Fuel must be a positive number");
        }
    }

    public void refuel(double liters) {
        validateHasCapacity(liters);
        validateNonNegativeFuel(liters);
        setFuel(liters + this.fuel);
    }

    public String toString() {
        return String.format("%s: %.2f",
                this.getClass().getSimpleName(), getFuel());
    }

    protected  void addConsumption(double additional){
            this.consumption += additional;
    }

    protected  void subtractConsumption(double subtract){
        this.consumption -= subtract;
    }

    public double getConsumption() {
        return consumption;
    }

    public double getFuel() {
        return fuel;
    }

    protected void setFuel(double fuel) {
        validateNonNegativeFuel(fuel);
        validateHasCapacity(fuel);
        this.fuel = fuel;
    }


}
