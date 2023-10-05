package config;

import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeTest;

import static com.codeborne.selenide.Configuration.*;
import static com.codeborne.selenide.Selenide.closeWebDriver;


public class ConfigTests {
    @BeforeTest(description = "Set driver Configuration", groups = {"Regression1", "Regression2"})
    public void setUp() {
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--start-maximized");
        browserCapabilities = options;
        browserSize = null;
        screenshots = true;
        savePageSource = false;
        pageLoadTimeout = 60000;
        timeout = 15000;
    }

    @AfterMethod(description = "Quit driver", groups = {"Regression1", "Regression2"})
    public void tearDown() {
        closeWebDriver();
    }


}
