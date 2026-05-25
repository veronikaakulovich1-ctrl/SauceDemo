package tests;

import io.qameta.allure.*;
import org.testng.annotations.Test;

import java.util.Locale;

import static org.testng.Assert.assertEquals;

public class CheckoutCompleteTest extends BaseTest {

    @Test(
            description = "End to End test для оформления заказа",
            testName = "End to End test для оформления заказа",
            groups = "smoke"
    )
    @Owner("Akulovich")
    @Feature("Checkout Complete")
    @Description("Scenario: End to end Test for complete order" +
            "Given: Products were added to the cart" +
            "AND User's information was inputted" +
            "AND Checkout Overview page is displayed" +
            "WHEN:  Finish button was tapped" +
            "THEN: Order was completed" +
            "AND Checkout Complete page is displayed")
    @Severity(SeverityLevel.CRITICAL)
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
    @Owner("Akulovich")
    @Feature("Checkout Complete")
    @Description("Scenario: Transition upon Back Home Button" +
            "Given: CHeckout Complete page is displayed" +
            "WHEN:  Back Home button was tapped" +
            "THEN: Products page is displayed")
    @Severity(SeverityLevel.NORMAL)
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
