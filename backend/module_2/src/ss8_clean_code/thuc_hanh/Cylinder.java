package module_2.src.ss8_clean_code.thuc_hanh;

public class Cylinder {
    public static double getVolume(int radius, int height) {
        double baseArea = getBaseArea(radius);
        double perimeter = getPerimeter(radius);
        return getVolume(height, perimeter, baseArea);
    }

    private static double getVolume(int height, double perimeter, double baseArea) {
        return perimeter * height + 2 * baseArea;
    }

    private static double getPerimeter(int radius) {
        return 2 * Math.PI * radius;
    }

    private static double getBaseArea(int radius) {
        return Math.PI * radius * radius;
    }
}
