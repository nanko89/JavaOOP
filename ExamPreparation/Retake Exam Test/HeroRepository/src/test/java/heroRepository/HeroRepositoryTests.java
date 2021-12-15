package heroRepository;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class HeroRepositoryTests {
    private Hero hero1;
    private Hero hero2;
    private HeroRepository heroRepository;

    @Before
    public void set() {
        this.heroRepository = new HeroRepository();
        this.hero1 = new Hero("Nanko", 10);
        this.hero2 = new Hero("Cecko", 20);
    }

    @Test
    public void testCreateCorrectHero() {
        String expected = "Successfully added hero Nanko with level 10";

        Assert.assertEquals(expected, heroRepository.create(hero1));

        expected = "Successfully added hero Cecko with level 20";

        Assert.assertEquals(expected, heroRepository.create(hero2));

        Assert.assertEquals(2, heroRepository.getCount());

    }

    @Test(expected = NullPointerException.class)
    public void testCreateHeroWithNullHero() {
        heroRepository.create(null);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testCreateEqualsHero() {
        heroRepository.create(hero2);
        heroRepository.create(hero2);
    }

    @Test(expected = NullPointerException.class)
    public void testRemoveNullName() {
        heroRepository.remove(null);
    }

    @Test
    public void testRemoveCorrectly() {
        Assert.assertFalse(heroRepository.remove(hero1.getName()));
        heroRepository.create(hero1);
        Assert.assertTrue(heroRepository.remove(hero1.getName()));
    }

    @Test
    public void testGetHeroWithHighestLevel(){
        heroRepository.create(hero1);
        heroRepository.create(hero2);
        Assert.assertEquals(hero2,heroRepository.getHeroWithHighestLevel());
    }

    @Test
    public void testGetHero(){
        heroRepository.create(hero1);
        Assert.assertEquals(hero1, heroRepository.getHero(hero1.getName()));
    }

    @Test(expected = UnsupportedOperationException.class)
    public void testGetHeroes(){
        heroRepository.getHeroes().add(hero2);
    }
}
