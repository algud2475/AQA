package utils;

import aquality.selenium.browser.AqualityServices;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class FileUtil {
    public static String readFileToString(String path) {
        StringBuilder sb = new StringBuilder();
        try {
            BufferedReader br = new BufferedReader(new FileReader(path));
            String i = br.readLine();
            while (i != null) {
                sb.append(i);
                i = br.readLine();
            }
        } catch (IOException e) {
            AqualityServices.getLogger().error(e.getMessage());
            throw new RuntimeException(e);
        }
        return sb.toString();
    }
}
