package Vehicles;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        Map<String, Vehicles> vehicles = new LinkedHashMap<>();

        String[] carInfo = scanner.nextLine().split("\\s+");
        String[] truckInfo = scanner.nextLine().split("\\s+");

        vehicles.put("Car", new Car(Double.parseDouble(carInfo[1]),
                Double.parseDouble(carInfo[2])));

        vehicles.put("Truck", new Truck(Double.parseDouble(truckInfo[1]),
                Double.parseDouble(truckInfo[2])));

        int countCommands = Integer.parseInt(scanner.nextLine());

        for (int i = 0; i < countCommands; i++) {
            String[] command = scanner.nextLine().split("\\s+");
            double argument = Double.parseDouble(command[2]);
            switch (command[0]) {
                case "Drive":
                    System.out.println(vehicles.get(command[1]).drive(argument));
                    break;
                case "Refuel":
                    vehicles.get(command[1]).refuel(argument);
                    break;
            }
        }
        vehicles.values().forEach(System.out::println);
    }
}
