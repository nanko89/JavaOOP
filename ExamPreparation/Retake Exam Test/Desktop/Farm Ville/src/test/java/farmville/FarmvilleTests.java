package farmville;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class FarmvilleTests {
    private Animal animal1;
    private Animal animal2;
    private Farm farm;

    @Before
    public void set() {
        this.farm = new Farm("Farm", 10);
        this.animal1 = new Animal("Cow", 100);
        this.animal2 = new Animal("Chicken", 5);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testSetInvalidCapacity(){
        Farm farm = new Farm("Farm", -10);
    }

    @Test
    public void testCorrectlySetCapacity(){
        Assert.assertEquals(10, farm.getCapacity());
    }

    @Test(expected = NullPointerException.class)
    public void testSetNullName(){
        Farm farm = new Farm(null, 10);
    }

    @Test(expected = NullPointerException.class)
    public void testSetEmptyName(){
        Farm farm = new Farm("   ", 10);
    }

    @Test
    public void testSetName(){
       Assert.assertEquals("Farm", farm.getName());
    }

    @Test(expected = IllegalArgumentException.class)
    public void testWhenFarmIsFull(){
        Farm farm = new Farm("Farm", 1);
        farm.add(animal1);
        Assert.assertEquals(farm.getCount(), farm.getCapacity());
        farm.add(animal2);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testAddSameAnimalType(){
        farm.add(animal1);
        farm.add(new Animal("Cow", 10));
    }

    @Test
    public void testCorrectAddAnimal(){
        farm.add(animal1);
        Assert.assertEquals(1, farm.getCount());
        farm.add(animal2);
        Assert.assertEquals(2, farm.getCount());
    }

    @Test
    public void testRemoveAnimalTrue(){
        farm.add(animal1);
        Assert.assertTrue(farm.remove(animal1.getType()));
    }

    @Test
    public void testRemoveAnimalFalse(){
        farm.add(animal1);
        Assert.assertFalse(farm.remove(animal2.getType()));
    }



}
