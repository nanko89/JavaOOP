package barracksWars5.core.factories;

import barracksWars5.interfaces.Unit;
import barracksWars5.interfaces.UnitFactory;

import java.lang.reflect.InvocationTargetException;

public class UnitFactoryImpl implements UnitFactory {

    private static final String UNITS_PACKAGE_NAME =
            "barracksWars.models.units.";

    @Override
    public Unit createUnit(String unitType) {
        Unit unitClass = null;

        try {
            Class<?> aClass = Class.forName(UNITS_PACKAGE_NAME + unitType);
            unitClass = (Unit) aClass.getDeclaredConstructor().newInstance();
        } catch (ClassNotFoundException | NoSuchMethodException | InstantiationException | IllegalAccessException | InvocationTargetException e) {
            e.printStackTrace();
        }

        return unitClass;
    }
}
