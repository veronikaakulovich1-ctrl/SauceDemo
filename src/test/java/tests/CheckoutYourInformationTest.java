package tests;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;

public class CheckoutYourInformationTest extends BaseTest {

    @Test(
            description = "Проверка ввода валидных данных на странице Checkout Your Information странице",
            testName = "Проверка ввода валидных данных на странице Checkout Your Information странице",
            groups = "smoke"
    )
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
