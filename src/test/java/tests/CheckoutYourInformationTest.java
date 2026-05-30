package tests;

import io.qameta.allure.*;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;

public class CheckoutYourInformationTest extends BaseTest {

    @Test(
            description = "Проверка ввода валидных данных на странице Checkout Your Information странице",
            testName = "Проверка ввода валидных данных на странице Checkout Your Information странице",
            groups = "smoke"
    )
    @Owner("Akulovich")
    @Feature("Checkout Information")
    @Description("Scenario: Check checkout information form with positive credentials" +
            "Given: User is logged in" +
            "AND Product was added to the cart" +
            "AND Checkout button was tapped" +
            "AND Checkout information page is displayed" +
            "WHEN: Valid firstname is inputted" +
            "AND Valid lastname is inputted" +
            "AND Valid zipcode is inputted" +
            "AND Continue Order button was tapped" +
            "THEN: Checkout Overview page is displayed")
    @Severity(SeverityLevel.CRITICAL)
    public void checkCheckoutYourInformationWithPositiveCred() {
        loginPage.open();
        loginPage.login("standard_user", "secret_sauce");
        productsPage.addToCart("Sauce Labs Backpack");
        productsPage.clickCart();
        yourCartPage.clickToCheckoutButton();
        checkoutYourInformationPage.continueOrder("veronika", "akulovich", "123456");
        assertEquals(checkoutOverviewPage.getTitle(), "Checkout: Overview", "Title wasn't found");
    }

    @DataProvider(name = "Параметризированный тест для негативных сценариев на странице Checkout Your Information")
    public Object[][] CheckYourInformationData() {
        return new Object[][]{
                {"", "akulovich", "123456", "Error: First Name is required"},
                {"veronika", "", "123456", "Error: Last Name is required"},
                {"veronika", "akulovich", "", "Error: Postal Code is required"}
        };
    }

    @Test(
            dataProvider = "Параметризированный тест для негативных сценариев на странице Checkout Your Information",
            description = "Негативные сценарии для страницу Checkout Your Information",
            testName = "Негативные сценарии для страницу Checkout Your Information",
            groups = "regression"
    )
    @Owner("Akulovich")
    @Feature("Checkout Information")
    @Description("Scenario Outline: Check checkout information form with positive credentials" +
            "Given: User is logged in" +
            "AND Product was added to the cart" +
            "AND Checkout button was tapped" +
            "AND Checkout information page is displayed" +
            "WHEN:  <firstname> is inputted" +
            "AND <lastname> is inputted" +
            "AND  <zipcode> is inputted" +
            "AND Continue Order button was tapped" +
            "THEN: <ErrorMessage> is displayed")
    @Severity(SeverityLevel.NORMAL)
    public void checkCheckoutYourInformationWithEmptyZipCode1(String firstName, String lastName, String zipCode, String errorMessage) {
        loginPage.open();
        loginPage.login("standard_user", "secret_sauce");
        productsPage.addToCart("Sauce Labs Backpack");
        productsPage.clickCart();
        yourCartPage.clickToCheckoutButton();
        checkoutYourInformationPage.continueOrder(firstName, lastName, zipCode);
        assertEquals(checkoutYourInformationPage.getErrorMessageForCheckoutInformationPage(), errorMessage, "Something went wrong. Error wasn't found");
    }

    @Test(
            description = "Проверка перехода на странице Your Cart при клике на кнопку Cancel",
            testName = "Проверка перехода на странице Your Cart при клике на кнопку Cancel",
            groups = "regression"
    )
    @Owner("Akulovich")
    @Feature("Checkout Information")
    @Description("Scenario: Check transition upon Cancel Button" +
            "Given: Checkout Your information page is displayed" +
            "AND Cancel button is displayed" +
            "WHEN:  Cancel button was tapped" +
            "THEN: Your Cart page is displayed")
    @Severity(SeverityLevel.NORMAL)
    public void checkTransitionFromCancelButton() {
        loginPage.open();
        loginPage.login("standard_user", "secret_sauce");
        productsPage.addToCart("Sauce Labs Backpack");
        productsPage.clickCart();
        yourCartPage.clickToCheckoutButton();
        checkoutYourInformationPage.clickCancelButton();
        assertEquals(yourCartPage.getTitle(), "Your Cart", "Something went wrong. Your Cart wasn't found");
    }
}
