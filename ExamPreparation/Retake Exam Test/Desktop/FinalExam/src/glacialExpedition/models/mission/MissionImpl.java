package glacialExpedition.models.mission;

import glacialExpedition.models.explorers.Explorer;
import glacialExpedition.models.states.State;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

public class MissionImpl implements Mission {
    @Override
    public void explore(State state, Collection<Explorer> explorers) {

        for (Explorer explorer : explorers) {
            while (explorer.canSearch()) {
            List<String> exhibits = (List<String>) state.getExhibits();
                for (int i = 0; i < exhibits.size(); i++) {

                    String item = exhibits.get(i);
                    explorer.getSuitcase().getExhibits().add(item);
                    exhibits.remove(i);
                    i--;
                    explorer.search();
                    if (!explorer.canSearch()){
                        break;
                    }
                }
                if (exhibits.isEmpty()){
                    break;

                }            }
        }
    }
}
