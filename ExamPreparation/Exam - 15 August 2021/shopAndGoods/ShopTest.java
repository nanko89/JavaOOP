package shopAndGoods;


import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import javax.naming.OperationNotSupportedException;

public class ShopTest {
    private Shop shop;
    private Goods goods1;
    private Goods goods2;

    @Before
    public void set() {
        this.shop = new Shop();
        this.goods1 = new Goods("Meat", "3800");
        this.goods2 = new Goods("Chocolad", "2800");
    }

    @Test(expected = IllegalArgumentException.class)
    public void testAddGoodWhenNotExistShelf() throws OperationNotSupportedException {
        this.shop.addGoods("NoShelf", new Goods("name", "1"));
    }

    @Test(expected = IllegalArgumentException.class)
    public void testExistNotNullShelf() throws OperationNotSupportedException {
        this.shop.addGoods("Shelves7", goods1);
        this.shop.addGoods("Shelves7", goods2);
    }

    @Test(expected = OperationNotSupportedException.class)
    public void testExistItemThrow() throws OperationNotSupportedException {
        this.shop.addGoods("Shelves7", goods1);
        this.shop.addGoods("Shelves1", goods1);
    }

    @Test
    public void addGoodsRegular() throws OperationNotSupportedException {

        String expected =  "Goods: 2800 is placed successfully!";
        String actual = this.shop.addGoods("Shelves1", goods2);
        Assert.assertEquals(expected, actual);
        Assert.assertEquals(goods2, this.shop.getShelves().get("Shelves1"));
    }

    @Test(expected = IllegalArgumentException.class)
    public void testRemoveWhenShelfDoesNotExist() {
        this.shop.removeGoods("NoShelf", goods1);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testRemoveWhenGoodsDoesNotExist() throws OperationNotSupportedException {
        this.shop.addGoods("Shelves1", goods1);
        this.shop.removeGoods("Shelves1", goods2);
    }

    @Test
    public void testRemoveGoods() throws OperationNotSupportedException {
        this.shop.addGoods("Shelves1", goods1);
        String actual =  this.shop.removeGoods("Shelves1", goods1);
        String expected = "Goods: 3800 is removed successfully!";
        Assert.assertEquals(expected,actual);
        Assert.assertNull(this.shop.getShelves().get("Shelves1"));

    }
}