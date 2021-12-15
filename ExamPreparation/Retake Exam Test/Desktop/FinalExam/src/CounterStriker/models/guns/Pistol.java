package CounterStriker.models.guns;

public class Pistol extends GunImpl{
    private static final int FIRED_BULLETS = 1;

    public Pistol(String name, int bulletsCount) {
        super(name, bulletsCount);
    }

    @Override
    public int fire() {
        if (getBulletsCount() >= FIRED_BULLETS){
            setBulletsCount(getBulletsCount() - FIRED_BULLETS);
            return 1;
        }
        return 0;
    }
}
