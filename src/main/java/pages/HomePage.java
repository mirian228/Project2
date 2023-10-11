package pages;

import com.codeborne.selenide.SelenideElement;
import data.CommonData;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$x;

public class HomePage {
    public SelenideElement
            acceptCookieButton = $(".acceptCookie"),
            holidayButton = $x("//li[@class='MoreCategories'][contains(normalize-space(), '" + CommonData.HOLIDAY_BUTTON_TEXT + "')]"),
            categoriesButton = $(".categoriesTitle"),
            loginButton = $(".HeaderTools.swoop-login");
}
