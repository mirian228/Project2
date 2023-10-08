package pages;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selenide.$;

public class HolidayPage {
    private SelenideElement
            categoryFilter = $(".category-filter-desk");
    public SelenideElement

            minPrice = categoryFilter.$("#minprice"),
            maxPrice = categoryFilter.$("#maxprice"),
            submitButton = categoryFilter.$(".submit-button");

}
