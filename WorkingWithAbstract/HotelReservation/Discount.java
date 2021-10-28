package HotelReservation;

public enum Discount {
    VIP(0.20),
    SECOND_VISIT(0.10),
    NONE(0);

    private double percent;

    Discount(double percent){
        this.percent = percent;
    }

    public double getPercent() {
        return percent;
    }

    public static Discount parseDiscount(String discount){
        switch (discount){
            case "VIP":
                return VIP;
            case "SecondVisit":
                return SECOND_VISIT;
            case "None":
                return NONE;
            default:
                throw new IllegalArgumentException("Unknown discount type " + discount);
        }
    }
}
