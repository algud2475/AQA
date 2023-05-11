package pages;

import aquality.selenium.forms.Form;
import org.openqa.selenium.By;

public class PasswordPage extends Form {
    public PasswordPage() {
        super(By.xpath("//form[contains(@class,'vkc__EnterPasswordNoUserInfo__content')]"), "Password page");
    }
}
