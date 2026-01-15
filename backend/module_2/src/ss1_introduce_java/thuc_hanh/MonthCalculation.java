package module_2.src.ss1_introduce_java.thuc_hanh;

import java.util.Scanner;

public class MonthCalculation {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Which month that you want to count days? ");
        byte month = Byte.parseByte(scanner.nextLine());
        String daysInMonth = calculateDays(month);
        if (!daysInMonth.equals("")) {
            System.out.printf("The month '%d' has %s days!", month, daysInMonth);
        } else {
            System.out.print("Invalid input!");
        }
    }

    public static String calculateDays(byte month) {
        switch (month) {
            case 2:
                return "28 or 29";
            case 1, 3, 5, 7, 8, 10, 12:
                return "31";
            case 4, 6, 9, 11:
                return "30";
            default:
                return "";
        }
    }
}
