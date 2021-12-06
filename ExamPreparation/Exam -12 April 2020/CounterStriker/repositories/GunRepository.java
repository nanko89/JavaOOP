package CounterStriker.repositories;

import CounterStriker.models.guns.Gun;

import java.util.*;

import static CounterStriker.common.ExceptionMessages.*;

public class GunRepository implements Repository<Gun> {
    private Map<String, Gun> guns;

    public GunRepository() {
        this.guns = new LinkedHashMap<>();
    }

    @Override
    public Collection<Gun> getModels() {
        return this.guns.values();
    }

    @Override
    public void add(Gun model) {
        if (model == null) {
            throw new NullPointerException(INVALID_GUN_REPOSITORY);
        }
        guns.put(model.getName(), model);
    }

    @Override
    public boolean remove(Gun model) {
        Gun remove = guns.remove(model.getName());
        return remove != null;
    }

    @Override
    public Gun findByName(String name) {
        return guns.get(name);
    }
}
