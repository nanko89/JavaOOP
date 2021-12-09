package halfLife;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.Map;

public class PlayerTests {
    private Player player;
    private Gun gun1;
    private Gun gun2;

    @Before
    public void set(){
        this.player = new Player("Nanko", 100);
        this.gun1 = new Gun("Makarov", 12);
        this.gun2 = new Gun("Kalashnikov" ,50);
    }

    @Test(expected = NullPointerException.class)
    public void testSetInvalidNameWithNull(){
        Player player = new Player(null, 50);
    }

    @Test(expected = NullPointerException.class)
    public void testSetInvalidNameEmpty(){
        Player player = new Player("    ", 50);
    }

    @Test
    public void testSetValidName(){
        Assert.assertEquals("Nanko", player.getUsername());
    }

    @Test(expected = IllegalArgumentException.class)
    public void testSetInvalidHealthNull(){
        Player player = new Player("Pesho", -40);
    }

    @Test
    public void testSetValidHealth(){
        Assert.assertEquals(100, player.getHealth());
    }

    @Test
    public void testTakeDamage(){
        player.takeDamage(50);
        Assert.assertEquals(50, player.getHealth());
        player.takeDamage(150);
        Assert.assertEquals(0, player.getHealth());
    }

    @Test(expected = NullPointerException.class)
    public void testAddGunWithValueNull(){
       player.addGun(null);
    }

    @Test
    public void testAddGun(){
        player.addGun(gun1);
        player.addGun(gun2);
        Assert.assertEquals(2, player.getGuns().size());
    }

    @Test
    public void testRemoveGun(){
        player.addGun(gun1);
        Assert.assertFalse(player.removeGun(gun2));
        Assert.assertTrue(player.removeGun(gun1));
    }

    @Test
    public void testGetGunWithNullByName(){
        Assert.assertNull(this.player.getGun("Nanko"));
    }

    @Test
    public void testCanGetGunByName(){
        player.addGun(gun1);
        player.addGun(gun2);
        Assert.assertEquals(gun1, player.getGun(gun1.getName()));
    }


}
