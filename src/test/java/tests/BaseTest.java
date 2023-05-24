package tests;

import org.testng.annotations.BeforeMethod;
import utils.DBUtil;

public class BaseTest {
    @BeforeMethod
    public void connectToDb() {
        DBUtil.setupDataSource();
    }
}
