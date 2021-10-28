package HotelReservation;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        String[] input = scanner.nextLine().split("\\s+");

        double pricePerDay = Double.parseDouble(input[0]);
        int days = Integer.parseInt(input[1]);
        Seasons seasons = Seasons.valueOf(input[2].toUpperCase());
        Discount discount = Discount.parseDiscount(input[3]);

        PriceCalculator calculator = new PriceCalculator(pricePerDay,days,seasons,discount);

        System.out.printf("%.2f%n" , calculator.calculatorPrice());

    }


}
