package pages;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

public class ProductPage {
    public SelenideElement
            wishlistButton = $$(".deal-box-wishlist").first(),
            voucherDiagram = $$(".voucher-limit").first(),
            sortingFilter = $("#sort"),
            firstItem = $$(".special-offer-title").first();
}
