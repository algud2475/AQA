package pages;

import aquality.selenium.elements.ElementType;
import aquality.selenium.elements.interfaces.IButton;
import aquality.selenium.elements.interfaces.ILabel;
import aquality.selenium.elements.interfaces.ILink;
import aquality.selenium.forms.Form;
import org.openqa.selenium.By;
import utils.FileSystemUtil;
import utils.RandomStringUtil;

import java.io.File;
import java.util.List;

public class AvatarInterestsForm extends Form {
    private ILink uploadImageLink = getElementFactory().getLink(By.xpath(
            "//a[@class='avatar-and-interests__upload-button']"), "Upload image link");
    private ILabel imageIsUploaded = getElementFactory().getLabel(By.xpath(
            "//div[@class='avatar-and-interests__avatar-image']"), "Upload image status bar");
    private List<IButton> interests = getElementFactory().findElements(By.xpath(
            "//label[@class='checkbox__label']"), ElementType.BUTTON);
    private IButton unselectAll = getElementFactory().getButton(By.xpath(
            "//label[@for='interest_unselectall']"), "Unselect all check-box");
    private IButton nextButton = getElementFactory().getButton(By.xpath(
            "//button[contains(@class,'button--stroked')]"), "Next button");
    private final String IGNORING_INTERESTS = "interest_selectall interest_unselectall";

    public AvatarInterestsForm() {
        super(By.xpath("//div[@class='avatar-and-interests__form']"), "Avatar and Interests Form");
    }

    public void uploadImage(String IMAGE_NAME) {
        uploadImageLink.click();
        File file = new File(IMAGE_NAME);
        FileSystemUtil.getFileByPath(file.getAbsolutePath());
    }

    public boolean imageIsUploaded() {
        return imageIsUploaded.state().waitForDisplayed();
    }

    public void chooseNumberInterests(int number) {
        unselectAllInterests();
        List<IButton> checkBoxes = interests;
        for (int i = 0; i < number; ) {
            int randomBoxNumber = RandomStringUtil.getRandomMinMaxLimitedNumber(0, checkBoxes.size());
            if (!IGNORING_INTERESTS.contains(checkBoxes.get(randomBoxNumber).getAttribute("for"))) {
                checkBoxes.get(randomBoxNumber).click();
                i++;
            }
            checkBoxes.remove(randomBoxNumber);
        }
    }

    public void unselectAllInterests() {
        unselectAll.click();
    }

    public void clickNextButton() {
        nextButton.click();
    }
}
