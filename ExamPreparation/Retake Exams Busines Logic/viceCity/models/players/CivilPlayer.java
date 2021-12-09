package viceCity.models.players;

public class CivilPlayer extends BasePlayer {
    private static int LIFE_POINT = 50;

    public CivilPlayer(String name) {
        super(name, LIFE_POINT);
    }
}
