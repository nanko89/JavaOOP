package computers;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class ComputerManagerTests {
    private ComputerManager computerManager;
    private Computer computer1;
    private Computer computer2;

    @Before
    public void set(){
        this.computerManager = new ComputerManager();
        this.computer1 = new Computer("HP" , "90201", 1000.0);
        this.computer2 = new Computer("ACER", "100" , 1200.0);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testAddComputerTryToAddNull(){
        computerManager.addComputer(null);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testTryToAddAlreadyExistComputer(){
        computerManager.addComputer(computer1);
        computerManager.addComputer(computer1);
    }

    @Test
    public void testCorrectAddComputer(){
        computerManager.addComputer(computer1);
        computerManager.addComputer(computer2);
        Assert.assertEquals(2, computerManager.getCount());
        Assert.assertEquals("HP", computerManager.getComputers().get(0).getManufacturer());
    }

    @Test
    public void testRemoveComputer(){
        computerManager.addComputer(computer1);
        Assert.assertEquals(computer1, computerManager.removeComputer(computer1.getManufacturer(), computer1.getModel()));
    }

    @Test(expected = IllegalArgumentException.class)
    public void testGetComputerWithNullManufacturer(){
        computerManager.getComputer(null, "5060");
    }

    @Test(expected = IllegalArgumentException.class)
    public void testGetComputerWithNullModel(){
        computerManager.getComputer("HP", null);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testGetComputerWhenNotExist(){
        computerManager.addComputer(computer1);
        computerManager.getComputer(computer2.getManufacturer(), computer2.getModel());
    }

    @Test
    public void testGetComputerCorrect(){
        computerManager.addComputer(computer1);
       Assert.assertEquals(computer1, computerManager.getComputer(computer1.getManufacturer(), computer1.getModel()));
    }

    @Test(expected = IllegalArgumentException.class)
    public void testGetComputerByManufactureWithNullManufacture(){
        computerManager.getComputersByManufacturer(null);
    }

    @Test
    public void testGetComputerByManufacture(){
        computerManager.addComputer(computer1);
        Assert.assertEquals(computerManager.getComputers(), computerManager.getComputersByManufacturer(computer1.getManufacturer()));
    }
}
