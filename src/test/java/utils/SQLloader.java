package utils;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public enum SQLloader {
    BROWSERS_COUNT("BROWSERS_COUNT.sql"),
    FILTER_BEFORE_DATE("FILTER_BEFORE_DATE.sql"),
    MIN_WORKING("MIN_WORKING.sql"),
    TESTS_COUNT("TESTS_COUNT.sql");

    private String path;
    private static final String BASE_PATH = "src/test/resources/SQLrequests/";

    public String get() {
        return FileUtil.readFileToString(BASE_PATH + path);
    }
}
