package catHouse.entities.toys;

public abstract class BaseToy implements Toy{
    private  int softness;
    private double price;

    protected BaseToy(int softness, double price) {
        this.setSoftness(softness);
        this.setPrice(price);
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public void setSoftness(int softness) {
        this.softness = softness;
    }

    @Override
    public int getSoftness() {
        return this.softness;
    }

    @Override
    public double getPrice() {
        return this.price;
    }
}
