package module_2.src.ss1_introduce_java.bai_tap;

import java.util.Scanner;

public class CurrencyConversion {
    enum MoneyTypes {
        JPY(165.26),
        USD(26250),
        CNY(3763.44),
        RUB(333.10);
        private final double rate;

        MoneyTypes(double rate) {
            this.rate = rate;
        }

        public double getRate() {
            return this.rate;
        }
    }

    public static void main(String[] args) {
        //25 usd -> jpy
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter amount : ");
        long amount = Long.parseLong(scanner.nextLine());

        System.out.println("Enter money type from : ");
        String inputFrom = scanner.nextLine().toUpperCase();
        MoneyTypes from = MoneyTypes.valueOf(inputFrom);

        System.out.println("Enter money type to : ");
        String inputTo = scanner.nextLine().toUpperCase();
        MoneyTypes to = MoneyTypes.valueOf(inputTo);

        long jpy = ExchangeRate(amount, from, to);
        System.out.println(25 + " usd " + jpy + " jpy");
    }

    private static long ExchangeRate(double amount, MoneyTypes from, MoneyTypes to) {
        long vnd = (long) (amount * from.getRate());
        return (long) (vnd / to.getRate());
    }
}
