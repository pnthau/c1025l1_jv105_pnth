package module_2.src.ss2_loop.bai_tap;

import java.util.Scanner;

public class DisplayShade {
    public static void main(String[] args) {

        boolean flag = true;
        Scanner scanner = new Scanner(System.in);
        byte choice = 0;
        while (flag) {
            System.out.println("1. Print the rectangle \n" +
                    "2. Print the square triangle (The corner is square at 4 different angles: top-left, top-right, botton-left, botton-right) \n" +
                    "3. Print isosceles triangle \n" +
                    "0. Exit ");
            choice = Byte.parseByte(scanner.nextLine());
            createMenu(choice);
            if (choice == 0) {
                flag = false;
            }
        }
    }

    private static void createMenu(byte choice) {

        switch (choice) {
            case 1:
                displayRectangle((byte) 3, (byte) 3);
                break;
            case 2:
                displayTriangleTop((byte) 3, (byte) 3, true);
                displayTriangleTop((byte) 3, (byte) 3, false);
                displayTriangleBot((byte) 3, (byte) 3, true);
                displayTriangleBot((byte) 3, (byte) 3, false);
                break;
            case 3:
                displayIsoscelesTriangle((byte) 3);
                break;
            case 0:
                System.out.println("Exit");
                break;
            default:
                System.out.println("No choice");
        }
    }

    private static void displayRectangle(byte width, byte height) {
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                System.out.print("*");
            }
            System.out.println("");
        }
    }

    private static void displayTriangleTop(byte width, byte height, boolean stars) {
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                if (stars && i <= j || !stars && j <= i) {
                    System.out.print("*");
                } else {
                    System.out.print(" ");
                }
            }
            System.out.println("");
        }
    }

    private static void displayTriangleBot(byte width, byte height, boolean stars) {
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                if (stars && (i + j) < width || !stars && (i + j) >= width - 1) {
                    System.out.print("*");
                } else {
                    System.out.print(" ");
                }
            }
            System.out.println("");
        }
    }

    private static void displayIsoscelesTriangle(byte size) {
        for (int i = 0; i < size; i++) {
            for (int j = -size; j <= size; j++) {
                if (Math.abs(j) <= i) {
                    System.out.print("*");
                } else {
                    System.out.print(" ");
                }
            }
            System.out.println("");
        }
    }
}
