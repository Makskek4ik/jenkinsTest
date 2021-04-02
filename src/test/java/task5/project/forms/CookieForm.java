package task5.project.forms;

import aquality.selenium.elements.interfaces.IButton;
import aquality.selenium.forms.Form;
import org.openqa.selenium.By;
import java.util.List;

public class CookieForm extends Form {
    private IButton accept = getElementFactory().getButton(By.xpath("//button[contains(@class,'button--solid')][contains(@class,'button--transparent')]"), "accept");

    protected CookieForm() {
        super(By.xpath("//div[contains(@class,'cookies')]"), "cookieForm");
    }

    public void acceptCookie() {
        accept.click();
    }

    public boolean isClosed() {
        List<IButton> elements = getElementFactory().findElements(getLocator(),"name",IButton.class);
        return elements.size() == 0;
    }
}
