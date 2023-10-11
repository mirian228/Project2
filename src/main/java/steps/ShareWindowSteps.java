package steps;

import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import pages.ShareWindowPage;
import data.CommonData;
import util.HelperFunctions;

public class ShareWindowSteps {
    private ShareWindowPage shareWindowPage = new ShareWindowPage();


    @Step("Check that window with Facebook login page has appeared")
    public boolean checkFacebookWindow() {
        WebDriver driver = HelperFunctions.getWebDriverInstance();
        if (driver.getTitle().equalsIgnoreCase(CommonData.EXPECTED_TITLE) && shareWindowPage.loginButton.isDisplayed()) {
            System.out.println("Opened window title is: " + driver.getTitle());
            System.out.println("'Log In' button is presented on page");
            return true;
        }
        return false;
    }

}
