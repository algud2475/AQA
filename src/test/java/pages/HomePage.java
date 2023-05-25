package pages;

import aquality.selenium.elements.interfaces.ILink;
import aquality.selenium.forms.Form;
import org.openqa.selenium.By;

public class HomePage extends Form {
    private final ILink clickHereLink = getElementFactory().getLink(By.xpath("//a[contains(@class,'start__link')]"), "Click here Link");

    public HomePage() {
        super(By.xpath("//div[contains(@class,'start')]"), "Home page");
    }

    public void clickHereLink() {
        clickHereLink.click();
    }
}
