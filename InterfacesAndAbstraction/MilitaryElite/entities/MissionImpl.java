package MilitaryElite.entities;

import MilitaryElite.enums.State;
import MilitaryElite.interfaces.Mission;


public class MissionImpl implements Mission {
    private String codeName;
    private State status;

    public MissionImpl(String codeName, State status) {
        this.codeName = codeName;
        this.status = status;
    }

    @Override
    public void completeMission() {
        this.status = State.finished;
    }

    @Override
    public String toString() {
        return String.format("  Code Name: %s State: %s", codeName, status.name());
    }
}
