package module_2.src.ss5_access_modifier.bai_tap;

public class TestCircle {
    public static void main(String[] args) {
        Circle circle = new Circle();
        circle.getArea();
        circle.getRadius();
    }
}

class Circle {
    private double radius = 1.0;
    private String color = "red";

    Circle() {
        this.radius = 1.0;
        this.color = "red";
    }

    Circle(double radius) {
        this.radius = radius;
    }

    double getRadius() {
        return this.radius;
    }

    double getArea() {
        return this.radius * Math.PI;
    }
}




