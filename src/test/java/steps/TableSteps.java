package steps;

import aquality.selenium.browser.AqualityServices;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.*;

public class TableSteps {
    public static void printResults(List<LinkedHashMap<String, Object>> table) {
        int[] columnsWidth = getTableSize(table);
        Set<String> columns = table.get(0).keySet();
        StringBuilder header = new StringBuilder();
        int i = 0;
        for (String column : columns) {
            header.append(column);
            header.append(" ".repeat(columnsWidth[i] - column.length()));
            header.append("   | ");
            i++;
        }
        AqualityServices.getLogger().info(header.toString());
        AqualityServices.getLogger().info("-".repeat(header.toString().length() - 1));
        for (LinkedHashMap<String, Object> row : table) {
            i = 0;
            header = new StringBuilder();
            for (String column : columns) {
                header.append(row.get(column));
                header.append(" ".repeat(columnsWidth[i] - row.get(column).toString().length()));
                header.append("   | ");
                i++;
            }
            AqualityServices.getLogger().info(header.toString());
        }
    }

    private static int[] getTableSize(List<LinkedHashMap<String, Object>> table) {
        int[] size = new int[table.get(0).size()];
        Arrays.fill(size, 0);
        Set<String> headers = table.get(0).keySet();
        int j = 0;
        for (String header : headers) {
            if (header.length() > size[j]) {
                size[j] = header.length();
            }
            j++;
        }
        for (int i = 0; i < table.size(); i++) {
            List columns = new ArrayList<>(table.get(i).values());
            j = 0;
            for (Object column : columns) {
                if (column.toString().length() > size[j]) {
                    size[j] = column.toString().length();
                }
                j++;
            }
        }
        return size;
    }

    public static String convertToJsonString(List<LinkedHashMap<String, Object>> table) {
        String json = null;
        ObjectMapper mapper = new ObjectMapper();
        try {
            json = new ObjectMapper().writeValueAsString(table);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
        return json;
    }

    public static JsonNode convertToJsonObject(List<LinkedHashMap<String, Object>> table) {
        String json = null;
        JsonNode node = null;
        ObjectMapper mapper = new ObjectMapper();
        try {
            json = mapper.writeValueAsString(table);
            node = mapper.readTree(json);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
        return node;
    }
}
