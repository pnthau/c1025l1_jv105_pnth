package module_2.src.ss3_array.thuc_hanh;

public class ReverseArray {
    public static void main(String[] args) {
        short[] array = new short[]{1, 2, 3, 7, 6, 5};
        System.out.printf("%-20s", "Origin array : ");
        display(array);
        reverse(array);
        System.out.printf("\n%-20s", "Reverse array : ");
        display(array);
    }

    private static void reverse(short[] array) {
        short left = 0;
        short right = (short) (array.length - 1);
        short temp = 0;

        while (left < right) {
            temp = array[left];
            array[left] = array[right];
            array[right] = temp;

            left++;
            right--;
        }
    }

    private static void display(short[] array) {
        for (short value : array) {
            System.out.print(value + " ");
        }
    }
}
