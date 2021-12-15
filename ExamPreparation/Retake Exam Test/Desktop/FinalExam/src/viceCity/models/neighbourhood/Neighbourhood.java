package viceCity.models.neighbourhood;

import viceCity.models.players.Player;

import java.util.Collection;
import java.util.List;

public interface Neighbourhood {
    void action(Player mainPlayer, Collection<Player> civilPlayers);
}
