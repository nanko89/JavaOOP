package barracksWars5.interfaces;

import java.lang.reflect.InvocationTargetException;

public interface Runnable {
	void run() throws ClassNotFoundException, InvocationTargetException,
			NoSuchMethodException, InstantiationException, IllegalAccessException;
}
