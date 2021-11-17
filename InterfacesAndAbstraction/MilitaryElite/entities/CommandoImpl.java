package MilitaryElite.entities;

import MilitaryElite.enums.Corps;
import MilitaryElite.interfaces.Commando;
import MilitaryElite.interfaces.Mission;

import java.util.*;

public class CommandoImpl extends SpecialisedSoldierImpl implements Commando {
    private List<Mission> missions;

    public CommandoImpl(int id, String firstName, String lastName, double salary, Corps corps) {
        super(id, firstName, lastName, salary, corps);
        this.missions = new ArrayList<>();
    }

    @Override
    public void addMission(Mission mission) {
        this.missions.add(mission);
    }

    @Override
    public Collection<Mission> getMissions() {
        return this.missions;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder(super.toString());

        sb.append("Missions:").append(System.lineSeparator());

        for (Mission mission : missions) {
            sb.append(mission.toString())
                    .append(System.lineSeparator());
        }

        return sb.toString().trim();
    }
}
