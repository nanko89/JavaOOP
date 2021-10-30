package jediGalaxy;

import java.util.Arrays;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int[] dimensions = readPosition(scanner.nextLine());

        int rows = dimensions[0];
        int cols = dimensions[1];

        Field field = new Field(rows,cols);
        Galaxy galaxy = new Galaxy(field);

        String command = scanner.nextLine();

        long startsCollect = 0;

        while (!command.equals("Let the Force be with you")) {

            int[] jediPosition = readPosition(command);
            int[] evilPossition = readPosition(scanner.nextLine());

            int rowEvil = evilPossition[0];
            int colEvil = evilPossition[1];

            galaxy.moveEvil(rowEvil,colEvil);

            int rowJedi = jediPosition[0];
            int colJedi = jediPosition[1];

            startsCollect += galaxy.moveJedi(rowJedi,colJedi);

            command = scanner.nextLine();
        }

        System.out.println(startsCollect);


    }

    private static int[] readPosition(String command){
        return Arrays.stream(command.split(" "))
                .mapToInt(Integer::parseInt).toArray();
    }
}
