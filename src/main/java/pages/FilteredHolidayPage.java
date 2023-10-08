package pages;

import com.codeborne.selenide.ElementsCollection;

import static com.codeborne.selenide.Selenide.$$;

public class FilteredHolidayPage {
    public ElementsCollection
            discountedPrices = $$(".discounted-prices");
}
