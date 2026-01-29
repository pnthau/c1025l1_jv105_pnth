package module_2.src.ss6_inheritance.bai_tap;

public class Circle {
    private double radius;
    private String color;

    public Circle() {
        radius = 1.0;
        color = "green";
    }

    public Circle(String color, double radius) {
        this.color = color;
        this.radius = radius;
    }

    public double getRadius() {
        return radius;
    }

    public void setRadius(double radius) {
        this.radius = radius;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public double getArea() {
        return this.radius * this.radius * Math.PI;
    }

    @Override
    public String toString() {
        return "A Circle with radius=" + this.radius + ", color = " + this.color;
    }
}
