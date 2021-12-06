package CounterStriker.models.field;

import CounterStriker.models.players.CounterTerrorist;
import CounterStriker.models.players.Player;
import CounterStriker.models.players.Terrorist;

import java.util.ArrayDeque;
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
    public String start(List<Player> players) {

        Collection<Player> terrorists = players.stream()
                .filter(p -> p instanceof Terrorist && p.isAlive())
                .collect(Collectors.toList());

        Collection<Player> counterTerrorists = players.stream()
                .filter(p -> p instanceof CounterTerrorist && p.isAlive())
                .collect(Collectors.toList());

        while (counterTerrorists.stream().anyMatch(Player::isAlive) && terrorists.stream().anyMatch(Player::isAlive)){
            for (Player terrorist : terrorists) {
                for (Player counterTerrorist : counterTerrorists) {
                    counterTerrorist.takeDamage(terrorist.getGun().fire());
                }
            }

            counterTerrorists = counterTerrorists.stream().filter(Player::isAlive).collect(Collectors.toList());

            for (Player counterTerrorist : counterTerrorists) {
                for (Player terrorist : terrorists) {
                    terrorist.takeDamage(counterTerrorist.getGun().fire());
                }
            }
            terrorists = terrorists.stream().filter(Player::isAlive).collect(Collectors.toList());
        }
        return terrorists.stream().anyMatch(Player::isAlive) ? TERRORIST_WINS : COUNTER_TERRORIST_WINS;
    }
}
