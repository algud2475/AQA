package utils;

import aquality.selenium.browser.AqualityServices;
import com.mysql.cj.jdbc.MysqlDataSource;

import javax.sql.DataSource;
import java.sql.*;
import java.util.*;

public class DBUtil {
    private static final String SERVER_NAME = "localhost";
    private static final int PORT = 3306;
    private static final String DATABASE_NAME = "union_reporting";
    private static final String USER = "root";
    private static final String PASSWORD = "";
    private static DataSource dataSource;

    public static void setupDataSource() {
        if (dataSource == null) {
            dataSource = buildDataSource();
        }
    }

    public static List<LinkedHashMap<String, Object>> sendQuery(String query) {
        List<LinkedHashMap<String, Object>> answer = new ArrayList<>();
        try (Connection connection = dataSource.getConnection();
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

    private static DataSource buildDataSource() {
        MysqlDataSource dataSource = new MysqlDataSource();
        dataSource.setServerName(SERVER_NAME);
        dataSource.setPort(PORT);
        dataSource.setDatabaseName(DATABASE_NAME);
        dataSource.setUser(USER);
        dataSource.setPassword(PASSWORD);
        try (Connection connection = dataSource.getConnection()) {
            AqualityServices.getLogger().info("Connection established");
        } catch (SQLException e) {
            AqualityServices.getLogger().error("Connection failed. " + e.getMessage());
            throw new RuntimeException(e);
        }
        return dataSource;
    }
}
