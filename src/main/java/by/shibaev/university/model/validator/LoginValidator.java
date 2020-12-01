package by.shibaev.university.model.validator;

import java.util.regex.Pattern;

public class LoginValidator {
    public static final  LoginValidator INSTANCE  = new LoginValidator();
    private static final String PASSWORD_PATTERN = "^(?=.*[a-z])(?=.*[A-Z])[A-Za-z\\d@$!%*?&]{4,25}";
    private static final String LOGIN_PATTERN = "^(?=.*[A-Za-z0-9]$)[a-zA-Z][a-zA-Z0-9._-]{4,25}";

    private LoginValidator(){
    }

    public boolean isPasswordCorrect(String password){
        return Pattern.matches(PASSWORD_PATTERN,password);
    }

    public boolean isLoginCorrect(String login){
        return Pattern.matches(LOGIN_PATTERN,login);
    }
}
