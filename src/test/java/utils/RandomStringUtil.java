package utils;

import java.util.Random;

public class RandomStringUtil {
    public static String getRandomPassword(String email, int length) {
        String password = getRandomString(length);
        password = password + email.charAt(0) + "1";
        return password;
    }

    public static String getRandomString(int length) {
        Random ran = new Random();
        char letter;
        String word = "";
        for (int i = 0; i < length; i++) {
            letter = (char) (ran.nextInt(25) + 65);
            word = word + letter;
        }
        return word;
    }

    public static int getRandomMinMaxLimitedNumber(int min, int max) {
        Random ran = new Random();
        int number = ran.nextInt(max-min) + min;
        return number;
    }
}