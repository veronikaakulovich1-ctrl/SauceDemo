package tests;

import org.testng.annotations.Test;

import java.util.Locale;

import static org.testng.Assert.assertEquals;

public class CheckoutCompleteTest extends BaseTest {

    @Test(
            description = "End to End test для оформления заказа",
            testName = "End to End test для оформления заказа",
            groups = "smoke"
    )
    public void andToEndTestForCompleteOrder() {
        loginPage.open();
        loginPage.login("standard_user", "secret_sauce");
        productsPage.addToCart("Sauce Labs Backpack");
        productsPage.addToCart("Sauce Labs Bolt T-Shirt");
        productsPage.clickCart();
        yourCartPage.clickToCheckoutButton();
        checkoutYourInformationPage.continueOrder("veronika", "akulovich", "123456");
        checkoutOverviewPage.clickFinishButton();
        assertEquals(checkoutCompletePage.getTitle(), "Checkout: Complete!", "Something went wrong. Checkout: Complete! wasn't found");
    }

    @Test(
            description = "Проверка перехода по кнопке Back Home со страницы Checkout Completed",
            testName = "Проверка перехода по кнопке Back Home со страницы Checkout Completed",
            groups = "regression"
    )
    public void checkTransitionByBackHomeButton() {
        loginPage.open();
        loginPage.login("standard_user", "secret_sauce");
        productsPage.addToCart("Sauce Labs Backpack");
        productsPage.addToCart("Sauce Labs Bolt T-Shirt");
        productsPage.clickCart();
        yourCartPage.clickToCheckoutButton();
        checkoutYourInformationPage.continueOrder("veronika", "akulovich", "123456");
        checkoutOverviewPage.clickFinishButton();
        checkoutCompletePage.backHomeButtonClick();
        assertEquals(productsPage.getTitle(), "Products", "Title wasn't found");
    }
}
