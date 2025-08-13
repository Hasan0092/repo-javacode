sealed interface Shape permits Circle, Rectangle {}

final class Circle implements Shape {
    double radius;
    Circle(double radius) { this.radius = radius; }
}

final class Rectangle implements Shape {
    double length, width;
    Rectangle(double length, double width) {
        this.length = length; this.width = width;
    }
}

public class SealedExample {
    public static void main(String[] args) {
        Shape shape = new Circle(5);
        if (shape instanceof Circle) {
            System.out.println("Circle radius = " + ((Circle) shape).radius);
        }
    }
}
