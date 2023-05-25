package pages;

import aquality.selenium.elements.interfaces.ILabel;
import aquality.selenium.forms.Form;
import org.openqa.selenium.By;

public class GamePage extends Form {
    private final ILabel cardNumber = getElementFactory().getLabel(By.xpath(
            "//div[contains(@class,'page-indicator')]"), "Label with card number");
    private final ILabel timerLabel = getElementFactory().getLabel(By.xpath(
            "//div[contains(@class,'timer')]"), "Timer label");

    public GamePage() {
        super(By.xpath("//div[contains(@class,'game')]"), "Game page");
    }

    public String getCardNumber() {
        return cardNumber.getText().substring(0, 1);
    }

    public String getTimerValue() {
        return timerLabel.getText();
    }
}
