package CounterStriker.models.guns;

public class Pistol extends GunImpl{

    private static final int BULLET_FIRE = 1;
    public Pistol(String name, int bulletsCount) {
        super(name, bulletsCount);
    }

    @Override
    public int fire() {
        if (getBulletsCount() < BULLET_FIRE){
            return 0;
        }
        super.decreaseBullets(BULLET_FIRE);
        return BULLET_FIRE;
    }
}
