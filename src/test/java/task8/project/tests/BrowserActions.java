package task8.project.tests;

import aquality.selenium.browser.AqualityServices;

public class BrowserActions {

        private BrowserActions(){ }

        public static void waitPage(){
            AqualityServices.getBrowser().waitForPageToLoad();
        }

        public static void goBack(){
            AqualityServices.getBrowser().goBack();
        }
}
