package CounterStriker.repositories;

import CounterStriker.models.guns.Gun;

import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.Map;

import static CounterStriker.common.ExceptionMessages.*;

public class GunRepository implements  Repository<Gun>{
    private Map<String ,Gun> models;

    public GunRepository() {
        this.models = new LinkedHashMap<>();
    }

    @Override
    public Collection<Gun> getModels() {
        return this.models.values();
    }

    @Override
    public void add(Gun model) {
        if (model == null){
            throw new NullPointerException(INVALID_GUN_REPOSITORY);
        }
        this.models.put(model.getName(), model);
    }

    @Override
    public boolean remove(Gun model) {
        return models.remove(model.getName()) != null;
    }

    @Override
    public Gun findByName(String name) {
        return models.get(name);
    }
}
