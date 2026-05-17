package tests;

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
    public void continueShoppingButtonClick() {
        loginPage.open();
        loginPage.login("standard_user", "secret_sauce");
        productsPage.addToCart("Sauce Labs Backpack");
        productsPage.clickCart();
        yourCartPage.clickToContinueShoppingButton();
        assertEquals(productsPage.getTitle(), "Products", "Title wasn't found");
    }
}
