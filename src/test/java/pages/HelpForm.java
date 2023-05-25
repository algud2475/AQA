package pages;

import aquality.selenium.elements.interfaces.IButton;
import aquality.selenium.forms.Form;
import org.openqa.selenium.By;

public class HelpForm extends Form {
    private final IButton hideFormButton = getElementFactory().getButton(By.xpath(
            "//div[@class='help-form']//button[contains(@class,'help-form__send-to-bottom-button')]"), "Send to bottom button");
    public HelpForm() {
        super(By.xpath("//div[@class='help-form']"), "Help form");
    }

    public void clickHideFormButton() {
        hideFormButton.click();
    }
}
