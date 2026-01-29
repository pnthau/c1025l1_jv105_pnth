package module_2.src.ss6_inheritance.bai_tap;

public class Cylinder extends Circle {
    private double height;

    public Cylinder() {
        super("red", 1.0);
        this.height = 1.0;
    }

    public Cylinder(String color, double radius, double height) {
        super(color, radius);
        this.height = height;
    }

    public double getVolume() {
        return this.getArea() * this.height;
    }
}
