package pages;

import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.By;
import data.CommonData;

import static com.codeborne.selenide.Selenide.$;

public class CategoryPage {
    public SelenideElement
            foodCategory = $(By.linkText(CommonData.FOOD_CATEGORY_TEXT)),
            sushiButton = $(By.linkText(CommonData.SUSHI_CATEGORY_TEXT));
}
