package bankSafe;


import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import javax.naming.OperationNotSupportedException;

public class BankVaultTest {
    private BankVault bankVault;
    private Item existItem;
    private Item nonExistItem;

    @Before
    public void set() {
        bankVault = new BankVault();
        existItem = new Item("Gold", "C1");
        nonExistItem = new Item("Silver", "N60");
    }

    @Test(expected = IllegalArgumentException.class)
    public void testAddMethodWhenCellDoesntExist() throws OperationNotSupportedException {
        this.bankVault.addItem("N60", nonExistItem);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testAddMethodWhenCellIsAlreadyTaken() throws OperationNotSupportedException {
        this.bankVault.addItem("C1", existItem);
        this.bankVault.addItem("C1", nonExistItem);
    }

    @Test
    public void testIfItemExist() throws OperationNotSupportedException {
        String actual =  this.bankVault.addItem("C1", existItem);
        String expected = "Item:C1 saved successfully!";
        Assert.assertEquals(expected, actual);
        Assert.assertTrue(this.bankVault.getVaultCells().containsValue(existItem));
    }

    @Test(expected = OperationNotSupportedException.class)
    public void testThrowExceptionWhenItemExist() throws OperationNotSupportedException {

        this.bankVault.addItem("C1", existItem);
        this.bankVault.addItem("C2", existItem);

    }

    @Test(expected = IllegalArgumentException.class)
    public void testRemoveNotExistValueCell() {
        bankVault.removeItem("N60", nonExistItem);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testRemoveNotExistItemCell() {
        bankVault.removeItem("C1", nonExistItem);
    }

    @Test
    public void testRemoveExistItem() throws OperationNotSupportedException {
        bankVault.addItem("C1", existItem);
        String actual = bankVault.removeItem("C1", existItem);
        String expected = "Remove item:C1 successfully!";
        Assert.assertEquals(expected, actual);
        Assert.assertNull(bankVault.getVaultCells().get("C1"));
    }

    @Test
    public void testSetOwner() throws OperationNotSupportedException {
        Assert.assertEquals("Gold", existItem.getOwner());
    }

}