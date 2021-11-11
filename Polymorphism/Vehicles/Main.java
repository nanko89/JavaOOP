package Vehicles;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        String[] carInfo = scanner.nextLine().split("\\s+");
        String[] truckInfo = scanner.nextLine().split("\\s+");

        Car car = new Car(Double.parseDouble(carInfo[1]), Double.parseDouble(carInfo[2]));
        Truck truck = new Truck(Double.parseDouble(truckInfo[1]), Double.parseDouble(truckInfo[2]));

        int countCommands = Integer.parseInt(scanner.nextLine());

        for (int i = 0; i < countCommands; i++) {
            String[] command = scanner.nextLine().split("\\s+");
            switch (command[0]){
                case "Drive":
                    switch (command[1]){
                        case "Car":
                            System.out.println(car.driven(Double.parseDouble(command[2])));
                            break;
                        case "Truck":
                            System.out.println(truck.driven(Double.parseDouble(command[2])));
                            break;
                    }
                    break;
                case "Refuel":
                    switch (command[1]){
                        case "Car":
                           car.refuel(Double.parseDouble(command[2]));
                            break;
                        case "Truck":
                            truck.refuel(Double.parseDouble(command[2]));
                            break;
                    }
                    break;
            }
        }
        System.out.println(car);
        System.out.println(truck);
    }
}
