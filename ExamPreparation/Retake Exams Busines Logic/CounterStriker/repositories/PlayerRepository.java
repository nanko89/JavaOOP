package CounterStriker.repositories;

import CounterStriker.models.players.Player;


import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.Map;

import static CounterStriker.common.ExceptionMessages.*;

public class PlayerRepository implements Repository<Player>{
    private Map<String, Player> models;

    public PlayerRepository() {
        this.models = new LinkedHashMap<>();
    }

    @Override
    public Collection<Player> getModels() {
        return this.models.values();
    }

    @Override
    public void add(Player model) {
        if (model == null){
            throw new NullPointerException(INVALID_PLAYER_REPOSITORY);
        }
        this.models.put(model.getUsername(), model);
    }

    @Override
    public boolean remove(Player model) {
        return this.models.remove(model.getUsername()) != null;
    }

    @Override
    public Player findByName(String name) {
        return this.models.get(name);
    }
}
