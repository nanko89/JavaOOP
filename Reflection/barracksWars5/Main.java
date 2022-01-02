package barracksWars5;

import barracksWars5.core.CommandInterpreterImpl;
import barracksWars5.interfaces.Repository;
import barracksWars5.interfaces.Runnable;
import barracksWars5.interfaces.UnitFactory;
import barracksWars5.core.Engine;
import barracksWars5.core.factories.UnitFactoryImpl;
import barracksWars5.data.UnitRepository;

import java.lang.reflect.InvocationTargetException;

public class Main {

    public static void main(String[] args) throws ClassNotFoundException, InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException {
        Repository repository = new UnitRepository();
        UnitFactory unitFactory = new UnitFactoryImpl();

        Runnable engine = new Engine(new CommandInterpreterImpl(repository, unitFactory));
        engine.run();
    }
}
