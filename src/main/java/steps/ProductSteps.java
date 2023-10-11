package steps;

import com.codeborne.selenide.ClickOptions;
import io.qameta.allure.Step;
import pages.ProductPage;
import data.CommonData;

public class ProductSteps {
    private ProductPage productPage = new ProductPage();

    @Step("Add first returned item to favorites list")
    public ProductSteps clickOnFirstAddToWishlistButton() {
        productPage.wishlistButton.click(ClickOptions.usingJavaScript());
        return this;
    }


    @Step("Check that vouchers are not sold out")
    public boolean checkVoucherStatus() {
        String soldOutText = productPage.voucherDiagram.getAttribute("data-width");
        int soldOutValue = Integer.parseInt(soldOutText);
        if (soldOutValue >= 100) {
            System.out.println(String.format("Vouchers are sold out"));
            return false;
        } else if (soldOutValue == 0) {
            System.out.println("All vouchers are available at the moment, none of them are sold");
            return true;
        }
        System.out.println(String.format("Only %d%% of vouchers are sold out", soldOutValue));
        return true;
    }

    @Step("Click on sort element dropdown")
    public ProductSteps clickSortElementDropDown() {
        productPage.sortingFilter.click();
        return this;
    }

    @Step("Choose descending order filter")
    public ProductSteps applyDescendingOrderFilter() {
        productPage.sortingFilter.selectOption(CommonData.DESCENDING_PRICE_TEXT);
        return this;
    }

    @Step("Navigate to the first returned item")
    public ProductSteps navigateToFirstItem() {
        productPage.firstItem.click();
        return this;
    }
}
