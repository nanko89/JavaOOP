package cats;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class HouseTests {
    private Cat cat1;
    private Cat cat2;
    private House house;

    @Before
    public void set() {
        this.cat1 = new Cat("Cat");
        this.cat2 = new Cat("Tom");
        this.house = new House("Home", 10);
    }

    @Test(expected = NullPointerException.class)
    public void testSetNameWithNull() {
        House house = new House(null, 10);
    }

    @Test(expected = NullPointerException.class)
    public void testSetNameEmpty() {
        House house = new House("   ", 10);
    }

    @Test
    public void testSetNameCorrect() {
        Assert.assertEquals("Home", house.getName());
    }

    @Test(expected = IllegalArgumentException.class)
    public void testSetCapacityNull() {
        House house = new House("Home", -1);
    }

    @Test
    public void testSetCapacityCorrect() {
        Assert.assertEquals(10, house.getCapacity());
    }

    @Test(expected = IllegalArgumentException.class)
    public void addCatWhenHouseIsFull(){
        House house = new House("House", 1);
        house.addCat(cat1);
        house.addCat(cat2);
    }

    @Test
    public void testAddCatCorrectly(){
        house.addCat(cat2);
        house.addCat(cat1);
        Assert.assertEquals(2, house.getCount());
    }

    @Test(expected = IllegalArgumentException.class)
    public void testRemoveCatNotExist(){
        house.removeCat(cat1.getName());

    }



    @Test
    public void testRemoveCatCorrectly(){
        house.addCat(cat1);
        house.removeCat(cat1.getName());
        Assert.assertEquals(0, house.getCount());

    }

    @Test(expected = IllegalArgumentException.class)
    public void testCatForSaleNoExist(){
        house.catForSale(cat1.getName());
    }

    @Test
    public void testCorrectSaleCat(){
        house.addCat(cat1);
        Assert.assertEquals(cat1, house.catForSale(cat1.getName()));
        Assert.assertFalse(cat1.isHungry());
    }

    @Test
    public void testStatistic(){
        house.addCat(cat2);
        house.addCat(cat1);
        String expected = "The cat Tom, Cat is in the house Home!";
        Assert.assertEquals(expected, house.statistics());

    }

}
