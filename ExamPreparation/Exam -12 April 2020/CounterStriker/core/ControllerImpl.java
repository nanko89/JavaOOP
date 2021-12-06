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

import java.util.Collection;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static CounterStriker.common.ExceptionMessages.*;
import static CounterStriker.common.OutputMessages.SUCCESSFULLY_ADDED_GUN;
import static CounterStriker.common.OutputMessages.SUCCESSFULLY_ADDED_PLAYER;

public class ControllerImpl implements Controller {
    private GunRepository guns;
    private PlayerRepository players;
    private Field field;

    public ControllerImpl() {
        this.guns = new GunRepository();
        this.players = new PlayerRepository();
        this.field = new FieldImpl();
    }

    @Override
    public String addGun(String type, String name, int bulletsCount) {
        Gun gun;
        if (type.equals("Pistol")) {
            gun = new Pistol(name, bulletsCount);
        } else if (type.equals("Rifle")) {
            gun = new Rifle(name, bulletsCount);
        }else {
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
        if (type.equals("Terrorist")){
            player = new Terrorist(username,health,armor,gun);
        }else if (type.equals("CounterTerrorist")){
            player = new CounterTerrorist(username,health,armor,gun);
        }else {
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
        List<Player> terrorist = players.getModels().stream()
                .filter(p -> p.getClass().getSimpleName().equals("Terrorist"))
                .collect(Collectors.toList());

        List<Player> counterTerrorist = players.getModels()
                .stream()
                .filter(p->p.getClass().getSimpleName().equals("CounterTerrorist"))
                .collect(Collectors.toList());

        List<Player> sortedTerrorist = terrorist.stream().sorted(Comparator.comparing(Player::getHealth)
                .thenComparing(Player::getUsername)).collect(Collectors.toList());

        List<Player> sortedContraTerrorist = counterTerrorist.stream().sorted(Comparator.comparing(Player::getHealth)
                .thenComparing(Player::getUsername)).collect(Collectors.toList());

        for (Player player : sortedContraTerrorist) {
            sb.append(player.toString());
        }

        for (Player player : sortedTerrorist) {
            sb.append(player.toString());
        }
        return sb.toString().trim();
    }
}
