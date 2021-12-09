package viceCity.models.guns;

public class Rifle extends BaseGun{

    private static final int TOTAL_BULLETS = 500;
    private static final int BULLETS_PER_BARREL = 50;
    private static final int FIRE = 5;

    public Rifle(String name) {
        super(name, BULLETS_PER_BARREL, TOTAL_BULLETS);
    }

    @Override
    public int fire() {
        if (getBulletsPerBarrel() == 0 && getTotalBullets() > 0) {
            setTotalBullets(getTotalBullets() - BULLETS_PER_BARREL);
            setBulletsPerBarrel(BULLETS_PER_BARREL);
        }

        if (getBulletsPerBarrel() > 0) {
            setBulletsPerBarrel(getBulletsPerBarrel() - FIRE);
        }

        return FIRE;
    }
}
