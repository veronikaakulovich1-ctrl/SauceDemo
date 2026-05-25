package tests;

import io.qameta.allure.*;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

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
    @Description("Scenario: Check product addition to the cart" +
            "Given: User is logged in" +
            "WHEN: Product was added" +
            "AND Your cart page is displayed" +
            "THEN: Added product is displayed on the cart")
    @Severity(SeverityLevel.CRITICAL)
    public void checkAddToCart() {
        SoftAssert softAssert = new SoftAssert();
        loginPage.open();
        loginPage.login("standard_user", "secret_sauce");
        productsPage.addToCart("Sauce Labs Backpack");
        productsPage.clickCart();
        softAssert.assertEquals(yourCartPage.getTitle(), "Your Cart", "Something went wrong. Your Cart wasn't found");
        String expectedProductName = "Sauce Labs Backpack";
        String actualProductName = yourCartPage.getAddedProductName();
        softAssert.assertEquals(actualProductName, expectedProductName, "The expected product was not found in the cart.");
        softAssert.assertAll();
    }

    @Test(
            description = "Проверка удаления товара из корзины",
            testName = "Проверка удаления товара из корзины",
            groups = "smoke"
    )
    @Owner("Akulovich")
    @Feature("Your Cart")
    @Description("Scenario: Check removing product from the cart" +
            "Given: User is logged in" +
            "AND Product was added to the cart" +
            "WHEN: Your cart page is displayed" +
            "AND Remove button next to product was tapped" +
            "THEN: Product was removed from the cart")
    @Severity(SeverityLevel.NORMAL)
    public void checkRemoveFromCart() {
        SoftAssert softAssert = new SoftAssert();
        loginPage.open();
        loginPage.login("standard_user", "secret_sauce");
        productsPage.addToCart("Sauce Labs Backpack");
        productsPage.clickCart();
        yourCartPage.clickToRemoveButton("Sauce Labs Backpack");
        WebElement removedItem = yourCartPage.findRemovedCartItemElement();
        softAssert.assertFalse(removedItem.isDisplayed(), "Product wasn't removed from the cart");
        softAssert.assertAll();
    }

    @Test(
            description = "Проверка перехода на страницу Product при клике на кнопку Continue Shopping",
            testName = "Проверка перехода на страницу Product при клике на кнопку Continue Shopping",
            groups = "regression"
    )
    @Owner("Akulovich")
    @Feature("Your Cart")
    @Description("Scenario: Check transition upon Continue Shopping Button" +
            "Given: User is logged in" +
            "AND Product was added to the cart" +
            "WHEN: Your cart page is displayed" +
            "AND Continue Shopping button was tapped" +
            "THEN: Products page is displayed")
    @Severity(SeverityLevel.NORMAL)
    public void continueShoppingButtonClick() {
        loginPage.open();
        loginPage.login("standard_user", "secret_sauce");
        productsPage.addToCart("Sauce Labs Backpack");
        productsPage.clickCart();
        yourCartPage.clickToContinueShoppingButton();
        assertEquals(productsPage.getTitle(), "Products", "Title wasn't found");
    }
}
