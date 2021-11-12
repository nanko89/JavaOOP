package VehiclesExtension;

public class Car extends Vehicles {

    private static final double AIR_CONDITIONAL_SUMMER = 0.9;

    public Car(double fuel, double consumption, double tankCapacity) {
        super(fuel, consumption + AIR_CONDITIONAL_SUMMER,
                tankCapacity);
    }


}
