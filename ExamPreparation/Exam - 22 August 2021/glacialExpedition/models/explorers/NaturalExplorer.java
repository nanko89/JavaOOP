package glacialExpedition.models.explorers;

public class NaturalExplorer extends BaseExplorer {
    private static final double NATURAL_EXPLORER_ENERGY = 60;

    public NaturalExplorer(String name) {
        super(name, NATURAL_EXPLORER_ENERGY);
    }

    @Override
    public void search() {
        if (getEnergy() <= 7) {
            this.setEnergy(0);
        } else {
            this.setEnergy(this.getEnergy() - 7);
        }
    }
}
