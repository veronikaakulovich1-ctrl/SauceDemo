package tests;

import io.qameta.allure.*;
import org.testng.annotations.Test;
import pages.CheckoutOverviewPage;

import static org.testng.Assert.assertTrue;

public class CheckOutOverviewTest extends BaseTest {

    @Test(
            description = "Проверка цены всех продуктов и total price на Checkout Overview странице",
            testName = "Проверка цены всех продуктов и total price на Checkout Overview странице",
            groups = "smoke"
    )
    @Owner("Akulovich")
    @Feature("Checkout Overview")
    @Severity(SeverityLevel.CRITICAL)
    public void checkGetProductPriceSumWithTotalPrice() {
        loginStep.auth(user, password);
        CheckoutOverviewPage overview = productsPage.isPageOpened()
                .addToCart("Sauce Labs Backpack")
                .addToCart("Sauce Labs Bolt T-Shirt")
                .openCart()
                .checkout()
                .continueOrder();
        assertTrue(overview.calculateTotalPriceOnCheckout().equals(overview.getSummarySubtotalPrice()),
                "Something went wrong. Price doesn't match");
    }
}
