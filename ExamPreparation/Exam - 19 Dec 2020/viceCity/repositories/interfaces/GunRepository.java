package viceCity.repositories.interfaces;

import viceCity.models.guns.Gun;

import java.util.*;

public class GunRepository implements Repository<Gun>{
    public Map<String, Gun> models;

    public GunRepository() {
        this.models = new LinkedHashMap<>();
    }

    @Override
    public Collection<Gun> getModels() {
        return Collections.unmodifiableCollection(this.models.values());
    }

    @Override
    public void add(Gun model) {
        models.putIfAbsent(model.getName(), model);
    }

    @Override
    public boolean remove(Gun model) {
        Gun remove = models.remove(model.getName());
        return remove != null;
        //models.values().stream().anyMatch(g->g.getName().equals(model.getName()));
    }

    @Override
    public Gun find(String name) {
        return models.get(name);
    }
}
