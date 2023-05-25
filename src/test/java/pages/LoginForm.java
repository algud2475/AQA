package pages;

import accounts.Account;
import aquality.selenium.elements.ElementType;
import aquality.selenium.elements.interfaces.*;
import aquality.selenium.forms.Form;
import org.openqa.selenium.By;
import utils.RandomStringUtil;

import java.util.List;

public class LoginForm extends Form {
    private ITextBox passwordField = getElementFactory().getTextBox(By.xpath(
            "//input[@placeholder='Choose Password']"), "Password field");
    private ITextBox emailField = getElementFactory().getTextBox(By.xpath(
            "//input[@placeholder='Your email']"), "Email field");
    private ITextBox domainField = getElementFactory().getTextBox(By.xpath(
            "//input[@placeholder='Domain']"), "Domain field");
    private IButton showDomainExtensionsButton = getElementFactory().getButton(By.xpath(
            "//div[@class='dropdown__opener']"), "Domain extension drop-down box");
    private By domainExtensionOption = By.xpath("//div[contains(@class,'dropdown__list-item')]");
    private IButton acceptConditionsButton = getElementFactory().getButton(By.xpath(
            "//label[@class='checkbox__label']"), "Accept Terms & Conditions checkbox");
    private ILink nextLink = getElementFactory().getLink(By.xpath(
            "//a[@class='button--secondary']"), "Next link");

    public LoginForm() {
        super(By.xpath("//div[@class='login-form']"), "Login form on Game page");
    }

    public void typeFields(Account account) {
        typePassword(account.getPassword());
        typeEmail(account.getEmail());
        typeDomain(account.getDomain());
    }

    public void typePassword(String password) {
        passwordField.clearAndType(password);
    }

    public void typeEmail(String email) {
        emailField.clearAndType(email);
    }

    public void typeDomain(String domain) {
        domainField.clearAndType(domain);
    }

    public void selectRandomDomainExtension() {
        showDomainExtensionsButton.click();
        List<IButton> domainExtensions = getElementFactory().findElements(
                domainExtensionOption, ElementType.BUTTON);
        int randomNumber = RandomStringUtil.getRandomMinMaxLimitedNumber(1, domainExtensions.size());
        domainExtensions.get(randomNumber).click();
    }

    public void acceptConditions() {
        acceptConditionsButton.click();
    }

    public void clickNext() {
        nextLink.click();
    }
}
