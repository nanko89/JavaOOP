package blackBoxInteger;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws InvocationTargetException,
            IllegalAccessException, NoSuchMethodException, InstantiationException, NoSuchFieldException {
        Scanner scanner = new Scanner(System.in);

        Class<BlackBoxInt> clazz = BlackBoxInt.class;

        Constructor<BlackBoxInt> constructor = clazz.getDeclaredConstructor();

        constructor.setAccessible(true);

        BlackBoxInt blackBoxInt = constructor.newInstance();

        Field innerValue = clazz.getDeclaredField("innerValue");

        String input = scanner.nextLine();

        Method[] methods = BlackBoxInt.class.getDeclaredMethods();

        while (!input.equals("END")) {
            String command = input.split("_")[0];
            int value = Integer.parseInt(input.split("_")[1]);

            Method method = Arrays.stream(methods)
                    .filter(method1 -> method1.getName().equals(command))
                    .findFirst()
                    .orElse(null);
            if (method != null) {
                method.setAccessible(true);
                method.invoke(blackBoxInt, value);
                innerValue.setAccessible(true);
                System.out.println(innerValue.get(blackBoxInt));
            }

            input = scanner.nextLine();

        }
    }
}
