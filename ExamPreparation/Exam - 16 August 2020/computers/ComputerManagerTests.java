package computers;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class ComputerManagerTests {
    private ComputerManager computerManager;
    private Computer computer;
    private Computer computer2;

    @Before
    public void set() {
        computerManager = new ComputerManager();
        computer = new Computer("Dell", "A6060", 800);
        computer2 = new Computer("Asus", "RN60", 500);
    }

    @Test(expected = UnsupportedOperationException.class)
    public void testGetComputerManager(){
        this.computerManager.getComputers().remove(0);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testThrowExceptionAddMethod(){
        this.computerManager.addComputer(null);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testSecondAdd(){
        this.computerManager.addComputer(computer);
        this.computerManager.addComputer(computer);
    }

    @Test
    public void testAddComputer(){
        this.computerManager.addComputer(computer);
        this.computerManager.addComputer(computer2);
        Assert.assertEquals(2, computerManager.getCount());
    }

    @Test(expected = IllegalArgumentException.class)
    public void testGetComputerByNullManufacturer(){
        computerManager.getComputer("null" , "AX45");
    }

    @Test(expected = IllegalArgumentException.class)
    public void testGetComputerByNullModel(){
        computerManager.getComputer("Dell" , "null");
    }

    @Test(expected = IllegalArgumentException.class)
    public void testNonExisting(){
        computerManager.getComputer(computer.getManufacturer() , computer.getModel());
    }

    @Test
    public void testGetComputer(){
        this.computerManager.addComputer(computer);
        Computer test = computerManager.getComputer(computer.getManufacturer(), computer.getModel());
        Assert.assertEquals(computer, test);
    }

    @Test
    public void testGetComputersByManufacturer(){
        this.computerManager.addComputer(computer);
        this.computerManager.addComputer(computer2);
        List<Computer> computersByManufacturer = computerManager
                .getComputersByManufacturer(computer.getManufacturer());
        Assert.assertNotNull(computersByManufacturer);
        Assert.assertEquals(1, computersByManufacturer.size());
        Assert.assertEquals(computersByManufacturer.get(0).getManufacturer(), computer.getManufacturer());
    }

    @Test
    public void testRemoveComputer(){
        computerManager.addComputer(computer);
        computerManager.removeComputer(computer.getManufacturer(), computer.getModel());
        Assert.assertEquals(0,computerManager.getComputers().size());
    }

}