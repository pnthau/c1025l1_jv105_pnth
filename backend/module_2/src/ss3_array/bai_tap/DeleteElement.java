package module_2.src.ss3_array.bai_tap;

import java.util.Arrays;

public class DeleteElement {
    public static void main(String[] args) {
        int[] array = new int[]{1, 2, 38, 100};

        int index = search(2, array);
        if (index == -1) {
            System.out.println("Not found");
            return;
        }
        delete(index, array);
        System.out.println(Arrays.toString(array));
    }

    private static int search(int key, int[] array) {
        for (int i = 0; i < array.length; i++) {
            if (array[i] == key) {
                return i;
            }
        }
        return -1;
    }

    private static void delete(int position, int[] array) {
        for (int i = position + 1; i < array.length; i++) {
            array[i - 1] = array[i];
        }
        array[array.length - 1] = 0;
    }
}
