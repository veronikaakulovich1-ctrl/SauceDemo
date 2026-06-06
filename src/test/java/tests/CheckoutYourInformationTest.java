package tests;

import io.qameta.allure.*;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import pages.CheckoutYourInformationPage;

import static org.testng.Assert.assertEquals;

public class CheckoutYourInformationTest extends BaseTest {

    @Test(
            description = "Проверка ввода валидных данных на странице Checkout Your Information странице",
            testName = "Проверка ввода валидных данных на странице Checkout Your Information странице",
            groups = "smoke"
    )
    @Owner("Akulovich")
    @Feature("Checkout Information")
    @Severity(SeverityLevel.CRITICAL)
    public void checkCheckoutYourInformationWithPositiveCred() {
        loginStep.auth(user, password);
        String title = productsPage.isPageOpened()
                .addToCart("Sauce Labs Backpack")
                .openCart()
                .checkout()
                .continueOrder()
                .getTitle();
        assertEquals(title, "Checkout: Overview", "Title wasn't found");
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
    @Severity(SeverityLevel.NORMAL)
    public void checkCheckoutYourInformationWithEmptyZipCode1(String firstName, String lastName, String zipCode, String errorMessage) {
        loginStep.auth(user, password);
        productsPage.isPageOpened()
                .addToCart("Sauce Labs Backpack")
                .openCart()
                .checkout();
        String actualError = new CheckoutYourInformationPage(driver)
                .continueOrderExpectingError(firstName, lastName, zipCode)
                .getErrorMessageForCheckoutInformationPage();
        assertEquals(actualError, errorMessage, "Something went wrong. Error wasn't found");
    }

    @Test(
            description = "Проверка перехода на странице Your Cart при клике на кнопку Cancel",
            testName = "Проверка перехода на странице Your Cart при клике на кнопку Cancel",
            groups = "regression"
    )
    @Owner("Akulovich")
    @Feature("Checkout Information")
    @Severity(SeverityLevel.NORMAL)
    public void checkTransitionFromCancelButton() {
        loginStep.auth(user, password);
        String title = productsPage.isPageOpened()
                .addToCart("Sauce Labs Backpack")
                .openCart()
                .checkout()
                .cancel()
                .getTitle();
        assertEquals(title, "Your Cart", "Something went wrong. Your Cart wasn't found");
    }
}
