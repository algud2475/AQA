package steps;

import tables.Api_key;
import utils.DbUtil;

import javax.sql.DataSource;
import java.sql.*;
import java.util.*;

public class Api_keySteps {
    public static void sendToDb(Api_key apiKey) {
        DataSource dataSource = DbUtil.getDataSource();
        Api_key answer = null;
        String query = "INSERT INTO api_key (id, key, key_info) values (?, ?, ?)";
        try (Connection connection = dataSource.getConnection()) {
            PreparedStatement statement = connection.prepareStatement(query);
            setValue(statement, 1, movie.getYear());
            ResultSet resultSet = statement.executeQuery();
            resultSet.next();
            String name = resultSet.get("name");

            //System.out.println(name);
        } catch (SQLException e) {
            //AqualityServices.getLogger().error(e.getMessage());
            System.err.println("Connection failed. " + e.getMessage());
            throw new RuntimeException(e);
        }
    }

    public static List<Map<String, Object>> getQuery(String query) {
        DataSource dataSource = DbUtil.getDataSource();
        List<Map<String, Object>> answer = new ArrayList<>();
        Map<String, Object> inner = new HashMap<>();
        try (Connection connection = dataSource.getConnection();
        Statement statement = connection.createStatement()) {
            try (ResultSet resultSet = statement.executeQuery(query)) {
                while (resultSet.next()) {
                    
                }
            }
        } catch (SQLException e) {
            //AqualityServices.getLogger().error(e.getMessage());
            System.err.println("Connection failed. " + e.getMessage());
            throw new RuntimeException(e);
        }
    }
}
