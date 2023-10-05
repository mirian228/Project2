package steps;

import io.qameta.allure.Step;
import pages.HomePage;

import java.util.NoSuchElementException;

public class HomeSteps {
    private HomePage homePage = new HomePage();

    @Step("Accept cookie if present")
    public HomeSteps acceptCookie() {
        try {
            homePage.acceptCookieButton.click();
        } catch (NoSuchElementException e) {
            System.out.println("No Cookie present");
        }
        return this;
    }
    @Step("Click on holiday button")
    public HomeSteps clickOnHolidayButton() {
        homePage.holidayButton.scrollTo().click();
        return this;
    }

    @Step("Click on categories button")
    public HomeSteps clickOnCategoriesButton() {
        homePage.categoriesButton.scrollTo().click();
        return this;
    }

    @Step("Navigate to login button")
    public HomeSteps clickOnLoginButton() {
        homePage.loginButton.scrollTo().click();
        return this;
    }
}
