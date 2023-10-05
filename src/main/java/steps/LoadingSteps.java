package steps;

import com.codeborne.selenide.Condition;
import io.qameta.allure.Step;
import pages.LoadingPage;

public class LoadingSteps {

    private LoadingPage loadingPage = new LoadingPage();

    @Step("Wait for search to finish")
    public LoadingSteps waitForSearchToFinish() {
        loadingPage.loadElement.should(Condition.hidden);
        return this;
    }
}
