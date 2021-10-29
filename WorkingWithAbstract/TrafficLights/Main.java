package TrafficLights;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        Color[] signals = Arrays.stream(scanner.nextLine().split("\\s+"))
                .map(String::toUpperCase)
                .map(Color::valueOf)
                .toArray(Color[]::new);
        List<TrafficLight> trafficLights = new ArrayList<>();

        for (Color color : signals) {
            TrafficLight trafficLight = new TrafficLight(color);
            trafficLights.add(trafficLight);
        }

        int number = Integer.parseInt(scanner.nextLine());

        for (int i = 0; i < number; i++) {
            for (TrafficLight light : trafficLights) {
                light.changeColor();
                System.out.print(light + " ");
            }
            System.out.println();
        }
    }
}
