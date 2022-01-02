package barracksWars5.core.commands;

import barracksWars5.interfaces.Inject;
import barracksWars5.interfaces.Repository;
import barracksWars5.interfaces.Unit;
import barracksWars5.interfaces.UnitFactory;

public class Add extends Command {
    private String[] data;
    @Inject
    private Repository repository;
    @Inject
    private UnitFactory unitFactory;

    public Add(String[] data) {
        super(data);
    }

    @Override
    public String execute() {
        String unitType = getData()[1];
        Unit unitToAdd = this.unitFactory.createUnit(unitType);
        this.repository.addUnit(unitToAdd);
        return unitType + " added!";
    }
}
