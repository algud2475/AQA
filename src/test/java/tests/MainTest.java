package tests;

import accounts.Account;
import aquality.selenium.browser.AqualityServices;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.*;
import utils.JSONReader;

public class MainTest extends BaseTest {
    private static final int NUMBER_OF_INTERESTS = Integer.parseInt(JSONReader.getTestData().getValue("/testWebsiteData/NUMBER_OF_INTERESTS").toString());
    private static final String IMAGE_NAME = JSONReader.getTestData().getValue("/testWebsiteData/IMAGE_NAME").toString();
    private static final String EXPECTED_TIMER_VALUE = JSONReader.getTestData().getValue("/testWebsiteData/EXPECTED_TIMER_VALUE").toString();
    private final Account validAccount = Account.getValidAccount();

    @Test
    public void theFirstTestCase() {
        HomePage homePage = new HomePage();

        AqualityServices.getLogger().info("1 STEP ACTION:NAVIGATE TO HOME PAGE");
        Assert.assertTrue(homePage.state().waitForDisplayed(), "Welcome page is not open");
        AqualityServices.getLogger().info("1 STEP RESULT: WELCOME PAGE IS OPENED");

        AqualityServices.getLogger().info("2 STEP ACTION: CLICK THE LINK IN TEXT 'Please click HERE to GO to the next page'");
        homePage.clickHereLink();
        GamePage gamePage = new GamePage();

        Assert.assertTrue(gamePage.state().waitForDisplayed(), "Next page is not open");
        AqualityServices.getLogger().info("2 STEP RESULT: NEXT PAGE IS OPENED");

        LoginForm loginForm = new LoginForm();

        Assert.assertTrue(loginForm.state().waitForDisplayed(), "The first card is not open");
        AqualityServices.getLogger().info("2 STEP RESULT: THE '1' CARD IS OPENED");

        AqualityServices.getLogger().info("3 STEP ACTION: INPUT RANDOM VALID PASSWORD, EMAIL, ACCEPT THE TERMS OF USE AND CLICK 'NEXT' BUTTON");
        loginForm.typeFields(validAccount);
        loginForm.selectRandomDomainExtension();
        loginForm.acceptConditions();
        loginForm.clickNext();
        AvatarInterestsForm avatarInterestsForm = new AvatarInterestsForm();

        Assert.assertTrue(avatarInterestsForm.state().waitForDisplayed(), "The second card is not open");
        AqualityServices.getLogger().info("3 STEP RESULT: THE '2' CARD IS OPENED");

        AqualityServices.getLogger().info("4 STEP ACTION: CHOOSE 3 RANDOM INTERESTS, UPLOAD IMAGE, CLICK 'NEXT' BUTTON");
        avatarInterestsForm.chooseNumberInterests(NUMBER_OF_INTERESTS);
        avatarInterestsForm.uploadImage(IMAGE_NAME);

        Assert.assertTrue(avatarInterestsForm.imageIsUploaded(), "The image is not uploaded");

        avatarInterestsForm.clickNextButton();
        PersonalDetailsForm personalDetailsForm = new PersonalDetailsForm();

        Assert.assertTrue(personalDetailsForm.state().waitForDisplayed(), "The third card is not open");
        AqualityServices.getLogger().info("4 STEP RESULT: THE '3' CARD IS OPENED");
    }

    @Test
    public void theSecondTestCase() {
        HomePage homePage = new HomePage();

        AqualityServices.getLogger().info("1 STEP ACTION:NAVIGATE TO HOME PAGE");
        Assert.assertTrue(homePage.state().waitForDisplayed(), "Welcome page is not open");
        AqualityServices.getLogger().info("1 STEP RESULT: WELCOME PAGE IS OPENED");

        AqualityServices.getLogger().info("2.1 STEP ACTION: CLICK THE LINK IN TEXT 'Please click HERE to GO to the next page'");
        homePage.clickHereLink();
        GamePage gamePage = new GamePage();

        Assert.assertTrue(gamePage.state().waitForDisplayed(), "Next page is not open");
        AqualityServices.getLogger().info("2.1 STEP RESULT: NEXT PAGE IS OPENED");

        HelpForm helpForm = new HelpForm();

        Assert.assertTrue(helpForm.state().waitForDisplayed(), "The Help form is hidden");
        AqualityServices.getLogger().info("2.1 STEP RESULT: HELP FORM IS VISIBLE");

        AqualityServices.getLogger().info("2.2 STEP ACTION: HIDE HELP FORM");
        helpForm.clickHideFormButton();

        Assert.assertFalse(helpForm.state().waitForDisplayed(), "The Help form is not hidden");
        AqualityServices.getLogger().info("2.2 STEP RESULT: HELP FORM IS HIDDEN");
    }

    @Test
    public void theThirdTestCase() {
        HomePage homePage = new HomePage();

        AqualityServices.getLogger().info("1 STEP ACTION:NAVIGATE TO HOME PAGE");
        Assert.assertTrue(homePage.state().waitForDisplayed(), "Welcome page is not open");
        AqualityServices.getLogger().info("1 STEP RESULT: WELCOME PAGE IS OPENED");

        AqualityServices.getLogger().info("2.1 STEP ACTION: CLICK THE LINK IN TEXT 'Please click HERE to GO to the next page'");
        homePage.clickHereLink();
        GamePage gamePage = new GamePage();

        Assert.assertTrue(gamePage.state().waitForDisplayed(), "Next page is not open");
        AqualityServices.getLogger().info("2.1 STEP RESULT: NEXT PAGE IS OPENED");

        CookiesForm cookiesForm = new CookiesForm();

        Assert.assertTrue(cookiesForm.state().waitForDisplayed(), "The Cookies form is not open");
        AqualityServices.getLogger().info("2.1 STEP RESULT: COOKIES FORM IS VISIBLE");

        AqualityServices.getLogger().info("2.2 STEP ACTION: ACCEPT COOKIES");
        cookiesForm.clickAcceptCookiesButton();

        Assert.assertFalse(cookiesForm.state().waitForDisplayed(), "The Cookies form is not closed");
        AqualityServices.getLogger().info("2.2 STEP RESULT: COOKIES IS ACCEPTED");
    }

    @Test
    public void theFourthTestCase() {
        HomePage homePage = new HomePage();

        AqualityServices.getLogger().info("1 STEP ACTION:NAVIGATE TO HOME PAGE");
        Assert.assertTrue(homePage.state().waitForDisplayed(), "Welcome page is not open");
        AqualityServices.getLogger().info("1 STEP RESULT: WELCOME PAGE IS OPENED");

        AqualityServices.getLogger().info("2.1 STEP ACTION: CLICK THE LINK IN TEXT 'Please click HERE to GO to the next page'");
        homePage.clickHereLink();
        GamePage gamePage = new GamePage();

        Assert.assertTrue(gamePage.state().waitForDisplayed(), "Next page is not open");
        AqualityServices.getLogger().info("2.1 STEP RESULT: NEXT PAGE IS OPENED");

        AqualityServices.getLogger().info("2.2 STEP ACTION: VALIDATE THAT TIMER STARTS FROM '00:00'");
        String actualTimerValue = gamePage.getTimerValue();

        Assert.assertEquals(actualTimerValue, EXPECTED_TIMER_VALUE, "Timer doesn`t start from 00:00");
        AqualityServices.getLogger().info("2.2 STEP RESULT: TIMER STARTS FROM '00:00'");
    }
}
