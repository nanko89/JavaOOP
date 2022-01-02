package barracksWars5.core.commands;

import barracksWars5.interfaces.Inject;
import barracksWars5.interfaces.Repository;

public class Report extends Command{
    @Inject
    private Repository repository;

    public Report(String[] data) {
        super(data);
    }

    @Override
    public String execute() {
        return this.repository.getStatistics();
    }
}
