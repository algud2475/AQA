package pages;

import aquality.selenium.browser.AqualityServices;
import aquality.selenium.elements.interfaces.IButton;
import aquality.selenium.forms.Form;
import org.openqa.selenium.By;

public class NewsPage extends Form {
    private IButton myProfileButton = AqualityServices.getElementFactory().getButton(By.xpath(
            "//li[@id='l_pr']"), "MyProfile button");

    public NewsPage() {
        super(By.xpath("//div[@id='main_feed']"), "News page");
    }

    public void clickMyProfile() {
        myProfileButton.click();
    }
}
