package task5.project.tests;

import aquality.selenium.browser.AqualityServices;

public class TestSteps {

    private TestSteps(){ }

    public static void waitPage(){
        AqualityServices.getBrowser().waitForPageToLoad();
    }
}
