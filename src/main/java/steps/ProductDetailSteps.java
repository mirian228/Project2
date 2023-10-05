package steps;

import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import pages.ProductDetailPage;
import util.HelperFunctions;

import java.util.Set;

public class ProductDetailSteps {
    private ProductDetailPage productDetailPage = new ProductDetailPage();
    private WebDriver driver;

    @Step("Click on share button")
    public ProductDetailSteps clickOnShareButton() {
        productDetailPage.shareButton.click();
        return this;
    }

    @Step("Switch to popup window")
    public void switchToPopupWindow() {
        driver = HelperFunctions.getWebDriverInstance();
        Set<String> windowHandles = driver.getWindowHandles();
        String secondWindowHandle = windowHandles.toArray()[1].toString();
        driver.switchTo().window(secondWindowHandle);
    }

}
