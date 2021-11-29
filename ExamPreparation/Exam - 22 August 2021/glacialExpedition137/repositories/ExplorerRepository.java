package glacialExpedition137.repositories;

import glacialExpedition137.models.explorers.Explorer;

import java.util.ArrayList;
import java.util.List;

public class ExplorerRepository implements Repository<Explorer>{
    private List<Explorer> explorers;

    public ExplorerRepository() {
        this.explorers = new ArrayList<>();
    }

    @Override
    public List<Explorer> getCollection() {
        return this.explorers;
    }

    @Override
    public void add(Explorer entity) {
        if (this.explorers.stream().noneMatch(e -> e.getName().equals(entity.getName()))){
            this.explorers.add(entity);
        }
    }

    @Override
    public boolean remove(Explorer entity) {
        return this.explorers.remove(entity);
    }

    @Override
    public Explorer byName(String name) {
        return this.explorers.stream().filter(e->e.getName().equals(name)).findFirst().orElse(null);
    }
}
