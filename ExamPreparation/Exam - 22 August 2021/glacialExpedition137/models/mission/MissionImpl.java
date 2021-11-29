package glacialExpedition137.models.mission;

import glacialExpedition137.models.explorers.Explorer;
import glacialExpedition137.models.states.State;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

public class MissionImpl implements Mission{
    @Override
    public void explore(State state, Collection<Explorer> explorers) {
        List<Explorer> explorerList = explorers.stream().filter(e -> e.getEnergy() > 0)
                .collect(Collectors.toList());
        for (int index = 0; index < explorerList.size(); index++) {
            Explorer explorer = explorerList.get(index);

            for (int indexState = 0; indexState < state.getExhibits().size(); indexState++) {
                String exhibits = state.getExhibits().get(indexState);
                explorer.search();
                explorer.getSuitcase().getExhibits().add(exhibits);
                state.getExhibits().remove(indexState);
                indexState--;
                if (!explorer.canSearch()){
                    explorerList.remove(index);
                    index--;
                    break;
                }
            }
        }


    }
}
