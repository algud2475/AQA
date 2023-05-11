package pages;

import aquality.selenium.browser.AqualityServices;
import aquality.selenium.forms.Form;
import org.openqa.selenium.By;

public class MyProfilePage extends Form {
    private final String locatorWallPostText = "//div[contains(@id,'_%d')]//div[contains(@class,'wall_post_text')]";
    public MyProfilePage() {
        super(By.xpath("//div[@id='react_rootprofile']"), "My Profile page");
    }

    public String getWallPostTextById(Integer id) {
        return AqualityServices.getElementFactory().getLabel(By.xpath(
                String.format(locatorWallPostText, id)), "Some wall post").getText();
    }

    //

}
