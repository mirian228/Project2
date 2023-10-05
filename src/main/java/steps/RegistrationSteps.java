package steps;

import io.qameta.allure.Step;
import pages.RegistrationPage;

public class RegistrationSteps {
    private RegistrationPage registrationPage = new RegistrationPage();

    @Step("Fill first name field with {0}")
    public RegistrationSteps fillFirstName(String firstName) {
        registrationPage.firstName.setValue(firstName);
        return this;
    }

    @Step("Fill last name field with {0}")
    public RegistrationSteps fillLastName(String lastName) {
        registrationPage.lastName.setValue(lastName);
        return this;
    }

    @Step("Fill email field with {0}")
    public RegistrationSteps fillEmail(String email) {
        registrationPage.email.setValue(email);
        return this;
    }

    @Step("Fill phone field with {0}")
    public RegistrationSteps fillPhoneNumber(String phoneNumber) {
        registrationPage.phone.setValue(phoneNumber);
        return this;
    }

    @Step("Fill birth date field with {0}")
    public RegistrationSteps fillDate(String date) {
        registrationPage.dateOfBirth.setValue(date);
        return this;
    }

    @Step("Fill password field with {0}")
    public RegistrationSteps fillPassword(String password) {
        registrationPage.password.setValue(password);
        return this;
    }

    @Step("Fill password confirmation field with {0}")
    public RegistrationSteps fillPasswordConfirm(String password) {
        registrationPage.passwordConfirm.setValue(password);
        return this;
    }


    @Step("Click on register submit button")
    public RegistrationSteps clickOnRegistrationButton() {
        registrationPage.registrationButton.click();
        return this;
    }


    @Step("Return the warning message")
    public String getWarningMessage() {
        return registrationPage.warning.getText().trim();
    }


}
