package pages;

import com.codeborne.selenide.ElementsCollection;

import static com.codeborne.selenide.Selenide.$$x;

public class SortedProductPage {
    public ElementsCollection
            voucherPrices = $$x("//div[@class='discounted-prices']//p[@class='deal-voucher-price'][1]");
}
