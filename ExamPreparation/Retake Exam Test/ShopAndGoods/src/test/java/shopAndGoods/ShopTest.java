package shopAndGoods;


import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import javax.naming.OperationNotSupportedException;

public class ShopTest {
    private Goods good1;
    private Goods good2;
    private Shop shop;

    @Before
    public void set() {
        this.good1 = new Goods("Milk", "3800");
        this.good2 = new Goods("Rice", "2800");
        this.shop = new Shop();
    }

    @Test
    public void testKeysInShop() {
        Assert.assertTrue(shop.getShelves().containsKey("Shelves1"));
        Assert.assertTrue(shop.getShelves().containsKey("Shelves9"));
    }

    @Test(expected = UnsupportedOperationException.class)
    public void testUnmodifiableMap() {
        shop.getShelves().put("Chocolate", good1);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testAddGoodsDoNotExistShelf() throws OperationNotSupportedException {
        shop.addGoods("Table", good1);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testAddGoodsWhenShelfIsAlreadyTaken() throws OperationNotSupportedException {
        shop.addGoods("Shelves1", good1);
        shop.addGoods("Shelves1", good2);
    }

    @Test(expected = OperationNotSupportedException.class)
    public void testAddGoodWhenAlreadyExist() throws OperationNotSupportedException {
        shop.addGoods("Shelves1", good1);
        shop.addGoods("Shelves2", good1);
    }

    @Test
    public void testAddGoodCorrectly() throws OperationNotSupportedException {
        String expected = "Goods: 3800 is placed successfully!";
        Assert.assertEquals(expected, shop.addGoods("Shelves1", good1));
        Assert.assertEquals(good1.getName(), shop.getShelves().get("Shelves1").getName());
        Assert.assertEquals(good1, shop.getShelves().get("Shelves1"));
    }

    @Test(expected = IllegalArgumentException.class)
    public void testRemoveGoodsDoNotExistShelf() throws OperationNotSupportedException {
        shop.removeGoods("Table", good1);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testRemoveGoodsDoNotExistInThatShelf() throws OperationNotSupportedException {
        shop.removeGoods("Shelves8", good1);
    }

    @Test
    public void testRemoveGoodsCorrectly() throws OperationNotSupportedException {
        shop.addGoods("Shelves8", good1);
        String expected = "Goods: 3800 is removed successfully!";
        Assert.assertEquals(expected, shop.removeGoods("Shelves8", good1));
        Assert.assertNull(shop.getShelves().get("Shelves8"));
    }
}