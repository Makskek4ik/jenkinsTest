package task5.project.forms;

import aquality.selenium.elements.interfaces.IButton;
import aquality.selenium.elements.interfaces.IComboBox;
import aquality.selenium.elements.interfaces.ITextBox;
import aquality.selenium.forms.Form;
import org.openqa.selenium.By;
import task5.utils.Logger;
import task5.utils.RandomData;
import java.util.List;

import static java.lang.String.format;

public class FirstCardPage extends Form {
    private final String placeHolder = "//*[@id='app']//*[@placeholder='%s']";
    private final ITextBox password = getElementFactory().getTextBox(By.xpath(format(placeHolder, "Choose Password")), "password");
    private final ITextBox email = getElementFactory().getTextBox(By.xpath(format(placeHolder, "Your email")), "email");
    private final ITextBox domain = getElementFactory().getTextBox(By.xpath(format(placeHolder, "Domain")), "domain");
    private final IButton selectDomain = getElementFactory().getButton(By.xpath("//div[contains(@class,'dropdown__field')]"), "selectDomain");
    private final IButton accept = getElementFactory().getButton(By.xpath("//span[contains(@class,'icon-check')][contains(@class,'checkbox__check')]"), "accept");
    private final ITextBox timer = getElementFactory().getTextBox(By.xpath("//div[contains(@class,'timer--white')][contains(@class,'timer--center')]"), "timer");
    private final String highLevelDomains = "//div[contains(@class,'dropdown__list-item')]";
    private final IButton openSecondCard = getElementFactory().getButton(By.xpath("//*[@id='app']//a[@class='button--secondary']"),"openSecondCard");

    protected FirstCardPage() {
        super(By.xpath("//div[contains(@class,'login-form__container')]"), "firstCard");
    }

    public void fillAllForms() {
        String[] passAndEmail = RandomData.randomPassEmail();
        fillPassword(passAndEmail[0]);
        fillEmail(passAndEmail[1]);
        fillDomain("email");
        selectHighLevelDomain();
    }

    private void fillPassword(String value) {
        password.clearAndType(value);
        Logger.info("Password has been filled");
    }

    private void fillEmail(String value) {
        email.clearAndType(value);
        Logger.info("Email has been filled");
    }

    private void fillDomain(String value) {
        domain.clearAndType(value);
        Logger.info("Domain has been filled");
    }

    private void selectHighLevelDomain() {
        selectDomain.click();
        List<IComboBox> domains = getElementFactory().findElements(By.xpath(highLevelDomains), "domains", IComboBox.class);
        domains.get((int) (Math.random()* domains.size())).click();
        Logger.info("Domain high level has been selected");
    }

    public void acceptTerms() {
        accept.click();
        Logger.info("Terms and conditions has been accepted");

    }

    public CookieForm getCookieForm() {
        return new CookieForm();
    }

    public void openSecondCardPage() {
        openSecondCard.click();
        Logger.info("SecondCardPage has been opened");
    }

    public String getTime() {
        return timer.getText();
    }

    public boolean isOpened() {
        return getElementFactory().getButton(getLocator(),"loginForm").getElement().isDisplayed();
    }

    public HelpForm getHelpForm() {
        return new HelpForm();
    }
}