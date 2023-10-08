package steps;

import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import pages.FilteredHolidayPage;
import util.HelperFunctions;

public class FilteredHolidaySteps {
    private FilteredHolidayPage filteredHolidayPage = new FilteredHolidayPage();


    @Step("Check that all returned elements on the first page are in selected range of minimum price {0} and maximum price {1}")
    public Boolean checkSelectedPriceRange(String minPriceRange, String maximumPriceRange) {
        for (SelenideElement discountedPrice : filteredHolidayPage.discountedPrices.asDynamicIterable()) {
            SelenideElement voucherPriceElement = discountedPrice.find(".deal-voucher-price");
            String voucherPrice = voucherPriceElement.getText();
            int parsedMinPriceRange = Integer.parseInt(minPriceRange);
            int parsedMaximumPriceRange = Integer.parseInt(maximumPriceRange);
            int parsedVoucherPrice = HelperFunctions.parseStringWithGelSymbol(voucherPrice);
            if (parsedVoucherPrice < parsedMinPriceRange || parsedVoucherPrice > parsedMaximumPriceRange) {
                System.out.println(String.format("%d is not in the range of %d min price and %d max price", parsedVoucherPrice, parsedMinPriceRange, parsedMaximumPriceRange));
                return false;
            }
        }
        return true;
    }
}
