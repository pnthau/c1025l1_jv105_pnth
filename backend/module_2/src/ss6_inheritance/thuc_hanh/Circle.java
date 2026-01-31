package module_2.src.ss6_inheritance.thuc_hanh;

public class Circle extends Shape {
    private double radius;

    public Circle() {
        this.radius = 1.0;
    }

    public Circle(double radius) {
        this.radius = radius;
    }

    public Circle(boolean filled, String color, double radius) {
        super(color, filled);
        this.radius = radius;
    }

    public double getRadius() {
        return radius;
    }

    public void setRadius(double radius) {
        this.radius = radius;
    }

    public double getArea() {
        return this.radius * this.radius * Math.PI;
    }

    public double getPerimeter() {
        return 2 * this.radius * Math.PI;
    }

    @Override
    public String toString() {
        return "A Circle with radius=" + this.radius + " , which is a subclass of " + super.getClassName();
    }

    @Override
    public void resize(double precent) {
        this.setRadius(this.getRadius() * precent);
    }
}
