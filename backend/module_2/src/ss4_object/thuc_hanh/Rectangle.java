package module_2.src.ss4_object.thuc_hanh;

import java.util.Scanner;

public class Rectangle {
    private int width;
    private int height;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter the width:");
        int width = Integer.parseInt(scanner.nextLine());
        System.out.print("Enter the height:");
        int height = Integer.parseInt(scanner.nextLine());

        Rectangle rectangle = new Rectangle(width, height);
        rectangle.display();
        System.out.println(rectangle.getPerimeter());
        System.out.println(rectangle.getArea());
    }

    Rectangle() {
        width = 0;
        height = 0;
    }

    Rectangle(int width, int height) {
        this.width = width;
        this.height = height;
    }

    public int getArea() {
        return this.width * this.height;
    }

    public int getPerimeter() {
        return 2 * (this.width + this.height);
    }

    public String display() {
        return "Rectangle{" + "width=" + width + ", height=" + height + "}";
    }
}
