package rpis82.muhutdinov.oop.model;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class InvalidAccountNumberException extends RuntimeException {
    public static String NumberException(String number){
        Pattern pattern = Pattern.compile("(44|45|40)\\d{3}(810)\\d{4}[1-9]\\d{6}[1-9]");
        Matcher matcherNumber = pattern.matcher(number);
        if ( !matcherNumber.matches() )
            throw  new InvalidAccountNumberException();
        return number;
    }
}
