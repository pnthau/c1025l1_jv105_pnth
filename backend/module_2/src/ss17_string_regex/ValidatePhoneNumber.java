package module_2.src.ss17_string_regex;

public class ValidatePhoneNumber {
    public static void main(String[] args) {
        boolean isValid = validatePhoneNumber("(84)-(0978489648)");
        if (isValid) {
            System.out.println("is valid");
        } else {
            System.out.println("invalid");
        }
    }

    private static boolean validatePhoneNumber(String phoneNumber) {
        String regex = "^\\(\\d{2}\\)-\\(0\\d{9}\\)$";
        return phoneNumber.matches(regex);
    }
}
