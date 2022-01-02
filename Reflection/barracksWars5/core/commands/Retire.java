package barracksWars5.core.commands;

import barracksWars5.interfaces.Inject;
import barracksWars5.interfaces.Repository;

public class Retire extends Command {

    @Inject
    private Repository repository;

    public Retire(String[] data) {
        super(data);
    }

    @Override
    public String execute() {
        String unitType = getData()[1];
        this.repository.removeUnit(unitType);

        return unitType + " retired!";
    }
}
