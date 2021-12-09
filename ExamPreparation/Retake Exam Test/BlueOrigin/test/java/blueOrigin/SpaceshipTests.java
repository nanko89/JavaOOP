package blueOrigin;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class SpaceshipTests {
    private Spaceship spaceship;
    private Astronaut astronaut;
    private Astronaut astronaut2;

    @Before
    public void set(){
        this.spaceship = new Spaceship("Novo", 50);
        this.astronaut = new Astronaut("Nanko", 20.0);
        this.astronaut2 = new Astronaut("Cecko", 10.0);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testTryToAddAstronautWhenNoCapacity(){
        Spaceship spaceship = new Spaceship("N", 1);
        spaceship.add(astronaut);
        spaceship.add(astronaut2);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testTryToAddExistAstronaut(){
        spaceship.add(astronaut);
        spaceship.add(astronaut);
    }

    @Test
    public void testAddAstronautCorrect(){
        spaceship.add(astronaut);
        spaceship.add(astronaut2);
        Assert.assertEquals(2, spaceship.getCount());
    }

    @Test
    public void testRemoveAstronaut(){
        spaceship.add(astronaut);
        Assert.assertTrue(spaceship.remove(astronaut.getName()));
        Assert.assertFalse(spaceship.remove(astronaut2.getName()));
    }

    @Test(expected = IllegalArgumentException.class)
    public void testSettZeroCapacity(){
        Spaceship spaceship = new Spaceship("nani", -5);
    }

    @Test(expected = NullPointerException.class)
    public void testSetNullName(){
        Spaceship spaceship = new Spaceship(null , 10);
    }

    @Test(expected = NullPointerException.class)
    public void testSetEmptyName(){
        Spaceship spaceship = new Spaceship("  " , 10);
    }

    @Test
    public void testGetName(){
        Assert.assertEquals("Novo", spaceship.getName());
    }

}
