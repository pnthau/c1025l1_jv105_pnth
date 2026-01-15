package module_2.src.ss2_loop.bai_tap;

public class DisplayPrime2 {
    public static void main(String[] args) {
        for (int i = 2; i < 100; i++) {
            if (DisplayPrime.isPrime(i)) {
                System.out.println(i);
            }
        }
    }
}
