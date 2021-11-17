package MilitaryElite.entities;

import MilitaryElite.enums.Corps;
import MilitaryElite.interfaces.Engineer;
import MilitaryElite.interfaces.Repair;


import java.util.ArrayList;
import java.util.Collection;
import java.util.List;


public class EngineerImpl extends SpecialisedSoldierImpl implements Engineer{
    private List<Repair> repairSet;


    public EngineerImpl(int id, String firstName, String lastName, double salary, Corps corps) {
        super(id, firstName, lastName, salary, corps);
        this.repairSet = new ArrayList<>();
    }

    @Override
    public void addRepair(Repair repair) {
        repairSet.add(repair);
    }

    @Override
    public Collection<Repair> getRepairs() {
        return this.repairSet;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder(super.toString());
        sb.append("Repairs:").append(System.lineSeparator());

        for (Repair repair : repairSet) {
            sb.append(repair.toString()).append(System.lineSeparator());
        }
        return sb.toString().trim();
    }
}
