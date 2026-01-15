package module_2.src.ss1_introduce_java.bai_tap;

import java.util.*;

public class NumberToString {
    private static final String[] units = {"", "one", "two", "three", "four", "five", "six", "seven", "eight", "nine"};
    private static final String[] teens = {"ten", "eleven", "twelve", "thirteen", "fourteen", "fifteen", "sixteen", "seventeen", "eighteen", "nineteen"};
    private static final String[] tens = {"", "", "twenty", "thirty", "forty", "fifty", "sixty", "seventy", "eighty", "ninety"};
    private static final Map<Short, String> divisors = new TreeMap<>(Comparator.reverseOrder());

    static {
        divisors.put((short) 1_000, "thousand");
        divisors.put((short) 100, "hundred");
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter number : ");
        short number = Short.parseShort(scanner.nextLine());
        System.out.println(numericToString(number));
    }

    private static String numericToString(short number) {
        String result = "";

        for (Map.Entry<Short, String> entry : divisors.entrySet()) {
            if (number / entry.getKey() > 0) {
                result += units[number / entry.getKey()] + " " + entry.getValue() + " ";
                number %= entry.getKey();
            }
        }

        if (number >= 20) {
            result += tens[number / 10] + " ";
            number = (short) (number % 10);
        } else if (number >= 10) {
            result += teens[number - 10] + " ";
            number = (short) (number % 10);
        }

        if (number > 0) {
            result += units[number] + " ";
        }

        return result.trim();
    }
}
//    private static String numericToString(short number) {
//        final short hundred = 100;
//        final short ten = 10;
//        final short thousand = 1000;
//        final short twenty = 20;
//
//        String numeric = "";
//
//        byte hundreds = 0;
//        byte tens = 0;
//
//        while (number > twenty) {
//            if (number < hundred) {
//                numeric += numericToStringTens(number);
//                number %= ten;
//            } else if (number < thousand) {
//                hundreds = divideByPowerOfTen(number, hundred);
//                numeric += numericToStringUnits(hundreds) + " hundred ";
//                number %= hundred;
//            }
//        }
//        if (number <= ten) {
//            numeric += numericToStringUnits(number);
//        } else {
//            numeric += numericToStringTensLessTwenty(number);
//        }
//        return numeric;
//    }
//
//    private static String numericToStringUnits(short number) {
//        String[] numbers = {"one", "two", "three", "four", "five", "six", "seven", "eight", "nine", "ten"};
//        byte units = (byte) (number - 1);
//        return numbers[units];
//    }
//
//    private static String numericToStringTensLessTwenty(short number) {
//        String[] numbers = {"eleven", "twelve", "thirteen", "fourteen", "fifteen", "sixteen", "seventeen", "eighteen", "nineteen", "twenty"};
//        final short ten = 10;
//        byte units = units = (byte) (number - (ten + 1));
//        return numbers[units];
//    }
//
//    private static String numericToStringTens(short number) {
//        String[] numbers = {"twenty", "thirty", "forty", "fifty", "sixty", "seventy", "eighty", "ninety"};
//        final short hundred = 10;
//        final short ten = 10;
//        byte tens = (byte) (divideByPowerOfTen(number, ten) - 2);
//        return numbers[tens] + " ";
//    }
//
//    private static byte divideByPowerOfTen(short number, short powers) {
//        return (byte) (number / powers);
//    }
//}