package glacialExpedition137.models.explorers;

public class NaturalExplorer extends BaseExplorer{
    private static final double ENERGY_UNITS = 60;

    public NaturalExplorer(String name) {
        super(name, ENERGY_UNITS);
    }

    @Override
    public void search() {
        if (getEnergy() >= 7){
            setEnergy(getEnergy() - 7);
        }else {
            setEnergy(0);
        }
    }
}
