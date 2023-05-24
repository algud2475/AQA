package tests;

import aquality.selenium.browser.AqualityServices;
import org.testng.annotations.Test;
import steps.TableSteps;
import utils.DBUtil;

import java.util.LinkedHashMap;
import java.util.List;

public class TestDB extends BaseTest {
    private static final String QUERY_TABLE_MIN_WORKING = "SELECT (SELECT name FROM project WHERE id = test.project_id) AS project, " +
            "name, MIN(TIMESTAMPDIFF(SECOND, start_time, end_time)) as min_working_time FROM test GROUP BY name ORDER BY project, name;";
    private static final String QUERY_TABLE_TESTS_COUNT = "SELECT (SELECT name FROM project WHERE id = test.project_id) AS project, " +
            "COUNT(name) AS tests_count FROM test GROUP BY project ORDER BY project;";
    private static final String QUERY_TABLE_FILTER_BY_DATE = "SELECT (SELECT name FROM project WHERE id = test.project_id) AS project, " +
            "name, DATE(start_time) AS date FROM test WHERE DATE(start_time) < '2016-10-13' ORDER BY project, name;";
    private static final String QUERY_TABLE_BROWSERS_COUNT = "SELECT browser, COUNT(id) AS tests_count FROM test WHERE browser IN ('chrome', 'firefox') GROUP BY browser;";

    @Test
    public static void testDb() {
        AqualityServices.getLogger().info("TASK 1 ACTION: Для каждого теста вывести минимальное время работы. Сортировать по проектам и по тестам внутри проектов");
        List<LinkedHashMap<String, Object>> tableMinWorking = DBUtil.sendQuery(QUERY_TABLE_MIN_WORKING);
        TableSteps.printResults(tableMinWorking);
        AqualityServices.getLogger().info("TASK 2 ACTION: Вывести в лог все проекты с указанием количества уникальных тестов на проекте");
        List<LinkedHashMap<String, Object>> tableTestsCount = DBUtil.sendQuery(QUERY_TABLE_TESTS_COUNT);
        TableSteps.printResults(tableTestsCount);
        AqualityServices.getLogger().info("TASK 3 ACTION: Вывести тесты для каждого прoекта, которые выполнялись до 13 октября 2016. Сортировать по проектам и по тестам внутри проектов");
        List<LinkedHashMap<String, Object>> tableFilterByDate = DBUtil.sendQuery(QUERY_TABLE_FILTER_BY_DATE);
        TableSteps.printResults(tableFilterByDate);
        AqualityServices.getLogger().info("TASK 4 ACTION: Вывести количество тестов, выполнявшихся на Firefox и на Chrome");
        List<LinkedHashMap<String, Object>> tableBrowsersCount = DBUtil.sendQuery(QUERY_TABLE_BROWSERS_COUNT);
        TableSteps.printResults(tableBrowsersCount);
    }
}

