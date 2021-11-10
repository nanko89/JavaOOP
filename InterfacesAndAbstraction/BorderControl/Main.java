package BorderControl;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        String input = scanner.nextLine();
        List<Identifiable> ids = new ArrayList<>();

        while (!input.equals("End")) {
            String[] data = input.split(" ");

            Identifiable identifiable = data.length == 2
                    ? new Robot(data[0], data[1])
                    : new Citizen(data[0], Integer.parseInt(data[1]), data[2]);
                ids.add(identifiable);

                input = scanner.nextLine();
        }
        String invalidId = scanner.nextLine();

        System.out.println(ids.stream()
                .map(Identifiable::getId)
                .filter(id -> id.endsWith(invalidId))
                .collect(Collectors.joining(System.lineSeparator())));

    }
}
