package task5.project.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import task5.project.forms.FirstCardPage;
import task5.project.forms.WelcomePage;
import task5.project.forms.SecondCardPage;
import task5.utils.ConfigFileReader;

public class MyTest extends BaseTest {
    private static int amountInterests = 3;

    @Test
    public void fillCards() {
        WelcomePage welcomePage = new WelcomePage();
        Assert.assertTrue(welcomePage.isOpened(), "Welcome page has not been opened");

        welcomePage.openedFirsCard();
        TestSteps.waitPage();
        Assert.assertTrue(welcomePage.getFirstCardPage().isOpened(), "FirstCardPage has not been opened");

        FirstCardPage firstCardPage = welcomePage.getFirstCardPage();
        firstCardPage.fillAllForms();
        firstCardPage.acceptTerms();
        firstCardPage.openSecondCardPage();
        TestSteps.waitPage();
        Assert.assertTrue(welcomePage.getSecondCardPage().isOpened(), "FirstCardPage has not been opened");

        SecondCardPage secondCardPage = welcomePage.getSecondCardPage();
        secondCardPage.selectInterest(amountInterests);
        secondCardPage.upLoadImage(ConfigFileReader.getPathImage());
        secondCardPage.openThirdPage();
        TestSteps.waitPage();
        Assert.assertTrue(welcomePage.getThirdCardPage().isOpened(), "ThirdCardPage has not been opened");
    }

    @Test
    public void closeHelpForm() {
        WelcomePage welcomePage = new WelcomePage();
        FirstCardPage firstCardPage = welcomePage.getFirstCardPage();
        Assert.assertTrue(welcomePage.isOpened(), "HomePage has not been opened");

        welcomePage.openedFirsCard();
        TestSteps.waitPage();
        Assert.assertTrue(firstCardPage.isOpened(), "FirstCardPage has not been opened");

        firstCardPage.getHelpForm().hideFormHelp();
        Assert.assertTrue(firstCardPage.getHelpForm().isClosed(), "HelpForm has not been closed");
    }

    @Test
    public void closeCookie() {
        WelcomePage welcomePage = new WelcomePage();
        FirstCardPage firstCardPage = welcomePage.getFirstCardPage();
        Assert.assertTrue(welcomePage.isOpened(), "HomePage has not been opened");

        welcomePage.openedFirsCard();
        TestSteps.waitPage();
        Assert.assertTrue(firstCardPage.isOpened(), "FirstCardPage has not been opened");

        firstCardPage.getCookieForm().acceptCookie();
        Assert.assertTrue(firstCardPage.getCookieForm().isClosed(), "Cookie has not been closed");
    }

    @Test
    public void timer() {
        WelcomePage welcomePage = new WelcomePage();
        FirstCardPage firstCardPage = welcomePage.getFirstCardPage();
        Assert.assertTrue(welcomePage.isOpened(),"HomePage has not been opened");

        welcomePage.openedFirsCard();
        TestSteps.waitPage();
        Assert.assertTrue(firstCardPage.isOpened(),"FirstCardPage has not been opened");
        Assert.assertEquals(firstCardPage.getTime(), "00:00:00", "Timer has not started from zero");
    }
}

