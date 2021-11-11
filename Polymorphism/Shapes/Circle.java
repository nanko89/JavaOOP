package Shapes;

public class Circle extends Shape{
    private Double radius;

    public Circle(Double radius) {
        setRadius(radius);
        calculatePerimeter();
        calculateArea();
    }

    protected void setRadius(Double radius) {
        this.radius = radius;
    }

    public final Double getRadius() {
        return radius;
    }

    @Override
    protected void calculatePerimeter() {
        Double result = Math.PI * 2 * radius;
        setPerimeter(result);
    }

    @Override
    protected void calculateArea() {
        Double result = Math.PI * radius * radius;
        setArea(result);
    }
}
