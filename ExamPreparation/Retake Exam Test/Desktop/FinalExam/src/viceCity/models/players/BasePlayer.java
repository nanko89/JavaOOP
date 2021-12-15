package viceCity.models.players;

import viceCity.models.guns.Gun;
import viceCity.repositories.interfaces.GunRepository;
import viceCity.repositories.interfaces.Repository;

import static viceCity.common.ExceptionMessages.*;

public abstract class BasePlayer implements Player{
    private String name;
    private int lifePoints;
    private Repository<Gun> gunRepository;

    protected BasePlayer(String name, int lifePoints) {
        this.setName(name);
        this.setLifePoints(lifePoints);
        this.gunRepository = new GunRepository();

    }

    protected void setName(String name) {
        if (name == null || name.trim().isEmpty()){
            throw new NullPointerException(PLAYER_NULL_USERNAME);
        }
        this.name = name;
    }

    protected void setLifePoints(int lifePoints) {
        if (lifePoints < 0){
            throw new IllegalArgumentException(PLAYER_LIFE_POINTS_LESS_THAN_ZERO);
        }
        this.lifePoints = lifePoints;
    }

    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public int getLifePoints() {
        return this.lifePoints;
    }

    @Override
    public boolean isAlive() {
        return lifePoints > 0 ;
    }

    @Override
    public Repository<Gun> getGunRepository() {
        return gunRepository;
    }

    @Override
    public void takeLifePoints(int points) {
        if (this.lifePoints - points < 0){
            setLifePoints(0);
            isAlive();
        }else {
            this.lifePoints -= points;
        }
    }
}
