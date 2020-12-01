package by.shibaev.university.model.validator;

import java.util.regex.Pattern;

public class InputValidator {

    public static final InputValidator INSTANCE = new InputValidator();

    private final String NAME_PATTERN = "[\\p{L}\\s-]{1,20}";

    private InputValidator() {
    }

    public boolean isNumberValid(String group) {
        boolean result = true;
        try {
            int value = Integer.parseInt(group);
        } catch (NumberFormatException e) {
            result = false;
        }
        return result;
    }

    public boolean isNameValid(String name) {
        return Pattern.matches(NAME_PATTERN,name);
    }
}
