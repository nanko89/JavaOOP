package bakery.repositories.interfaces;

public interface TableRepository<Table> extends Repository<Table> {
    Table getByNumber(int number);
}
