package module_2.src.ss3_array.thuc_hanh;

public class SearchArrayValue {
    public static void main(String[] args) {
        String[] students = {"Christian", "Michael", "Camila", "Sienna", "Tanya", "Connor", "Zachariah", "Mallory", "Zoe", "Emily"};
        System.out.println(search("konnor", students) ? "yes" : "no");
    }

    public static boolean search(String key, String[] students) {
        for (String value : students) {
            if (value.equals(key)) {
                return true;
            }
        }
        return false;
    }
}
