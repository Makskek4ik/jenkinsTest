package task5.project.forms;

import aquality.selenium.forms.Form;
import org.openqa.selenium.By;

public class ThirdCardPage extends Form {

    protected ThirdCardPage() {
        super(By.xpath("//div[contains(@class,'personal-details__form')]"), "ThirdCardPage");
    }

    public boolean isOpened() {
        return getElementFactory().getButton(getLocator(),"detailForm").getElement().isDisplayed();
    }
}
