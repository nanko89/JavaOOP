package barracksWars.models.units;

public class Gunner extends AbstractUnit{
    private static final int GUNNER_HEALTH = 25;
    private static final int GUNNER_DAMAGE = 7;


    public Gunner() {
        super(GUNNER_HEALTH, GUNNER_DAMAGE);
    }
}
