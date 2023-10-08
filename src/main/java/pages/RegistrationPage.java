package pages;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selenide.$;

public class RegistrationPage {
    public SelenideElement
            firstName = $("#pFirstName"),
            lastName = $("#pLastName"),
            email = $("#pEmail"),
            phone = $("#pPhone"),
            dateOfBirth = $("#pDateBirth"),
            password = $("#pPassword"),
            registrationButton = $(".dashbord-registration"),
            passwordConfirm = $("#pConfirmPassword"),
            warning = $(".physicalInfoMassage");
}
