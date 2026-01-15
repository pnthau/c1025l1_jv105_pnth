package module_2.src.ss1_introduce_java.thuc_hanh;

import java.util.Scanner;

public class Rectangle {
    public static void main(String[] args) {
        float width = 0.f;
        float height = 0.f;
        Scanner scanner = new Scanner(System.in);

        System.out.println("Enter width: ");
        width = Float.parseFloat(scanner.nextLine());

        System.out.println("Enter height: ");
        height = Float.parseFloat(scanner.nextLine());

        float area = width * height;
        System.out.println("The area of the rectangle is: " + area);
    }
}
