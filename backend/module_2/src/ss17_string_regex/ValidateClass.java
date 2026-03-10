package module_2.src.ss17_string_regex;

public class ValidateClass {
    public static void main(String[] args) {
        boolean isValided = validateClass("C0223G");
        if (isValided) {
            System.out.println("Validate");
        } else {
            System.out.println("Invalidate");
        }
    }

    private static boolean validateClass(String className) {
        String regex = "^[CAP]\\d{4}[GHIK]$";
        return className.matches(regex);
    }

}
