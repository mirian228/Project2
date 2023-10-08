import com.codeborne.selenide.testng.ScreenShooter;
import config.ConfigTests;
import data.UserData;
import data.dao.jdbc.UserDaoImpl;
import data.model.User;
import io.qameta.allure.*;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import steps.*;
import util.CommonData;
import util.TestListener;

import java.sql.SQLException;

import static com.codeborne.selenide.Selenide.open;

@Epic("Regression Tests")
@Listeners({ScreenShooter.class, TestListener.class})
public class SwoopFeatureTest extends ConfigTests {
    private HomeSteps homeSteps;
    private HolidaySteps holidaySteps;
    private FilteredHolidaySteps filteredHolidaySteps;
    private CategorySteps categorySteps;
    private LoadingSteps loadingSteps;
    private ProductSteps productSteps;
    private SortedProductSteps sortedProductSteps;
    private ProductDetailSteps productDetailSteps;
    private ShareWindowSteps shareWindowSteps;
    private LoginSteps loginSteps;
    private RegistrationSteps registrationSteps;
    private UserDaoImpl userDao;
    private SoftAssert softAssert;

    @BeforeMethod(description = "Initialize all required step classes", groups = {"Regression1", "Regression2"})
    public void initializeSteps() {
        homeSteps = new HomeSteps();
        holidaySteps = new HolidaySteps();
        filteredHolidaySteps = new FilteredHolidaySteps();
        categorySteps = new CategorySteps();
        loadingSteps = new LoadingSteps();
        productSteps = new ProductSteps();
        sortedProductSteps = new SortedProductSteps();
        productDetailSteps = new ProductDetailSteps();
        shareWindowSteps = new ShareWindowSteps();
        loginSteps = new LoginSteps();
        registrationSteps = new RegistrationSteps();
        userDao = new UserDaoImpl();
        softAssert = new SoftAssert();

    }


    @Test(description = "Filter out holiday results by min and max price scenario", groups = {"Regression1"})
    @Feature("Filtering by price feature")
    @Story("Valid holiday search filter scenario")
    @Description("Holiday search filter scenario, verify price range correctness after filter")
    @Severity(SeverityLevel.NORMAL)
    public void holidayTest() {
        open(CommonData.SWOOP_URL);
        homeSteps.acceptCookie()
                .clickOnHolidayButton();
        holidaySteps.setMinPriceRange(CommonData.MIN_PRICE_RANGE)
                .setMaximumPriceRange(CommonData.MAX_PRICE_RANGE)
                .clickOnSubmit();
        loadingSteps.waitForSearchToFinish();
        Assert.assertTrue(filteredHolidaySteps.checkSelectedPriceRange(CommonData.MIN_PRICE_RANGE, CommonData.MAX_PRICE_RANGE));
    }

    @Test(description = "Add item to favorites list scenario", groups = {"Regression1"})
    @Feature("Add to wishlist feature")
    @Story("Valid add item to favorites list")
    @Description("Valid add item to favorites list scenario, verify login window and voucher status")
    @Severity(SeverityLevel.NORMAL)
    public void categoryVoucherTest() {
        open(CommonData.SWOOP_URL);
        homeSteps
                .acceptCookie()
                .clickOnCategoriesButton();
        categorySteps.hoverOnFoodCategory()
                .clickOnSushiCategory();
        productSteps.clickOnFirstAddToWishlistButton();
        softAssert.assertTrue(loginSteps.checkIfLoginWindowAppeared());
        softAssert.assertTrue(productSteps.checkVoucherStatus());
        softAssert.assertAll();
    }

    @Test(description = "Category sorting scenario", groups = {"Regression2"})
    @Feature("Product sorting feature")
    @Story("Valid descending order filter scenario")
    @Description("Apply descending order to items, compare first two item price")
    @Severity(SeverityLevel.NORMAL)
    public void categorySortingTest() {
        open(CommonData.SWOOP_URL);
        homeSteps
                .acceptCookie()
                .clickOnCategoriesButton();
        categorySteps.hoverOnFoodCategory()
                .clickOnSushiCategory();
        productSteps.clickSortElementDropDown()
                .applyDescendingOrderFilter();
        loadingSteps.waitForSearchToFinish();
        Assert.assertTrue(sortedProductSteps.checkDescendingOrderCorrectness());
    }

    @Test(description = "Share product scenario", groups = {"Regression2"})
    @Feature("Product sharing feature")
    @Story("Valid product share scenario")
    @Description("Share product scenario, validate window")
    @Severity(SeverityLevel.MINOR)
    public void categoryShareTest() {
        open(CommonData.SWOOP_URL);
        homeSteps
                .acceptCookie()
                .clickOnCategoriesButton();
        categorySteps.hoverOnFoodCategory()
                .clickOnSushiCategory();
        productSteps.navigateToFirstItem();
        productDetailSteps
                .clickOnShareButton()
                .switchToPopupWindow();
        Assert.assertTrue(shareWindowSteps.checkFacebookWindow());
    }

    @Test(description = "Registration without specifying gender scenario", groups = {"Regression2"})
    @Feature("User Registration feature")
    @Story("Registration scenario with valid data except gender is not filled scenario")
    @Description("Registration scenario without providing gender with other valid information, validate warning message")
    @Severity(SeverityLevel.NORMAL)
    public void registrationTest() throws SQLException {
        User user = new User(UserData.firstName, UserData.lastName, UserData.phone, UserData.email, UserData.dateOfBirth, UserData.password);
        int generatedId = userDao.insertUser(user);
        User userFromDb = userDao.selectEntityByIdWithSpecificDateFormat(generatedId);
        open(CommonData.SWOOP_URL);
        homeSteps
                .acceptCookie()
                .clickOnLoginButton();
        loginSteps.clickOnRegistration();
        registrationSteps
                .fillFirstName(userFromDb.getFirstName())
                .fillLastName(userFromDb.getLastName())
                .fillEmail(userFromDb.getEmail())
                .fillPhoneNumber(userFromDb.getPhone())
                .fillDate(userFromDb.getDateOfBirth())
                .fillPassword(userFromDb.getPassword())
                .fillPasswordConfirm(userFromDb.getPassword())
                .clickOnRegistrationButton();
        Assert.assertEquals(registrationSteps.getWarningMessage(), CommonData.GENDER_WARNING_TEXT);
        userDao.deleteEntityById(generatedId);
    }
}
