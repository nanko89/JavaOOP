package CounterStriker.models.field;

import CounterStriker.models.players.CounterTerrorist;
import CounterStriker.models.players.Player;
import CounterStriker.models.players.Terrorist;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import static CounterStriker.common.OutputMessages.COUNTER_TERRORIST_WINS;
import static CounterStriker.common.OutputMessages.TERRORIST_WINS;

public class FieldImpl implements Field {
    private List<Player> players;

    public FieldImpl() {
        this.players = new ArrayList<>();
    }

    @Override
    public String start(Collection<Player> players) {
        Collection<Player> terrorists = players.stream()
                .filter(p -> p instanceof Terrorist && p.isAlive()).collect(Collectors.toList());

        Collection<Player> counterTerrorists = players.stream().filter(p -> p instanceof CounterTerrorist && p.isAlive())
                .collect(Collectors.toList());

        while (terrorists.stream().anyMatch(Player::isAlive) && counterTerrorists.stream().anyMatch(Player::isAlive)){
           counterTerrorists = attack(counterTerrorists, terrorists);
           terrorists= attack(terrorists, counterTerrorists);
        }


        if (counterTerrorists.isEmpty()){
            return TERRORIST_WINS;
        }
        return COUNTER_TERRORIST_WINS;
    }

    private Collection<Player> attack(Collection<Player> defencers, Collection<Player> attackers) {
        for (Player attacker : attackers) {
            for (Player defencer : defencers) {
                defencer.takeDamage(attacker.getGun().fire());
            }
        }

        return  defencers.stream().filter(Player::isAlive).collect(Collectors.toList());
    }
}
