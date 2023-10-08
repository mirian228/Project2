package pages;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selenide.$;

public class LoginPage {
    public SelenideElement registerButton = $(".register"),
            loginWindow = $(".main-page-login");
}
