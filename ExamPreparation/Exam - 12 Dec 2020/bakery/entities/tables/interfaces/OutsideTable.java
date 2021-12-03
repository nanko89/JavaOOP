package bakery.entities.tables.interfaces;

public class OutsideTable extends BaseTable{
    private static final double PRISE_PER_PERSON = 3.50;

    public OutsideTable(int tableNumber, int capacity) {
        super(tableNumber, capacity, PRISE_PER_PERSON);
    }
}
