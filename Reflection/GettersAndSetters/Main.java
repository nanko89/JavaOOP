import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) throws NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException {
        Class<Reflection> clazz = Reflection.class;

        Method[] methods = clazz.getDeclaredMethods();

        Arrays.stream(methods)
                .filter(m -> m.getName().contains("get") || m.getName().contains("set"))
                .sorted(Comparator.comparing(Method::getName))
                .forEach(Main::printMethod);
    }

    public static void printMethod(Method method) {
        String out = method.getName().contains("set")
                ? String.format("%s and will set field of %s", method.getName(), formatType(method.getParameterTypes()[0]))
                : String.format("%s will return %s", method.getName(), formatType(method.getReturnType()));
        System.out.println(out);
    }

    public static String formatType(Class<?> type){
        return type == int.class
                ? "class " + type
                : type.toString();
    }
}
