import java.util.Random;
import java.util.regex.Pattern;

public final class UtilityClass {
    /// for not creating object.
    private UtilityClass(){}
    private static final String PASSWORD_PATTERN = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=])(?=\\S+$).{8,20}$";

    public static boolean phoneNumberValidator(String phoneNumber) {

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

    public static boolean isValidRole(String role) {
        role = role.toLowerCase();
        if (role.equalsIgnoreCase("owner") || role.equalsIgnoreCase("customer")) {
            return true;
        }
        return false;
    }

    public static boolean isValidName(String name) {
        if (name == null || name.isEmpty()) {
            return false;
        }else {
            return true;
        }
    }

    public static  boolean validatePassword(String password) {
        return password != null && password.matches(PASSWORD_PATTERN);
    }


    public static String generateRandomNumberId(int length) {
        Random random = new Random();
        StringBuilder sb = new StringBuilder(length);
        for (int i = 0; i < length; i++) {
            int number = random.nextInt(10);
            sb.append(number);
        }
        return sb.toString();
    }
    public static boolean verifyEmail(String email) {
        String emailRegex = "^[a-zA-Z0-9_+&*-]+@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$";
        Pattern pat = Pattern.compile(emailRegex);
        if (email == null || email.isEmpty()) {
            return false;
        }
        return pat.matcher(email).matches();
    }

}
