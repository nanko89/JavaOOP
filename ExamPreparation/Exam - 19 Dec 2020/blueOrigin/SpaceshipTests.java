package blueOrigin;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class SpaceshipTests {
    private Spaceship spaceship;

    @Before
    public void set(){
        spaceship = new Spaceship("Orange", 3);
    }

    @Test(expected = NullPointerException.class)
    public void testSetNullName(){
        new Spaceship(null, 3);
    }

    @Test(expected = NullPointerException.class)
    public void testSetEmptyName(){
        new Spaceship("   ", 3);

    }

    @Test(expected = IllegalArgumentException.class)
    public void testSetBehindZeroCapacity(){
        new Spaceship("Blue", -5);
    }

    @Test
    public void testRemoveAstronautWithNullName(){
        spaceship.add(new Astronaut("Teo", 10));
        Assert.assertFalse(spaceship.remove("Deo"));
    }

    @Test
    public void testRemoveAstronautWithName(){
        spaceship.add(new Astronaut("Teo", 10));
        Assert.assertTrue(spaceship.remove("Teo"));
    }

    @Test(expected = IllegalArgumentException.class)
    public void testTryToAddEqualsCountAstronautToCapacity(){
        Spaceship spaceship = new Spaceship("Blue", 1);
        spaceship.add(new Astronaut("Teo", 10));
        spaceship.add(new Astronaut("Deo", 10));
    }

    @Test(expected = IllegalArgumentException.class)
    public void testTryToAddEqualsAstronaut() {
    spaceship.add(new Astronaut("Teo", 10));
    spaceship.add(new Astronaut("Teo", 10));
    }

    @Test
    public void testGetCountAstronaut(){
        Assert.assertEquals(0, spaceship.getCount());
    }

    @Test
    public void testTryToAddAstronaut() {
        spaceship.add(new Astronaut("Teo", 10));
        spaceship.add(new Astronaut("Deo", 10));
        Assert.assertEquals(2, spaceship.getCount());
    }

}
