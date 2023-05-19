package utils;

import aquality.selenium.browser.AqualityServices;
import aquality.selenium.core.utilities.ISettingsFile;
import aquality.selenium.core.utilities.JsonSettingsFile;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.List;

public class JSONReader {
    private static ISettingsFile config = new JsonSettingsFile("config.json");
    private static ISettingsFile testData = new JsonSettingsFile("testCaseData.json");

    public static ISettingsFile getConfig() {
        return config;
    }

    public static ISettingsFile getTestData() {
        return testData;
    }

    public static <T> T getObject(String data, Class<T> classType) {
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            return objectMapper.readValue(data, classType);
        } catch (JsonProcessingException e) {
            AqualityServices.getLogger().info(e.getMessage());
            throw new RuntimeException(e);
        }
    }

    public static <T> List<T> getListObjects(String data, Class<T> classType) {
        ObjectMapper objectMapper = new ObjectMapper();
        JavaType type = objectMapper.getTypeFactory().constructCollectionType(List.class, classType);
        try {
            return objectMapper.readValue(data, type);
        } catch (JsonProcessingException e) {
            AqualityServices.getLogger().info(e.getMessage());
            throw new RuntimeException(e);
        }
    }
}
