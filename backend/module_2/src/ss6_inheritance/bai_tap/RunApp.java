package module_2.src.ss6_inheritance.bai_tap;

public class RunApp {
    public static void main(String[] args) {
//        Circle c1 = new Circle(); // radius mặc định = 1.0
//
//        System.out.println(c1);
//        System.out.println("Area: " + c1.getArea());

        Cylinder cy1 = new Cylinder("red", 2.0, 5.0); // radius = 2.0, height = 5.0
        System.out.println(cy1);
        System.out.println("Base Area: " + cy1.getArea());
        System.out.println("Volume: " + cy1.getVolume());
    }
}
