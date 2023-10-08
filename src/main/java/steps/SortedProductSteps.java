package steps;

import io.qameta.allure.Step;
import pages.SortedProductPage;
import util.HelperFunctions;

public class SortedProductSteps {

    private SortedProductPage sortedProductPage = new SortedProductPage();


    @Step("Check that the descending order works correctly by comparing prices of all items")
    public boolean checkDescendingOrderCorrectness() {
        for (int i = 0; i < sortedProductPage.voucherPrices.size() - 1; i++) {
            String voucherText = sortedProductPage.voucherPrices.get(i).getText();
            String nextVoucherText = sortedProductPage.voucherPrices.get(i + 1).getText();
            if (HelperFunctions.parseStringWithGelSymbol(voucherText) < HelperFunctions.parseStringWithGelSymbol(nextVoucherText)) {
                return false;
            }
        }
        return true;
    }
}
