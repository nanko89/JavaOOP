package CounterStriker.repositories;

import CounterStriker.models.players.Player;

import java.util.*;

import static CounterStriker.common.ExceptionMessages.INVALID_PLAYER_REPOSITORY;

public class PlayerRepository implements Repository<Player> {
    private List<Player> players;

    public PlayerRepository() {
        this.players = new ArrayList<>();
    }

    @Override
    public List<Player> getModels() {
        return this.players;
    }

    @Override
    public void add(Player model) {
        if (model == null){
            throw new NullPointerException(INVALID_PLAYER_REPOSITORY);
        }
        players.add(model);
    }

    @Override
    public boolean remove(Player model) {
        return players.remove(model);
    }

    @Override
    public Player findByName(String name) {
        return players.stream()
                .filter(p->p.getUsername().equals(name))
                .findFirst()
                .orElse(null);
    }
}
