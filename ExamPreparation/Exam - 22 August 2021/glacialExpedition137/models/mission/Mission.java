package glacialExpedition137.models.mission;

import glacialExpedition137.models.explorers.Explorer;
import glacialExpedition137.models.states.State;


import java.util.Collection;

public interface Mission {
    void explore(State state, Collection<Explorer> explorers);
}
