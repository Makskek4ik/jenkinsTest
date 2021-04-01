package task8.project.tests;

import aquality.selenium.browser.AqualityServices;
import aquality.selenium.browser.Browser;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import task8.utils.ConfigFileReader;

public class BaseTest {

    protected BaseTest() {
    }

    @BeforeMethod
    protected void beforeMethod() {
        ConfigFileReader.initConfigFileReader();
        getBrowser().maximize();
        getBrowser().goTo(ConfigFileReader.getApplicationUrl());
        getBrowser().waitForPageToLoad();
    }

    @AfterMethod
    public void afterTest() {
        if (AqualityServices.isBrowserStarted()) {
            AqualityServices.getBrowser().quit();
        }
    }

    protected Browser getBrowser() {
        return AqualityServices.getBrowser();
    }
}