package aquarium;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import javax.swing.event.ListDataEvent;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class AquariumTests {
private Fish f;
private Fish f1;
private Aquarium aquarium;


    @Before
    public void set(){
        this.f = new Fish("name");
        this.f1 = new Fish("name1");
        this.aquarium = new Aquarium("aqua", 10);
    }


    @Test(expected = IllegalArgumentException.class)
    public void testInvalidCapacitySet(){
        Aquarium aquarium = new Aquarium("tank", -5);
    }

    @Test
    public void testCapacitySet(){
        aquarium.add(f);
        aquarium = new Aquarium("tank", 5 );
        Assert.assertEquals(5, aquarium.getCapacity());
    }

    @Test(expected = NullPointerException.class)
    public void testSetNullName(){
        aquarium = new Aquarium(null, 10);
    }

    @Test(expected = NullPointerException.class)
    public void testSetEmptyName(){
        aquarium = new Aquarium("  ", 10);
    }

    @Test
    public void testSetName(){
        Assert.assertEquals("aqua", aquarium.getName());
    }

    @Test(expected = IllegalArgumentException.class)
    public void testWhenAquariumIsFullToAddFish(){
        aquarium = new Aquarium("blue", 1);
        aquarium.add(f);
        aquarium.add(f1);
    }

    @Test
    public void testAddFish(){
        aquarium.add(f);
        aquarium.add(f1);
        Assert.assertEquals(2, aquarium.getCount());

    }

    @Test(expected = IllegalArgumentException.class)
    public void testWhenRemoveNullFish(){
        aquarium.remove("fish");
    }

    @Test
    public void testRemoveFish(){
        aquarium.add(f);
        aquarium.remove(f.getName());
    }

    @Test(expected = IllegalArgumentException.class)
    public void testWhenSellNullFish(){
        aquarium.sellFish(null);
    }

    @Test
    public void testSellFish(){
        aquarium.add(f);
        aquarium.sellFish(f.getName());
        Assert.assertFalse(f.isAvailable());
    }

    @Test
    public void testReport(){
        aquarium.add(f);
        aquarium.add(f1);
        aquarium.report();
        String expected = "Fish available at aqua: name, name1";
        String actual = aquarium.report();;
        Assert.assertEquals(expected,actual);
    }


}

