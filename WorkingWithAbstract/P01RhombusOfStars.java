import java.util.Scanner;
import java.util.function.Predicate;

public class P01RhombusOfStars {
    public static void main(String[] args) {

        int size = readInput();

        String figure = buildRombOfStars(size);

        printOutput(figure);

    }

    private static String printMultipleRows(int start, int end, int step, int size) {

        StringBuilder out = new StringBuilder();

        Predicate<Integer> loopCondition = iter -> {
            if (step > 0) {
                return iter <= end;
            }
            return iter >= end;
        };
        for (int r = start; loopCondition.test(r); r += step) {
            out.append(printLine(size - r, r))
                    .append(System.lineSeparator());
        }
        return out.toString();
    }

    private static String buildRombOfStars(int size) {
        StringBuilder out = new StringBuilder();
        out.append(printMultipleRows(1,size,+1,size));
        out.append(printMultipleRows(size -1, 1 , -1, size));
        return out.toString();
    }

    private static String printLine(int spaces, int stars) {

        return " ".repeat(Math.max(0, spaces)) +
                "* ".repeat(Math.max(0, stars));
    }

    private static int readInput() {
        return Integer.parseInt(new Scanner(System.in).nextLine());
    }

    private static void printOutput(String figure) {
        System.out.println(figure);
    }
}
