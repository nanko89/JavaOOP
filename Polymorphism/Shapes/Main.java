package Shapes;

public class Main {
    public static void main(String[] args) {

        Shape circle = new Circle(4.5);
        Shape rectangle = new Rectangle(3D, 5D);

        System.out.println(circle.getArea());
        System.out.println(circle.getPerimeter());

        System.out.println(rectangle.getArea());
        System.out.println(rectangle.getPerimeter());
    }
}
