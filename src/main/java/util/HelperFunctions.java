package util;

import org.openqa.selenium.WebDriver;

import static com.codeborne.selenide.Selenide.webdriver;

public class HelperFunctions {

    public static int parseStringWithGelSymbol(String priceToParse) {
        int result = 0;
        char charToRemove = '₾';
        int charIndexToRemove = priceToParse.indexOf(charToRemove);

        if (charIndexToRemove != -1) {
            String modifiedString = priceToParse.substring(0, charIndexToRemove) + priceToParse.substring(charIndexToRemove + 1);
            result = Integer.parseInt(modifiedString);
        }

        return result;
    }

    public static WebDriver getWebDriverInstance() {
        return webdriver().driver().getWebDriver();
    }
}
