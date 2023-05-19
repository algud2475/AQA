package tests;

import io.restassured.RestAssured;
import org.testng.annotations.BeforeTest;
import utils.JSONReader;

public class BaseTest {
    @BeforeTest
    public static void setBaseURL() {
        RestAssured.baseURI = JSONReader.getConfig().getValue("/baseURI").toString();
    }
}
