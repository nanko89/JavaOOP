package halfLife;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class PlayerTests {
    private Player player;
    private Gun gun1;
    private Gun gun2;

    @Before
    public void set() {
        this.player = new Player("Nanko", 100);
        this.gun1 = new Gun("Makarov", 12);
        this.gun2 = new Gun("Remington ", 50);
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

    @Test
    public void testRemoveGunReturnTrue(){
        player.addGun(gun1);
        Assert.assertTrue(player.removeGun(gun1));
    }

    @Test
    public void testRemoveGunReturnFalse(){
        Assert.assertFalse(player.removeGun(gun1));
    }

    @Test(expected = NullPointerException.class)
    public void testWhenCanNotAddNullGun(){
        player.addGun(null);
    }

    @Test
    public void testAddGun(){
        player.addGun(gun1);
        player.addGun(gun2);
        Assert.assertEquals(2,player.getGuns().size());
    }

    @Test(expected = IllegalArgumentException.class)
    public void testWhenHealthEqualsOrBehindNull(){
        player = new Player("Nani", -10);
    }

   @Test
    public void testGetUserName(){
        Assert.assertEquals("Nanko", this.player.getUsername());
   }

   @Test(expected = NullPointerException.class)
    public void testSetNullName(){
        player = new Player(null, 50);
   }

    @Test(expected = NullPointerException.class)
    public void testSetEmptyName(){
        player = new Player("  ", 50);
    }

    @Test
    public void testGetHealth(){
        Assert.assertEquals(100, player.getHealth());
    }

    @Test
    public void testTakeDamage(){
        player.takeDamage(50);
        Assert.assertEquals(50, player.getHealth());
    }

    @Test
    public void testTakeDamageWhenResultIsNegative(){
        player.takeDamage(150);
        Assert.assertEquals(0, player.getHealth());
    }

}
