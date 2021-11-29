package glacialExpedition137.core;

import glacialExpedition137.models.explorers.AnimalExplorer;
import glacialExpedition137.models.explorers.Explorer;
import glacialExpedition137.models.explorers.GlacierExplorer;
import glacialExpedition137.models.explorers.NaturalExplorer;
import glacialExpedition137.models.mission.Mission;
import glacialExpedition137.models.mission.MissionImpl;
import glacialExpedition137.models.states.State;
import glacialExpedition137.models.states.StateImpl;
import glacialExpedition137.repositories.ExplorerRepository;
import glacialExpedition137.repositories.StateRepository;

import java.util.List;
import java.util.stream.Collectors;

import static glacialExpedition137.common.ConstantMessages.*;
import static glacialExpedition137.common.ExceptionMessages.*;

public class ControllerImpl implements Controller {
    private ExplorerRepository explorerRepository;
    private StateRepository stateRepository;
    private int exploredStateCount;

    public ControllerImpl() {
        this.explorerRepository = new ExplorerRepository();
        this.stateRepository = new StateRepository();
    }

    @Override
    public String addExplorer(String type, String explorerName) {
        Explorer explorer;

        switch (type) {
            case "NaturalExplorer":
                explorer = new NaturalExplorer(explorerName);
                break;
            case "GlacierExplorer":
                explorer = new GlacierExplorer(explorerName);
                break;
            case "AnimalExplorer":
                explorer = new AnimalExplorer(explorerName);
                break;
            default:
                throw new IllegalArgumentException(EXPLORER_INVALID_TYPE);

        }
        this.explorerRepository.add(explorer);
        return String.format(EXPLORER_ADDED, type, explorerName);
    }

    @Override
    public String addState(String stateName, String... exhibits) {
        List<String> exhibit = List.of(exhibits);
        State state = new StateImpl(stateName);
        state.getExhibits().addAll(exhibit);
        this.stateRepository.add(state);
        return String.format(STATE_ADDED, stateName);
    }

    @Override
    public String retireExplorer(String explorerName) {
        if (explorerRepository.getCollection().stream().noneMatch(e -> e.getName().equals(explorerName))) {
            throw new IllegalArgumentException(String.format(EXPLORER_DOES_NOT_EXIST, explorerName));
        }
        Explorer explorer = explorerRepository.getCollection().stream()
                .filter(e -> e.getName().equals(explorerName))
                .findFirst().orElse(null);
        explorerRepository.remove(explorer);
        return String.format(EXPLORER_RETIRED, explorerName);
    }

    @Override
    public String exploreState(String stateName) {
        List<Explorer> explorers = explorerRepository.getCollection().stream()
                .filter(e -> e.getEnergy() > 50)
                .collect(Collectors.toList());
        if (explorers.isEmpty()) {
            throw new IllegalArgumentException(STATE_EXPLORERS_DOES_NOT_EXISTS);
        }

        int startExplorers = explorers.size();

        Mission mission = new MissionImpl();

        State state = stateRepository.getCollection().stream()
                .filter(s -> s.getName().equals(stateName)).findFirst().orElse(null);

        mission.explore(state, explorers);
        exploredStateCount++;

        int endExplorer = explorers.size();

        return String.format(STATE_EXPLORER, stateName, startExplorers - endExplorer);
    }

    @Override
    public String finalResult() {
        StringBuilder sb = new StringBuilder();

        sb.append(String.format(FINAL_STATE_EXPLORED, exploredStateCount))
                .append(System.lineSeparator());
        sb.append(FINAL_EXPLORER_INFO)
                .append(System.lineSeparator());

        for (Explorer explorer : explorerRepository.getCollection()) {

            String suitcase = explorer.getSuitcase().getExhibits().isEmpty()
                    ? String.format(FINAL_EXPLORER_SUITCASE_EXHIBITS, "None")
                    : String.format(FINAL_EXPLORER_SUITCASE_EXHIBITS, explorer.getSuitcase()
                    .getExhibits()
                    .stream().map(String::new)
                    .collect(Collectors.joining(FINAL_EXPLORER_SUITCASE_EXHIBITS_DELIMITER)));

        sb.append(String.format(FINAL_EXPLORER_NAME, explorer.getName()))
                .append(System.lineSeparator());
        sb.append(String.format(FINAL_EXPLORER_ENERGY, explorer.getEnergy()))
                .append(System.lineSeparator());
        sb.append(suitcase)
                .append(System.lineSeparator());

        }
        return sb.toString().trim();
    }
}
