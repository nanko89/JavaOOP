package spaceStation.models.astronauts;


import spaceStation.models.bags.Bag;

public class Biologist extends BaseAstronaut {
    private static final double OXYGEN = 70;


    public Biologist(String name) {
        super(name, OXYGEN);
    }

    @Override
    public void breath() {
        if (getOxygen() - 5 < 0){
            setOxygen(0);
        }else {
            setOxygen(getOxygen() - 5);
        }
    }
}
