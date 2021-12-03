package bakery.repositories.interfaces;

import bakery.entities.tables.interfaces.BaseTable;
import bakery.entities.tables.interfaces.Table;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;

public class TableRepositoryImpl implements TableRepository<Table> {
    private List< Table> tables;

    public TableRepositoryImpl() {
        this.tables = new ArrayList<>();
    }

    @Override
    public Collection<Table> getAll() {
        return this.tables;
    }

    @Override
    public void add(Table table) {
        this.tables.add(table);
    }

    @Override
    public Table getByNumber(int number) {
        if (this.tables.isEmpty()){
            return null;
        }
        return this.tables.stream().filter(t->t.getTableNumber() == number)
                .findFirst().orElse(null);
    }
}
