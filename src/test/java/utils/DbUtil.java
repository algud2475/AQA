package utils;

import com.mysql.cj.jdbc.MysqlDataSource;

import javax.sql.DataSource;

import java.sql.*;

public class DbUtil {
    private static DataSource dataSource;

    public static DataSource getDataSource() {
        if (dataSource == null) {
            dataSource = buildDataSource();
        }
        return dataSource;
    }

    private static DataSource buildDataSource() {
        MysqlDataSource dataSource = new MysqlDataSource();
        dataSource.setServerName("localhost");
        dataSource.setPort(3306);
        dataSource.setDatabaseName("union_reporting");
        dataSource.setUser("root");
        dataSource.setPassword("lmxdd14z");
        try (Connection connection = dataSource.getConnection()) {
            System.out.println("Connection established");
            //Statement statement = connection.createStatement();
            //ResultSet resultSet = statement.executeQuery("SELECT * FROM test");
            //resultSet.next();
            //String name = resultSet.getString("name");
            //System.out.println(name);
        } catch (SQLException e) {
            //AqualityServices.getLogger().error(e.getMessage());
            System.err.println("Connection failed. " + e.getMessage());
            throw new RuntimeException(e);
        }
        return dataSource;
    }
}
