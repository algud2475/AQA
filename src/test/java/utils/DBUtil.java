package utils;

import aquality.selenium.browser.AqualityServices;

import java.sql.*;
import java.util.*;

public class DBUtil {
    private static final String SERVER_ULR = JSONReader.getConfigDB().getValue("/SERVER_ULR").toString();
    private static final String USER = JSONReader.getConfigDB().getValue("/USER").toString();
    private static final String PASSWORD = JSONReader.getConfigDB().getValue("/PASSWORD").toString();

    public static List<LinkedHashMap<String, Object>> sendQuery(String query) {
        List<LinkedHashMap<String, Object>> answer = new ArrayList<>();
        try (Connection connection = DriverManager.getConnection(SERVER_ULR, USER, PASSWORD);
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(query)) {
            List<String> columnNames = getColumnNames(resultSet);
            while (resultSet.next()) {
                LinkedHashMap<String, Object> inner = new LinkedHashMap<>();
                for (String name : columnNames) {
                    inner.put(name, resultSet.getString(name));
                }
                answer.add(inner);
            }
        } catch (SQLException e) {
            AqualityServices.getLogger().error(e.getMessage());
            throw new RuntimeException(e);
        }
        return answer;
    }

    private static List<String> getColumnNames(ResultSet resultSet) {
        List<String> answer = new ArrayList<>();
        try {
            ResultSetMetaData resultSetMetaData = resultSet.getMetaData();
            int numberOfColumns = resultSetMetaData.getColumnCount();
            for (int i = 1; i <= numberOfColumns; i++) {
                answer.add(resultSetMetaData.getColumnName(i));
            }
        } catch (SQLException e) {
            AqualityServices.getLogger().error(e.getMessage());
            throw new RuntimeException(e);
        }
        return answer;
    }
}
