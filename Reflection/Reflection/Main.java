import java.lang.reflect.InvocationTargetException;

public class Main {
    public static void main(String[] args) throws NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException {
        Class<Reflection> clazz = Reflection.class;
        System.out.println(clazz);

        Class superClass = clazz.getSuperclass();
        System.out.println(superClass);

        Class[] interfaces = clazz.getInterfaces();
        for (Class anInterface : interfaces) {
            System.out.println(anInterface);
        }

        Reflection reflection = clazz.getDeclaredConstructor().newInstance();
        System.out.println(reflection);
    }
}
