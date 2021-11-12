package VehiclesExtension;

public class Truck extends Vehicles {
    private static final double AIR_CONDITIONAL_SUMMER = 1.6;

    public Truck(double fuel, double consumption, double tankCapacity) {
        super(fuel, consumption + AIR_CONDITIONAL_SUMMER, tankCapacity);
    }

    @Override
    public void refuel(double liters) {
        super.refuel(liters * 0.95);
    }

}
