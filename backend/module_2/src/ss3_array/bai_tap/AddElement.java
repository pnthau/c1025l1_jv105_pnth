package module_2.src.ss3_array.bai_tap;

import java.util.Arrays;

import static java.lang.System.arraycopy;

public class AddElement {
    public static void main(String[] args) {
        int[] array = {5, 7, 83, 2};
        if (!isValidPosition(2, array)) {
            return;
        }
        array = addElement(1000, 2, array);
        System.out.println(Arrays.toString(array));
    }

    private static boolean isValidPosition(int position, int[] array) {
        return position >= 0 && position < array.length;
    }

    private static int[] addElement(int value, int position, int[] array) {
        int[] results = new int[array.length + 1];
        arraycopy(array, 0, results, 0, array.length);
        for (int i = results.length - 1; i >= position; i--) {
            results[i] = results[i - 1];
        }
        results[position] = value;
        return results;
    }
}
