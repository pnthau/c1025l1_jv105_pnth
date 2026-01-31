package module_2.src.ss7_abstract_class_interface.thuc_hanh;

import module_2.src.ss6_inheritance.thuc_hanh.Circle;

public class ComparableCircle extends Circle implements Comparable<ComparableCircle> {
    public ComparableCircle() {
    }

    public ComparableCircle(double radius) {
        super(radius);
    }

    public ComparableCircle(boolean filled, String color, double radius) {
        super(filled, color, radius);
    }

    @Override
    public int compareTo(ComparableCircle o) {
        if (this.getRadius() > o.getRadius()) {
            return 1;
        }
        if (this.getRadius() < o.getRadius()) {
            return -1;
        }
        return 0;
    }
}
