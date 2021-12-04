package farmville;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class FarmvilleTests {
    private Farm farm;
    private Animal animal1;
    private Animal animal2;

    @Before
    public void set(){
        this.animal1 = new Animal("cow", 20);
        this.animal2 = new Animal("chicken", 5);
        this.farm = new Farm("Farm", 10);
    }

    @Test(expected = NullPointerException.class)
    public void setNullForNamePerFarm(){
        Farm farm = new Farm(null, 10);
    }

    @Test(expected = NullPointerException.class)
    public void setEmptyNamePerFarm(){
        Farm farm = new Farm("   ", 10);
    }

    @Test
    public void setCorrectNamePerFarm(){
        Assert.assertEquals("Farm", farm.getName());
    }

    @Test(expected = IllegalArgumentException.class)
    public void testSetZeroCapacity(){
        Farm farm = new Farm("New", -1);
    }

    @Test
    public void testSetCorrectCapacity(){
        Assert.assertEquals(10, farm.getCapacity());
    }

    @Test
    public void testRemoveAnimalReturnTrue(){
        farm.add(animal1);
        farm.add(animal2);
        Assert.assertTrue(farm.remove(animal1.getType()));
    }

    @Test
    public void testRemoveAnimalReturnFalse(){
        farm.add(animal1);
        farm.add(animal2);
        Assert.assertFalse(farm.remove("dog"));
    }

    @Test(expected = IllegalArgumentException.class)
    public void testAddMethodWhenCapacityIsFull(){
        Farm farm = new Farm("Farm", 1);
        farm.add(animal1);
        farm.add(animal2);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testAddMethodWhenAnimalExist(){
        farm.add(animal1);
        farm.add(animal1);
    }

    @Test
    public void testCorrectAddAnimal(){
        farm.add(animal1);
        farm.add(animal2);
        Assert.assertEquals(2, farm.getCount());
    }

    

}
