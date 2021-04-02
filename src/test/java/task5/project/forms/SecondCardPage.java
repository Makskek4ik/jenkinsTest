package task5.project.forms;

import aquality.selenium.elements.interfaces.IButton;
import aquality.selenium.elements.interfaces.ICheckBox;
import aquality.selenium.forms.Form;
import org.openqa.selenium.By;
import task5.utils.Logger;
import task5.utils.RandomData;
import task5.utils.UpLoadFile;
import java.util.ArrayList;
import java.util.List;
import static java.lang.String.format;

public class SecondCardPage extends Form {
    private String avatarForm = "//*[contains(@class,'avatar-and-interests__%s')]";
    private By interests = By.xpath(format(avatarForm, "interests-list__item"));
    private IButton upLoad = getElementFactory().getButton(By.xpath(format(avatarForm, "upload-button")), "upLoad");
    private By interest = By.xpath("//span[contains(@class,'checkbox__box')]");
    private IButton openThirdCard = getElementFactory().getButton(By.xpath("//button[contains(@class,'button--stroked')][contains(@class,'button--fluid')]"),"openThirdCard");

    protected SecondCardPage() {
        super(By.xpath("//div[contains(@class,'avatar-and-interests__form')]"),"SecondCard");
    }

    public void selectInterest(int amountInterests) {
        List<ICheckBox> allInterests = this.getElementFactory().findElements(interests, "interests", ICheckBox.class);
        unSelectAll();
        ArrayList<ICheckBox> randomInterests = RandomData.randomInterest(allInterests, amountInterests);

        for (int i = 0; i < amountInterests; ++i) {
            randomInterests.get(i).findChildElement(interest, ICheckBox.class).check();
        }
        Logger.info("Three random interests has been selected");
    }

    public void upLoadImage(String path) {
        upLoad.click();
        UpLoadFile.upLoadImage(path);
        Logger.info("Image has been uploaded");
    }

    public void openThirdPage() {
        openThirdCard.click();
        Logger.info("ThirdPage has been opened");
    }

    public boolean isOpened() {
        return getElementFactory().getButton(getLocator(),"dataForm").getElement().isDisplayed();
    }

    public void unSelectAll() {
        List<ICheckBox> allInterests = getElementFactory().findElements(interests, "interests", ICheckBox.class);
        for (int i = allInterests.size() - 1; i >= 0; i--) {
            ICheckBox allInterest = allInterests.get(i);
            if (allInterest.getText().equals("Unselect all")) {
                allInterest.findChildElement(interest, ICheckBox.class).check();
                break;
            }
        }
        Logger.info("Unselect all has been selected");
    }
}

