package module_2.src.ss3_array.thuc_hanh;

public class LargestValue {
    public static void main(String[] args) {
        findLargestValue(new int[]{19, 20, 80, 101, 2});
    }

    public static void findLargestValue(int[] array) {
        int max = array[0];
        int index = 0;
        for (int i = 1; i < array.length; i++) {
            if (array[i] > max) {
                max = array[i];
                index = i;
            }
        }
    }
}
