package galaxy;

import java.util.Arrays;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int[] dimensions = readArray(scanner.nextLine());
        int n = dimensions[0];
        int m = dimensions[1];

        Field field = new Field(n, m);

        Galaxy galaxy = new Galaxy(field);

        String command = scanner.nextLine();
        long sum = 0;
        while (!command.equals("Let the Force be with you")) {
            int[] playerPosition = readArray(command);
            int[] evilPosition = readArray(scanner.nextLine());

            int evilRow = evilPosition[0];
            int evilCol = evilPosition[1];

            galaxy.moveEvil(evilRow, evilCol);

            int playerRow = playerPosition[0];
            int playerCol = playerPosition[1];

            sum += galaxy.movePlayer(playerRow,playerCol);

            command = scanner.nextLine();
        }

        System.out.println(sum);


    }

    public static int[] readArray(String input) {
        return Arrays.stream(input.split("\\s+")).mapToInt(Integer::parseInt).toArray();
    }
}
