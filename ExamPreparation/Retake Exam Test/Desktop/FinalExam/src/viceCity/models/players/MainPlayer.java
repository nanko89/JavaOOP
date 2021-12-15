package viceCity.models.players;

public class MainPlayer extends BasePlayer{
    private static int LIFE_POINT = 100;

    private static String NAME = "Tommy Vercetti";

    public MainPlayer() {
        super(NAME, LIFE_POINT);
    }
}
