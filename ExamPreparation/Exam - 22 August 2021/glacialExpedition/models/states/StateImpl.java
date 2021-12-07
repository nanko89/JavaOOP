package glacialExpedition.models.states;

import glacialExpedition.common.ExceptionMessages;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class StateImpl implements State {
    private String name;
    private Collection<String> exhibits;

    public StateImpl(String name) {
        this.setName(name);
        this.exhibits = new ArrayList<>();
    }

    @Override
    public String getName() {
        return name;
    }

    public void setName(String name) {
        if (name == null || name.trim().isEmpty()){
            throw new NullPointerException(ExceptionMessages.STATE_NAME_NULL_OR_EMPTY);
        }
        this.name = name;
    }

    @Override
    public Collection<String> getExhibits() {
        return this.exhibits;
    }

    public void setExhibits(Collection<String> exhibits) {
        this.exhibits = exhibits;
    }
}
