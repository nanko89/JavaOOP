package Archive.ClassBoxData;

public class Box {
    private double length;
    private double width;
    private double height;

    public Box(double length, double width, double height) {
        setLength(length);
        setWidth(width);
        setHeight(height);
    }

    public double surfaceArea() {
        //2lw + 2lh + 2wh
        return (2 * length * width) + (2 * length * height) + (2 * width * height);
    }

    public double lateralSurfaceArea(){
        //2lh + 2wh
        return (2 * length * height) + (2 * width * height);
    }

    public double volume (){
        return length * height * width;
    }

    public void setWidth(double width) {
        checkSide(width, "Width");
        this.width = width;
    }

    public void setHeight(double height) {
        checkSide(height, "Height");
        this.height = height;
    }

    public void setLength(double length) {
        checkSide(length, "Length");
        this.length = length;
    }

    private void checkSide(double side, String parameter) {
        if (side <= 0){
            throw new IllegalArgumentException(parameter +" cannot be zero or negative.");
        }
    }
}
