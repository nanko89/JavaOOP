package FootballTeamGenerator;

import java.util.ArrayList;
import java.util.List;

public class Team {
    private String name;
    private List<Player> players;

    public Team(String name) {
       this.setName(name);
       this.players = new ArrayList<>();
    }

    private void setName(String name) {
        if (name.trim().isEmpty()){
            throw new IllegalArgumentException("A name should not be empty.");
        }
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void  addPlayer(Player player){
        players.add(player);

    }
    public void removePlayer(String playerName){
        boolean removePlayer = players.removeIf(player -> player.getName().equals(playerName));
        if (!removePlayer){
            throw new IllegalArgumentException(String.format("Player %s is not in %s team."
                    ,playerName,this.name));
        }

    }
    public  double getRating(){
        return this.players.stream().mapToDouble(Player::overallSkillLevel).average().orElse(0.0);
    }
}

