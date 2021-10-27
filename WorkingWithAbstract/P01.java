import java.util.Scanner;

public class P01 {
    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        int n = Integer.parseInt(scanner.nextLine());

        printFigure(n);

    }

    private static void printFigure(int n) {
        printTop(n);
        printMiddle(n);
        printBottom(n);
    }

    public static void printBottom(int n) {
        for (int r = 1; r < n; r++) {
            repeatAndPrintString(r, " ");
            repeatAndPrintString(n - r, "* ");
            System.out.println();

        }
    }

    public static void printMiddle(int n) {
        repeatAndPrintString(n, "* ");
        System.out.println();
    }

    public static void printTop(int n) {
        for (int r = 1; r < n; r++) {
            repeatAndPrintString(n - r, " ");
            repeatAndPrintString(r, "* ");
            System.out.println();
        }
    }

    private static void repeatAndPrintString(int count, String string) {
        for (int i = 0; i < count; i++) {
            System.out.print(string);
        }

    }
}
