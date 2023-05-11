package utils;

import java.util.Random;

public class RandomUtil {
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
}
