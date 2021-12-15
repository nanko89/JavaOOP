package viceCity.models.neighbourhood;

import viceCity.models.guns.Gun;

import viceCity.models.players.Player;
import viceCity.repositories.interfaces.Repository;

import java.util.ArrayList;
import java.util.Collection;



public class GangNeighbourhood implements Neighbourhood {

    @Override
    public void action(Player mainPlayer, Collection<Player> civilPlayers) {
        Repository<Gun> gunRepository = mainPlayer.getGunRepository();
        for (Gun model : gunRepository.getModels()) {
            for (Player civilPlayer : civilPlayers) {
                while (civilPlayer.isAlive() && model.canFire()) {
                    civilPlayer.takeLifePoints(model.fire());
                }
            }

        }

        for (Player civilPlayer : civilPlayers) {
            if (civilPlayer.isAlive()) {
                Repository<Gun> gunsCivilPlayer = civilPlayer.getGunRepository();
                for (Gun model : gunsCivilPlayer.getModels()) {
                    while (mainPlayer.isAlive() && model.canFire()) {
                        mainPlayer.takeLifePoints(model.fire());
                    }
                    if (!mainPlayer.isAlive()) {
                        break;
                    }
                }
            }
        }
    }
}
