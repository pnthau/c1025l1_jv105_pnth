package module_2.src.ss3_array.thuc_hanh;

public class LessestValue {
    public static void main(String[] args) {
        int index = findSmallestValue(new int[]{4, 2, 3, 1});
    }

    public static int findSmallestValue(int[] array) {
        int min = array[0];
        int index = 0;
        for (int i = 1; i < array.length; i++) {
            if (array[i] < min) {
                min = array[i];
                index = i;
            }
        }
        return index;
    }
}
