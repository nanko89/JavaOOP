package MilitaryElite.entities;

import MilitaryElite.enums.Corps;
import MilitaryElite.interfaces.SpecialisedSoldier;


public class SpecialisedSoldierImpl extends PrivateImpl implements SpecialisedSoldier {
    protected Corps corps;

    public SpecialisedSoldierImpl(int id, String firstName, String lastName, double salary, Corps corps) {
        super(id, firstName, lastName, salary);
        this.corps = corps;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder(super.toString() + System.lineSeparator() + "Corps: ");
        sb.append(this.corps).append(System.lineSeparator());
        return sb.toString();
    }
}
