package task5.project.forms;

import aquality.selenium.elements.interfaces.IButton;
import aquality.selenium.forms.Form;
import org.openqa.selenium.*;
import task5.utils.Logger;

public class WelcomePage extends Form {

    private IButton openFirstCard = getElementFactory().getButton(By.xpath("//a[contains(@class,'start__link')]"),"openFirstCard");

    public WelcomePage() {
        super(By.xpath("//button[contains(@class,'start__button')]"), "welcomePage");
    }

    public void openedFirsCard() {
        openFirstCard.click();
        Logger.info("FirsCard has been opened");
    }

    public boolean isOpened() {
        return getElementFactory().getButton(getLocator(),"ButtonNo").getElement().isDisplayed();
    }

    public FirstCardPage getFirstCardPage() {
        return new FirstCardPage();
    }

    public SecondCardPage getSecondCardPage() {
        return new SecondCardPage();
    }
    public ThirdCardPage getThirdCardPage() {
        return new ThirdCardPage();
    }
}
