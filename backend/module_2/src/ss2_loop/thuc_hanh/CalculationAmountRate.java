package module_2.src.ss2_loop.thuc_hanh;

import java.util.Scanner;

public class CalculationAmountRate {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        double amount = 0;
        float rate = 0.f;
        short month = 0;
        boolean islessZero = false;
        boolean isValidedRate = false;
        boolean isValidedAmount = false;
        final float maximumYearRate = 0.5f;
        do {
            System.out.println("Enter Rate (%): ");
            rate = Float.parseFloat(scanner.nextLine());
            System.out.println("Enter amount (million) : ");
            amount = Double.parseDouble(scanner.nextLine());
            System.out.println("Enter month : ");
            month = Short.parseShort(scanner.nextLine());

            isValidedAmount = isLess(amount, 2);
            if (isValidedAmount) {
                System.out.println("Amount must be larger than " + 2);
                continue;
            }
            islessZero = isLessZero(amount) || isLessZero(rate) || isLessZero(month);
            if (islessZero) {
                System.out.println("Amount, rate and month is larger than zero");
                continue;
            }
            isValidedRate = isRateMonth(rate, maximumYearRate);
            if (isValidedRate) {
                System.out.println("rate is larger than " + maximumYearRate + "/year.");
            }
        } while (islessZero || isValidedRate || isValidedAmount);

        //th1 : lai kep

        //th2 : lai thuong
    }

    private static boolean isLessZero(double number) {
        return number < 0;
    }

    private static boolean isLess(double amount, double less) {
        return amount < less;
    }

    private static boolean isRateMonth(float rate, float maximumYearRate) {
        final byte months = 12;

        float maximumMonthRate = (1.0f / months) * maximumYearRate;
        return rate > maximumMonthRate;
    }

    private static double calculateRateMonthsDouble(double amount, float rate, short month) {
        return amount * Math.pow((1 + rate), month);
    }

    private static double calculateRateMonthsNormal(double amount, float rate, short month) {
        double amountRate = amount * rate;
        return amount + (amountRate * month);
    }
}
