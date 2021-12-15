package aquarium;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class AquariumTests {
    private Aquarium aquarium;
    private Fish fish1;
    private Fish fish2;

    @Before
    public void set() {
        this.fish1 = new Fish("Teo");
        this.fish2 = new Fish("Deo");
        this.aquarium = new Aquarium("Aquarium", 50);
    }

    @Test(expected = NullPointerException.class)
    public void testSetNullName() {
        Aquarium aquarium = new Aquarium(null, 60);
    }

    @Test(expected = NullPointerException.class)
    public void testSetEmptyName() {
        Aquarium aquarium = new Aquarium("   ", 60);
    }

    @Test
    public void testCorrectName() {
        Assert.assertEquals("Aquarium", aquarium.getName());
        Assert.assertEquals("Teo", fish1.getName());
    }

    @Test(expected = IllegalArgumentException.class)
    public void testSetNegativeCapacity() {
        Aquarium aquarium = new Aquarium("name", -7);
    }

    @Test
    public void testCorrectCapacity() {
        Assert.assertEquals(50, aquarium.getCapacity());
    }

    @Test(expected = IllegalArgumentException.class)
    public void testAddFishInFullAquarium() {
        Aquarium aquarium = new Aquarium("name", 1);
        aquarium.add(fish1);
        aquarium.add(fish2);
    }

    @Test
    public void testCorrectlyAddFish() {
        aquarium.add(fish1);
        aquarium.add(fish2);
        Assert.assertEquals(2, aquarium.getCount());
    }

    @Test(expected = IllegalArgumentException.class)
    public void testRemoveNoExistFish() {
        aquarium.remove(fish1.getName());
    }

    @Test
    public void testRemoveCorrectlyFish() {
        aquarium.add(fish1);
        aquarium.remove(fish1.getName());
        Assert.assertEquals(0, aquarium.getCount());
    }

    @Test(expected = IllegalArgumentException.class)
    public void testSellNoExistFish() {
        aquarium.sellFish(fish1.getName());
    }

    @Test
    public void testSellFishCorrectly() {
        aquarium.add(fish1);
        Assert.assertEquals(fish1, aquarium.sellFish(fish1.getName()));
        Assert.assertFalse(fish1.isAvailable());
    }

    @Test
    public void testReport(){
        aquarium.add(fish1);
        aquarium.add(fish2);
        String actual = aquarium.report();
        Assert.assertEquals("Fish available at Aquarium: Teo, Deo", actual);
    }


}

