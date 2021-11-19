package Archive.FirstAndReserveTeam;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Team {
    private String name;
    private List<Person> firstTeamPlayers;
    private List<Person> reserveTeamPlayers;

    public Team(String name) {
        this.name = name;
        this.firstTeamPlayers = new ArrayList<>();
        this.reserveTeamPlayers = new ArrayList<>();
    }

    public void addPlayer (Person person){
        if (person.getAge() < 40){
            firstTeamPlayers.add(person);
        }else {
            reserveTeamPlayers.add(person);
        }
    }

    public List<Person> getFirstTeam(){
        return Collections.unmodifiableList(firstTeamPlayers);
    }

    public List<Person> getReserveTeamPlayers() {
        return Collections.unmodifiableList(reserveTeamPlayers);
    }
}
