package module_2.src.ss15_exception.bai_tap;

import module_2.src.ss6_inheritance.bai_tap.Triangle;

public class RunApp {
    public static void main(String[] args) {
        try {
            Triangle triangle = new Triangle("Green", true, 1, 2, 3);
        } catch (IllegalTriangleException e) {
            System.out.println(e.getMessage());
        }
    }
}
