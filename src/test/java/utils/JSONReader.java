package utils;

import aquality.selenium.core.utilities.ISettingsFile;
import aquality.selenium.core.utilities.JsonSettingsFile;

public class JSONReader {
    private static ISettingsFile configDB = new JsonSettingsFile("configDB.json");

    public static ISettingsFile getConfigDB() {
        return configDB;
    }
}
