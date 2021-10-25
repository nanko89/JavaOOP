public class Rectangle {
    private final Point A;
    private final Point B;

    public Rectangle(Point A, Point B){
        this.A = A;
        this.B = B;
    }

    public boolean contains(Point p) {
        return p.greatherOrEqual(A) && p.lessOrEqual(B);
    }
}
