package barracksWars5.core.commands;

import barracksWars5.interfaces.Executable;

public abstract class Command implements Executable {
    private String[] data;


    protected Command(String[] data) {
        this.data = data;
    }

    protected String[] getData() {
        return data;
    }

}
