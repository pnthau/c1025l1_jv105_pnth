package module_2.src.ss7_abstract_class_interface.bai_tap;

import module_2.src.ss6_inheritance.thuc_hanh.Circle;
import module_2.src.ss6_inheritance.thuc_hanh.Rectangle;
import module_2.src.ss6_inheritance.thuc_hanh.Shape;
import module_2.src.ss6_inheritance.thuc_hanh.Square;

public class RunApp {
    public static void main(String[] args) {
        Shape[] shapes = new Shape[]{
                new Circle(3.5),
                new Rectangle(3, 4),
                new Square(5)
        };

        for (Shape s : shapes) {
            System.out.println(s.toString());
            if (s instanceof IColorable) {
                ((IColorable) s).howToColor();
            }
        }

        for (IResizeable r : shapes) {
            double present = r.randomResize();
            r.resize(present);
        }

        for (Shape s : shapes) {
            System.out.println(s.toString());
            if (s instanceof IColorable) {
                ((IColorable) s).howToColor();
            }
        }


    }
}
