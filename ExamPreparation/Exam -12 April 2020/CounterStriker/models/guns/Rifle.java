package CounterStriker.models.guns;

public class Rifle extends GunImpl{
    private static final int BULLET_FIRE = 10;

    public Rifle(String name, int bulletsCount) {
        super(name, bulletsCount);
    }

    @Override
    public int fire() {
        if (super.getBulletsCount() < BULLET_FIRE){
            return 0;
        }
        super.decreaseBullets(BULLET_FIRE);
        return BULLET_FIRE;
    }
}
