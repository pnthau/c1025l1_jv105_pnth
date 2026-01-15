package module_2.src.ss2_loop.bai_tap;

public class DisplayPrime {
    public static void main(String[] args) {
        byte count = 1;
        int start = 2;
        while (count <= 20) {
            if (isPrime(start)) {
                System.out.println(start);
                count++;
            }
            start++;
        }
    }

    public static boolean isPrime(int number) {
        if (number < 2) {
            return false;
        }
        for (int i = 2; i <= Math.sqrt(number); i++) {
            if (number % i == 0) {
                return false;
            }
        }
        return true;
    }
}
