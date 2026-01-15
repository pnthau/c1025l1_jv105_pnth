package module_2.src.ss3_array.bai_tap;

import java.util.Arrays;

public class MergeArrays {
    public static void main(String[] args) {
        int[] merger = mergeArrays(new int[]{1, 2, 3}, new int[]{4, 5, 6});
        System.out.println(Arrays.toString(merger));
    }

    private static int[] mergeArrays(int[] array1, int[] array2) {
        int[] merge = new int[(array1.length + array2.length)];
        int position = 0;
        for (int i = 0; i < array1.length; i++) {
            merge[position++] = array1[i];
        }
        for (int i = 0; i < array2.length; i++) {
            merge[position++] = array2[i];
        }
        return merge;
    }
}
