package CounterStriker.models.players;

import CounterStriker.models.guns.Gun;

import static CounterStriker.common.ExceptionMessages.*;

public abstract class PlayerImpl implements Player {
    private String username;
    private int health;
    private int armor;
    private boolean isAlive;
    private Gun gun;

    protected PlayerImpl(String username, int health, int armor, Gun gun) {
        this.setUsername(username);
        this.setHealth(health);
        this.setArmor(armor);
        this.setGun(gun);
    }

    private void setUsername(String username) {
        if (username == null || username.trim().isEmpty()) {
            throw new NullPointerException(INVALID_PLAYER_NAME);
        }
        this.username = username;
    }

    private void setHealth(int health) {
        if (health < 0) {
            this.isAlive = false;
            throw new IllegalArgumentException(INVALID_PLAYER_HEALTH);
        }
        this.isAlive = true;
        this.health = health;
    }

    private void setGun(Gun gun) {
        if (gun == null) {
            throw new NullPointerException(INVALID_GUN);
        }
        this.gun = gun;
    }

    private void setArmor(int armor) {
        if (armor < 0) {
            throw new IllegalArgumentException(INVALID_PLAYER_ARMOR);
        }
        this.armor = armor;
    }


    @Override
    public String getUsername() {
        return this.username;
    }

    @Override
    public int getHealth() {
        return this.health;
    }

    @Override
    public int getArmor() {
        return this.armor;
    }

    @Override
    public Gun getGun() {
        return this.gun;
    }

    @Override
    public boolean isAlive() {
        return this.health > 0;
    }

    @Override
    public void takeDamage(int points) {
//        int damage = points;
//        if (damage >= this.armor){
//            damage -= this.armor;
//            this.armor = 0;
//            this.health -= damage;
//        }else {
//            this.armor -= damage;
//        }
        int damageArmor = this.armor - points;
        if (damageArmor < 0){
            setArmor(0);
            int damageHealth = this.health + damageArmor;
            if (damageHealth <= 0){
                setHealth(0);
                this.isAlive = false;
            }else {
                setHealth(damageHealth);
            }
        }else {
            setArmor(damageArmor);
        }
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(String.format("%s: %s", this.getClass().getSimpleName(), username))
                .append(System.lineSeparator());
        sb.append(String.format("--Health: %d", health)).append(System.lineSeparator());
        sb.append(String.format("--Armor: %d", armor)).append(System.lineSeparator());
        sb.append(String.format("--Gun: %s", gun.getName())).append(System.lineSeparator());
        return  sb.toString();
    }
}
