package task5.project.forms;

import aquality.selenium.elements.interfaces.IButton;
import aquality.selenium.forms.Form;
import org.openqa.selenium.By;
import task5.utils.Logger;

public class HelpForm extends Form {
    private IButton hideHelp = getElementFactory().getButton(By.xpath("//button[contains(@class,'help-form__send-to-bottom-button')]"), "hideHelp");
    private By closedHelp = By.xpath("//div[contains(@class,'help-form')][contains(@class,'is-hidden')]");

    public HelpForm() {
        super(By.xpath("//div[@class='help-form']"), "helpForm");
    }

    public void hideFormHelp() {
        hideHelp.click();
        Logger.info("FormHelp has been hidden");
    }

    public boolean isClosed() {
        return getElementFactory().getButton(closedHelp,"closedHelp").getElement().isDisplayed();
    }
}

