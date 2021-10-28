package CardsWithPower;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        CardsRank cardsRank = CardsRank.valueOf(scanner.nextLine());
        SuitPower suitPower = SuitPower.valueOf(scanner.nextLine());

        System.out.printf("Card name: %s of %s; Card power: %d", cardsRank, suitPower, cardsRank.getRank() + suitPower.getPower());


    }
}
