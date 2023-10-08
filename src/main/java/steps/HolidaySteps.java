package steps;

import com.codeborne.selenide.ClickOptions;
import io.qameta.allure.Step;
import pages.HolidayPage;

public class HolidaySteps {
    private HolidayPage holidayPage = new HolidayPage();

    @Step("Set minimum price range to {0}")
    public HolidaySteps setMinPriceRange(String minPriceRange) {
        holidayPage.minPrice.setValue(minPriceRange);
        return this;
    }

    @Step("Set maximum price range to {0}")
    public HolidaySteps setMaximumPriceRange(String maximumPriceRange) {
        holidayPage.maxPrice.setValue(maximumPriceRange);
        return this;
    }

    @Step("Click on submit button")
    public HolidaySteps clickOnSubmit() {
        holidayPage.submitButton.click(ClickOptions.usingJavaScript());
        return this;
    }

}
