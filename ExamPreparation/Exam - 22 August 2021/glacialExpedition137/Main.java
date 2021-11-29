package glacialExpedition137;

import glacialExpedition137.core.Controller;
import glacialExpedition137.core.ControllerImpl;
import glacialExpedition137.core.Engine;
import glacialExpedition137.core.EngineImpl;

public class Main {

    public static void main(String[] args) {
        Controller controller = new ControllerImpl();
        Engine engine = new EngineImpl(controller);
        engine.run();
    }
}
