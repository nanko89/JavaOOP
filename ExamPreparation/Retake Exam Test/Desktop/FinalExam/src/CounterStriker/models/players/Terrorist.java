package CounterStriker.models.players;

import CounterStriker.models.guns.Gun;

public class Terrorist extends PlayerImpl{

    public Terrorist(String name, int health, int armor, Gun gun) {
        super(name, health, armor, gun);
    }
}
