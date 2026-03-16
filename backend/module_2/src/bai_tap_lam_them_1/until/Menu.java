package module_2.src.bai_tap_lam_them_1.until;

import module_2.src.bai_tap_lam_them_1.entity.Truck;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Menu {
    public static String getOptionMenu(String optionString) {
        String regex = "\\d+\\.\\s*(.+)";

        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(optionString);
        final int groupContent = 1;

        if (matcher.matches()) {
            return matcher.group(groupContent);
        }
        return null;
    }
}
