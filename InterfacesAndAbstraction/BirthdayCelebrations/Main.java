package BirthdayCelebrations;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        String input = scanner.nextLine();

        List<Birthable> birthdays = new ArrayList<>();

        while (!input.equals("End")) {
            String[] token = input.split(" ");
            switch (token[0]) {
                case "Citizen":
                    Birthable citizen = new Citizen(token[1], Integer.parseInt(token[2]), token[3], token[4]);
                    birthdays.add(citizen);
                    break;
                case "Pet":
                    Birthable pet = new Pet(token[1], token[2]);
                    birthdays.add(pet);
                    break;
                case "Robot":
                    break;
            }
            input = scanner.nextLine();

        }
        String year = scanner.nextLine();

        if (birthdays.size() > 0) {
            System.out.println(birthdays.stream()
                    .map(Birthable::getBirthDate)
                    .filter(e -> e.endsWith(year))
                    .collect(Collectors.joining(System.lineSeparator())));
        }else {
            System.out.println("<no output>");
        }
    }
}
