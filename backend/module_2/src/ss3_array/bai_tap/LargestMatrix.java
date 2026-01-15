package module_2.src.ss3_array.bai_tap;

import java.util.Arrays;
import java.util.Scanner;

public class LargestMatrix {
    public static void main(String[] args) {
        int[][] matrix = createMatrix();
        System.out.println(Arrays.toString(maxValueMatrix(matrix)));
    }

    private static int[] maxValueMatrix(int[][] matrix) {
        int max = 0;
        int row = 0;
        int column = 0;

        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[i].length; j++) {
                if (max < matrix[i][j]) {
                    max = matrix[i][j];
                    row = i;
                    column = j;
                }
            }
        }
        return new int[]{max, row, column};
    }

    private static int[][] createMatrix() {
        Scanner scanner = new Scanner(System.in);
        int length = 0;
        do {
            System.out.println("Enter matrix row : ");
            length = Integer.parseInt(scanner.nextLine());
        } while (length < 0);
        int[][] matrix = new int[length][];
        for (int i = 0; i < matrix.length; i++) {
            matrix[i] = createArray(i).clone();
        }
        return matrix;
    }

    private static int[] createArray(int row) {
        Scanner scanner = new Scanner(System.in);
        int length = 0;
        do {
            System.out.println("Enter matrix[" + row + "] length : ");
            length = Integer.parseInt(scanner.nextLine());
        } while (length < 0);

        int[] array = new int[length];

        for (int i = 0; i < array.length; i++) {
            System.out.println("Enter matrix[" + row + "][" + i + "] = ");
            array[i] = Integer.parseInt(scanner.nextLine());
        }
        return array;
    }
}
