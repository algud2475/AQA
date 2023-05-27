package tests;

import aquality.selenium.browser.AqualityServices;
import org.testng.annotations.Test;
import steps.TableSteps;
import utils.DBUtil;
import utils.SQLloader;

import java.util.LinkedHashMap;
import java.util.List;

public class TestDB {
    @Test
    public static void testDb() {
        AqualityServices.getLogger().info("TASK 1 ACTION: Для каждого теста вывести минимальное время работы. Сортировать по проектам и по тестам внутри проектов");
        List<LinkedHashMap<String, Object>> tableMinWorking = DBUtil.sendQuery(SQLloader.MIN_WORKING.get());
        TableSteps.printResults(tableMinWorking);
        AqualityServices.getLogger().info("TASK 2 ACTION: Вывести в лог все проекты с указанием количества уникальных тестов на проекте");
        List<LinkedHashMap<String, Object>> tableTestsCount = DBUtil.sendQuery(SQLloader.TESTS_COUNT.get());
        TableSteps.printResults(tableTestsCount);
        AqualityServices.getLogger().info("TASK 3 ACTION: Вывести тесты для каждого прoекта, которые выполнялись до 13 октября 2016. Сортировать по проектам и по тестам внутри проектов");
        List<LinkedHashMap<String, Object>> tableFilterByDate = DBUtil.sendQuery(String.format(SQLloader.FILTER_BEFORE_DATE.get(), "2016-10-13"));
        TableSteps.printResults(tableFilterByDate);
        AqualityServices.getLogger().info("TASK 4 ACTION: Вывести количество тестов, выполнявшихся на Firefox и на Chrome");
        List<LinkedHashMap<String, Object>> tableBrowsersCount = DBUtil.sendQuery(SQLloader.BROWSERS_COUNT.get());
        TableSteps.printResults(tableBrowsersCount);
    }
}

