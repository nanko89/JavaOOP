package CounterStriker.core;

import CounterStriker.models.field.Field;
import CounterStriker.models.field.FieldImpl;
import CounterStriker.models.guns.Gun;
import CounterStriker.models.guns.Pistol;
import CounterStriker.models.guns.Rifle;
import CounterStriker.models.players.CounterTerrorist;
import CounterStriker.models.players.Player;
import CounterStriker.models.players.Terrorist;
import CounterStriker.repositories.GunRepository;
import CounterStriker.repositories.PlayerRepository;
import CounterStriker.repositories.Repository;

import java.util.stream.Collectors;

import static CounterStriker.common.ExceptionMessages.*;
import static CounterStriker.common.OutputMessages.SUCCESSFULLY_ADDED_GUN;
import static CounterStriker.common.OutputMessages.SUCCESSFULLY_ADDED_PLAYER;

public class ControllerImpl implements Controller{
    //•	guns - GunRepository
    //•	players – PlayerRepository
    //•	field - Field

    private Repository<Gun> guns;
    private Repository<Player> players;
    private Field field;

    public ControllerImpl() {
        this.guns = new GunRepository();
        this.players = new PlayerRepository();
        this.field = new FieldImpl();
    }

    @Override
    public String addGun(String type, String name, int bulletsCount) {
        Gun gun;

        switch (type){
            case "Pistol":
                gun = new Pistol(name,bulletsCount);
                break;
            case "Rifle":
                gun = new Rifle(name,bulletsCount);
                break;
            default:
                throw new IllegalArgumentException(INVALID_GUN_TYPE);
        }
        guns.add(gun);

        return String.format(SUCCESSFULLY_ADDED_GUN, name);
    }

    @Override
    public String addPlayer(String type, String username, int health, int armor, String gunName) {
        Gun gun = guns.findByName(gunName);
        if (gun == null){
            throw new NullPointerException(GUN_CANNOT_BE_FOUND);
        }
        Player player;
        switch (type){
            case "Terrorist":
                player = new Terrorist(username,health,armor,gun);
                break;
            case "CounterTerrorist":
                player = new CounterTerrorist(username,health,armor,gun);
                break;
            default:
                throw new IllegalArgumentException(INVALID_PLAYER_TYPE);
        }

        players.add(player);

        return String.format(SUCCESSFULLY_ADDED_PLAYER, username);
    }

    @Override
    public String startGame() {
        return field.start(players.getModels());
    }

    @Override
    public String report() {
        StringBuilder sb = new StringBuilder();
        players.getModels().stream().sorted((p1, p2) -> {
            int result = p1.getClass().getSimpleName().compareTo(p2.getClass().getSimpleName());
            if (result == 0){
                result = Integer.compare(p2.getHealth(), p1.getHealth());
                if (result == 0){
                    return p1.getUsername().compareTo(p2.getUsername());
                }
            }
            return result;
        }).forEach(player -> sb.append(player.toString()));
        return sb.toString().trim();
    }
}
