package task8.project.forms;

import aquality.selenium.elements.interfaces.IButton;
import aquality.selenium.elements.interfaces.IElement;
import aquality.selenium.elements.interfaces.ITextBox;
import aquality.selenium.forms.Form;
import org.openqa.selenium.By;

public class WelcomePage extends Form {

    private final ITextBox login = getElementFactory().getTextBox(By.id("index_email"), "login");
    private final ITextBox password = getElementFactory().getTextBox(By.id("index_pass"), "password");
    private final IButton logIn = getElementFactory().getButton(By.id("index_login_button"), "logIn");
    private final IButton myProfile = getElementFactory().getButton(By.xpath("//span[contains(@class,'left_label')][contains(@class,'inl_bl')]"), "myProfile");
    private final By captcha = By.xpath("//div[contains(@class,'box_layout')]");


    public WelcomePage() {
        super(By.id("index_login"), "welcome Page");
    }

    private void fillLoginAndPassword(String login, String pass) {
        this.login.clearAndType(login);
        password.clearAndType(pass);
    }

    public void logIn(String login, String pass) {
        fillLoginAndPassword(login, pass);
        logIn.click();
    }

    public void goToMyProfile() {
        myProfile.click();
    }

    public void timeForCaptcha() throws InterruptedException {
        while (getElementFactory().findElements(captcha, IElement.class).size() != 0) {
            Thread.sleep(500);
        }
    }

    public boolean isOpened() {
        return getElementFactory().getTextBox(getLocator(), getName()).getElement().isDisplayed();
    }
}



