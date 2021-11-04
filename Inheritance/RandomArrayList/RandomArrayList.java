package RandomArrayList;

import java.util.ArrayList;
import java.util.Random;

public class RandomArrayList<T> extends ArrayList<T> {
    public T getRandomElement() {

        Random rang = new Random();
        int randIndex = rang.nextInt(super.size());
        return remove(randIndex);
    }
}
