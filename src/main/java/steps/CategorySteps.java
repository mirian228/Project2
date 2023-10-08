package steps;

import com.codeborne.selenide.ClickOptions;
import io.qameta.allure.Step;
import pages.CategoryPage;

public class CategorySteps {
    private CategoryPage categoryPage = new CategoryPage();

    @Step("Hover on food category")
    public CategorySteps hoverOnFoodCategory() {
        categoryPage.foodCategory.hover();
        return this;
    }

    @Step("Click on sushi category")
    public CategorySteps clickOnSushiCategory() {
        categoryPage.sushiButton.click(ClickOptions.usingJavaScript());
        return this;
    }
}
