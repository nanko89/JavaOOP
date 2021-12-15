package CounterStriker.models.guns;

public class Rifle extends GunImpl{
    private static final int FIRED_BULLETS = 10;

    public Rifle(String name, int bulletsCount) {
        super(name, bulletsCount);
    }

    @Override
    public int fire() {
        if (getBulletsCount() >= FIRED_BULLETS){
            setBulletsCount(getBulletsCount() - FIRED_BULLETS);
            return 10;
        }
        return 0;
    }
}
