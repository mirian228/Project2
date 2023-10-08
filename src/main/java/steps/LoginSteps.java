package steps;

import com.codeborne.selenide.Condition;
import io.qameta.allure.Step;
import pages.LoginPage;

public class LoginSteps {

    private LoginPage loginPage = new LoginPage();

    @Step("Navigate to registration")
    public LoginSteps clickOnRegistration() {
        loginPage.registerButton.click();
        return this;
    }

    @Step("Check that login window has appeared")
    public boolean checkIfLoginWindowAppeared() {
        loginPage.loginWindow.should(Condition.appear);
        return loginPage.loginWindow.isDisplayed();
    }
}
