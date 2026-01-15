package module_2.src.ss2_loop.thuc_hanh;

public class GCD {
    public static void main(String[] args) {
        gcd(12, 4);
    }

    private static int gcd(int a, int b) {
        int temp = 0;
        while (b != 0) {
            temp = b;
            b = a % b;
            a = temp;
        }
        return a;
    }
}
