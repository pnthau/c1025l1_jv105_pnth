package utility;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.time.format.ResolverStyle;

public class Validator {
    private static final String POSITIVE_NUMBER = "^[1-9]\\d*$";
    private static final String DATE_REGEX = "^[1-9]\\d{3}([-/.])(0[1-9]|1[0-2])\\1(0[1-9]|[12]\\d|3[01])$";
    public static boolean isPositiveNumber(String str){
        if(str == null || str.trim().isEmpty())
        {
            return false;
        }
        return str.trim().matches(POSITIVE_NUMBER);
    }

    public static boolean isValidDate(String dateStr)
    {
        if(dateStr == null || !dateStr.matches(DATE_REGEX))
        {
            return false;
        }
        try{
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("uuuu-MM-dd")
                    .withResolverStyle(ResolverStyle.STRICT);
            LocalDate.parse(dateStr,formatter);
            return true;
        }
        catch (DateTimeParseException e){
            return false;
        }
    }
}
