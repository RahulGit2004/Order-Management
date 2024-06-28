import java.util.Random;
import java.util.regex.Pattern;

public class Helper {
    private static final String PASSWORD_PATTERN = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=])(?=\\S+$).{8,20}$";

    public boolean phoneNumberValidator(String phoneNumber) {

        if (phoneNumber == null) {
            return false;
        }

        if (phoneNumber.length() != 10) {
            return false;
        }

        for (int i = 0; i < phoneNumber.length(); i++) {
            if (!Character.isDigit(phoneNumber.charAt(i))) {
                return false;
            }
        }

        return true;
    }

    public  boolean passwordValidator(String password) {
        if (password.length() < 8) {
            return false;
        }
        if (!Character.isUpperCase(password.charAt(0))) {
            return false;
        }

        boolean hasLowercase = false;
        boolean hasDigit = false;
        boolean hasSpecialChar = false;

        for (char ch : password.toCharArray()) {
            if (Character.isLowerCase(ch)) {
                hasLowercase = true;
            } else if (Character.isDigit(ch)) {
                hasDigit = true;
            } else if (!Character.isLetterOrDigit(ch)) {
                hasSpecialChar = true;
            }
        }

        return hasLowercase && hasDigit && hasSpecialChar;
    }


    public boolean isValidRole(String role) {
        role = role.toLowerCase();
        if (role.equalsIgnoreCase("owner") || role.equalsIgnoreCase("customer")) {
            return true;
        }
        return false;
    }

    public boolean isValid(String name) {
        if (name == null || name.isEmpty()) {
            return false;
        }else {
            return true;
        }
    }
    public  boolean validatePassword(String password) {
        return password != null && password.matches(PASSWORD_PATTERN);
    }

//    public  boolean validatePassword(String password) {
//        String regex = "^(.+)@(.+)$";
//        return Pattern.matches(regex, password);
//    }


    public  String generateRandomNumberId(int length) {
        Random random = new Random();
        StringBuilder sb = new StringBuilder(length);
        for (int i = 0; i < length; i++) {
            int number = random.nextInt(10);
            sb.append(number);
        }
        return sb.toString();
    }
    public  boolean verifyEmail(String email) {
        String emailRegex = "^[a-zA-Z0-9_+&*-]+@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$";
        Pattern pat = Pattern.compile(emailRegex);
        if (email == null || email.isEmpty()) {
            return false;
        }
        return pat.matcher(email).matches();
    }


}
