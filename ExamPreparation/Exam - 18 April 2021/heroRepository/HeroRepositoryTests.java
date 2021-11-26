package heroRepository;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class HeroRepositoryTests {
    private Hero hero;
    private HeroRepository heroRepository;

    @Before
    public void set() {
        hero = new Hero("Nani", 12);
        heroRepository = new HeroRepository();
    }

    @Test(expected = NullPointerException.class)
    public void testCreateWithNullHero(){
        heroRepository.create(null);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testTryToCreateHeroWhenExist(){
        heroRepository.create(hero);
        heroRepository.create(hero);
    }

    @Test
    public void testCreateHero(){
        heroRepository.create(hero);
    }

    @Test(expected = NullPointerException.class)
    public void testRemoveHeroWhenNameIsNull(){
        heroRepository.remove(null);
        heroRepository.remove("  ");
    }

    @Test(expected = NullPointerException.class)
    public void testRemoveHeroWhenNameEmpty(){
        heroRepository.remove("  ");
    }

    @Test
    public void testRemoveHero(){
        heroRepository.remove(hero.getName());
    }

    @Test
    public void testGetHeroGetCount(){
        heroRepository.create(hero);
        Assert.assertEquals(heroRepository.getCount(), heroRepository.getHeroes().size());
    }

    @Test
    public void testGetHeroWithHighestLevel(){
        heroRepository.create(hero);
        Hero betterHero = new Hero("Cecko", 30);
        heroRepository.create(betterHero);
        Assert.assertEquals(heroRepository.getHeroWithHighestLevel(), betterHero);
    }

    @Test
    public void testGetHero(){
        heroRepository.create(hero);
        Assert.assertSame(heroRepository.getHero(hero.getName()), hero);
        heroRepository.remove(hero.getName());
        Assert.assertNull(heroRepository.getHero(hero.getName()));
    }
}
