package farmville;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class FarmvilleTests {
    private List<Animal> animals;
    private Animal animal1;
    private Animal animal2;
    private Animal animal3;
    private Farm farm;

    @Before
    public void set(){
        animal1 = new Animal("Cow", 10.0);
        animal2 = new Animal("Sheep", 5.0);
        animal3 = new Animal("Cow", 8.0);
        farm = new Farm("MyFarm", 2);
        animals = new ArrayList<>();
    }


    @Test(expected = NullPointerException.class)
    public void testFarmNameEqualsNull(){
        farm = new Farm(null, 5);
    }

    @Test(expected = NullPointerException.class)
    public void testFarmNameIsEmpty(){
        farm = new Farm("    ", 5);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testFarmSetNullCapacity(){
        farm = new Farm("Farm" , -1);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testAnimalSizeWhenIsEqualsFarmCapacity(){
        farm.add(animal1);
        farm.add(animal2);
        farm.add(animal3);
    }

    @Test
    public void testAddAnimalInFarm(){
        farm.add(animal2);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testAnimalSizeWhenIsEqualsAnimalType(){
       farm.add(animal1);
       farm.add(animal3);
    }

    @Test
    public void testRemoveAnimalFromFarm(){
        farm.add(animal1);
        Assert.assertTrue(farm.remove(animal1.getType()));
        Assert.assertFalse(farm.remove(animal1.getType()));
    }

    @Test
    public void testGetAnimalsCount(){
        farm.add(animal1);
        Assert.assertEquals(farm.getCount(), 1);
    }

    @Test
    public void testFarmName(){
        Assert.assertEquals(farm.getName(), "MyFarm");
    }

    @Test
    public void testFarmCount(){
        farm.add(animal1);
        Assert.assertEquals(1,farm.getCount() );
    }

    @Test
    public void testGetEnergyAnimal(){
        animals.add(animal1);
        Assert.assertEquals(10.0, animal1.getEnergy(), 0.0);
    }


}
