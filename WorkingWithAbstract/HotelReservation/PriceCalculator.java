package HotelReservation;

public class PriceCalculator {
    private double pricePerDay;
    private int days;
    private Seasons seasons;
    private Discount discount;

    public PriceCalculator(double pricePerDay, int days, Seasons seasons, Discount discount) {
        this.pricePerDay = pricePerDay;
        this.days = days;
        this.seasons = seasons;
        this.discount = discount;
    }

    public double calculatorPrice(){
        return (pricePerDay * seasons.getMultiplayer()) * days *(1 - discount.getPercent());
    }
}
