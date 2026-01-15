package module_2.src.ss1_introduce_java.thuc_hanh;

import java.util.Scanner;

public class LeadYear {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter year : ");
        short year = Short.parseShort(scanner.nextLine());
        boolean islead = isLeadYear(year);
        if (islead) {
            System.out.println(year + " lead year");
        } else {
            System.out.println(year + " not lead year");
        }
    }

    private static boolean isLeadYear(short year) {
        return ((year % 100 != 0 && year % 4 == 0) || year % 400 == 0);
    }
}
