package VehiclesExtension;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        Map<String, Vehicles> vehicles = new LinkedHashMap<>();
                vehicles.put("Car", createVehicle(scanner.nextLine()));
                vehicles.put("Truck", createVehicle(scanner.nextLine()));
                Bus bus = createBus(scanner.nextLine());
                vehicles.put("Bus", bus);

        int countCommands = Integer.parseInt(scanner.nextLine());

        for (int i = 0; i < countCommands; i++) {

            String[] command = scanner.nextLine().split("\\s+");

            double argument = Double.parseDouble(command[2]);

            if ("Drive".equals(command[0]) && "Bus".equals(command[1])) {
                System.out.println(bus.driveWithPassengers(argument));
            }else if ("DriveEmpty".equals(command[0])){
                System.out.println(vehicles.get(command[1]).drive(argument));
            } else if ("Drive".equals(command[0])) {
                System.out.println(vehicles.get(command[1]).drive(argument));
            } else if ("Refuel".equals(command[0])) {
                try {
                    vehicles.get(command[1]).refuel(argument);
                } catch (IllegalArgumentException e) {
                    System.out.println(e.getMessage());
                }
            }
        }
        vehicles.values().forEach(System.out::println);
    }

    private static Bus createBus(String input) {
        String[] token = input.split("\\s+");
        return new Bus(Double.parseDouble(token[1]),
                Double.parseDouble(token[2]),
                Double.parseDouble(token[3]));
    }

    private static Vehicles createVehicle(String input) {
        String[] token = input.split("\\s+");
        switch (token[0]) {
            case "Car":
                return new Car(Double.parseDouble(token[1]),
                        Double.parseDouble(token[2]),
                        Double.parseDouble(token[3]));
            case "Truck":
                return new Truck(Double.parseDouble(token[1]),
                        Double.parseDouble(token[2]),
                        Double.parseDouble(token[3]));
            case "Bus":
                return createBus(input);
            default:
                throw new IllegalStateException("Unknown vehicle type for " + token[0]);
        }
    }
}
