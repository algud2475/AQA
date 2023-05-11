package pages;

import aquality.selenium.forms.Form;
import org.openqa.selenium.By;

public class LoginPage extends Form {
    public LoginPage() {
        super(By.xpath("//div[@id='index_login']"), "Login page");
    }
}
