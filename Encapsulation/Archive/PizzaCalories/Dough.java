package Archive.PizzaCalories;

public class Dough {
    private String flourType;
    private String technique;
    private double weight;

    public Dough(String flourType, String technique, double weight) {
        setFlourType(flourType);
        setTechnique(technique);
        setWeight(weight);
    }

    private void setFlourType(String flourType) {
        if (!flourType.equals("White") && !flourType.equals("Wholegrain")) {
            throw new IllegalArgumentException("Invalid type of dough.");
        }
        this.flourType = flourType;
    }

    private void setTechnique(String technique) {
        if (!technique.equals("Homemade") && !technique.equals("Chewy") && !technique.equals("Crispy")) {
            throw new IllegalArgumentException("Invalid type of dough.");

        }
        this.technique = technique;
    }

    private void setWeight(double weight) {
        if (weight <= 0 || weight > 200) {
            throw new IllegalArgumentException("Dough weight should be in the range [1..200].");
        }
        this.weight = weight;
    }

    public double calculateCalories() {
        return 2 * this.weight * modifiersBakingTechnique(this.technique)
                * modifiersFlorType(this.flourType);
    }

    private double modifiersFlorType(String flourType) {
        switch (flourType) {
            case "White":
                return 1.5;
            case "Wholegrain":
                return 1;
        }
        return 0;
    }

    private double modifiersBakingTechnique(String bakingTechnique) {
        switch (bakingTechnique) {
            case "Crispy":
                return 0.9;
            case "Chewy":
                return 1.1;
            case "Homemade":
                return 1.0;
        }
        return 0;
    }

}
