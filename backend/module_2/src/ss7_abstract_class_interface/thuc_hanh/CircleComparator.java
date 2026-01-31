package module_2.src.ss7_abstract_class_interface.thuc_hanh;

import module_2.src.ss6_inheritance.thuc_hanh.Circle;

import java.util.Comparator;

public class CircleComparator implements Comparator<Circle> {
    @Override
    public int compare(Circle o1, Circle o2) {
        if (o1.getRadius() > o2.getRadius()) {
            return 1;
        }
        if (o1.getRadius() < o2.getRadius()) {
            return -1;
        }
        return 0;
    }
}
