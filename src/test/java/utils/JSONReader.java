package utils;

import aquality.selenium.core.utilities.ISettingsFile;
import aquality.selenium.core.utilities.JsonSettingsFile;

public class JSONReader {
    private static ISettingsFile config = new JsonSettingsFile("config.json");
    private static ISettingsFile testData = new JsonSettingsFile("testData.json");

    public static ISettingsFile getConfig() {
        return config;
    }
    public static ISettingsFile getTestData() {
        return testData;
    }
}
