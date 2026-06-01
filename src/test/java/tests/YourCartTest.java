package tests;

import io.qameta.allure.*;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import pages.YourCartPage;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertFalse;

public class YourCartTest extends BaseTest {

    @Test(
            description = "Проверка добавления товара в корзину и отображение на странице You Cart",
            testName = "Проверка добавления товара в корзину и отображение на странице You Cart",
            groups = "smoke"
    )
    @Owner("Akulovich")
    @Feature("Your Cart")
    @Severity(SeverityLevel.CRITICAL)
    public void checkAddToCart() {
        SoftAssert softAssert = new SoftAssert();
        loginStep.auth("standard_user", "secret_sauce");
        YourCartPage cart = productsPage.isPageOpened()
                .addToCart("Sauce Labs Backpack")
                .openCart();
        softAssert.assertEquals(cart.getTitle(), "Your Cart",
                "Something went wrong. Your Cart wasn't found");
        softAssert.assertEquals(cart.getAddedProductName(), "Sauce Labs Backpack",
                "The expected product was not found in the cart.");
        softAssert.assertAll();
    }

    @Test(
            description = "Проверка удаления товара из корзины",
            testName = "Проверка удаления товара из корзины",
            groups = "smoke"
    )
    @Owner("Akulovich")
    @Feature("Your Cart")
    @Severity(SeverityLevel.NORMAL)
    public void checkRemoveFromCart() {
        loginStep.auth("standard_user", "secret_sauce");
        productsPage.isPageOpened()
                .addToCart("Sauce Labs Backpack")
                .openCart()
                .removeProduct("Sauce Labs Backpack");
        assertFalse(yourCartPage.findRemovedCartItemElement().isDisplayed(),
                "Product wasn't removed from the cart");
    }

    @Test(
            description = "Проверка перехода на страницу Product при клике на кнопку Continue Shopping",
            testName = "Проверка перехода на страницу Product при клике на кнопку Continue Shopping",
            groups = "regression"
    )
    @Owner("Akulovich")
    @Feature("Your Cart")
    @Severity(SeverityLevel.NORMAL)
    public void continueShoppingButtonClick() {
        loginStep.auth("standard_user", "secret_sauce");
        String title = productsPage.isPageOpened()
                .addToCart("Sauce Labs Backpack")
                .openCart()
                .continueShopping()
                .getTitle();
        assertEquals(title, "Products", "Title wasn't found");
    }
}
