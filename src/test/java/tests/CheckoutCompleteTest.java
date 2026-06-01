package tests;

import io.qameta.allure.*;
import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;

public class CheckoutCompleteTest extends BaseTest {

    @Test(
            description = "End to End test для оформления заказа",
            testName = "End to End test для оформления заказа",
            groups = "smoke"
    )
    @Owner("Akulovich")
    @Feature("Checkout Complete")
    @Severity(SeverityLevel.CRITICAL)
    public void andToEndTestForCompleteOrder() {
        loginStep.auth("standard_user", "secret_sauce");
        String title = productsPage.isPageOpened()
                .addToCart("Sauce Labs Backpack")
                .addToCart("Sauce Labs Bolt T-Shirt")
                .openCart()
                .checkout()
                .continueOrder()
                .finishOrder()
                .getTitle();
        assertEquals(title, "Checkout: Complete!", "Something went wrong. Checkout: Complete! wasn't found");
    }

    @Test(
            description = "Проверка перехода по кнопке Back Home со страницы Checkout Completed",
            testName = "Проверка перехода по кнопке Back Home со страницы Checkout Completed",
            groups = "regression"
    )
    @Owner("Akulovich")
    @Feature("Checkout Complete")
    @Severity(SeverityLevel.NORMAL)
    public void checkTransitionByBackHomeButton() {
        loginStep.auth("standard_user", "secret_sauce");
        String title = productsPage.isPageOpened()
                .addToCart("Sauce Labs Backpack")
                .addToCart("Sauce Labs Bolt T-Shirt")
                .openCart()
                .checkout()
                .continueOrder()
                .finishOrder()
                .backToProducts()
                .getTitle();
        assertEquals(title, "Products", "Title wasn't found");
    }
}
