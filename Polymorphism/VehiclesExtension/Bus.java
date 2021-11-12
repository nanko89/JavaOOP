package VehiclesExtension;

public class Bus extends Vehicles{
    private static final double AIR_CONDITIONAL_SUMMER = 1.4;

    public Bus(double fuel, double fuelConsumption, double tankCapacity) {
        super(fuel, fuelConsumption, tankCapacity);
    }

    public String driveWithPassengers(double distance) {
        super.addConsumption(AIR_CONDITIONAL_SUMMER);
        String out = super.drive(distance);
        super.subtractConsumption(AIR_CONDITIONAL_SUMMER);
        return out;
    }

}
