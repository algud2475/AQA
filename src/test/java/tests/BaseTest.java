package tests;

import aquality.selenium.browser.AqualityServices;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import utils.JSONReader;

public class BaseTest {
    private static final String DEFAULT_URL = JSONReader.getConfig().getValue("/WebsiteConfig/MainPageURL").toString();

    @BeforeMethod
    public void beforeTests() {
        AqualityServices.getBrowser().maximize();
        AqualityServices.getBrowser().goTo(DEFAULT_URL);
    }

    @AfterMethod
    public void afterTests() {
        AqualityServices.getBrowser().quit();
    }
}
