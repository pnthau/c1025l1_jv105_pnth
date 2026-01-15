package module_2.src.ss3_array.bai_tap;

import java.util.Scanner;

public class MinValueArray {
    public static void main(String[] args) {
        int[] array = createArray().clone();
        System.out.println(findMin(array));
    }

    private static int findMin(int[] array) {
        int min = array[0];
        for (int i = 1; i < array.length; i++) {
            if (array[i] < min) {
                min = array[i];
            }
        }
        return min;
    }

    private static int[] createArray() {
        Scanner scanner = new Scanner(System.in);
        int length = 0;
        do {
            System.out.println("Enter length : ");
            length = Integer.parseInt(scanner.nextLine());
        } while (length < 0);

        int[] array = new int[length];

        for (int i = 0; i < array.length; i++) {
            System.out.println("Enter array[" + i + "] = ");
            array[i] = Integer.parseInt(scanner.nextLine());
        }
        return array;
    }
}
