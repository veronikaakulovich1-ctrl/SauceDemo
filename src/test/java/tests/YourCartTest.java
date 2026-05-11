package tests;

import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertFalse;

public class YourCartTest extends BaseTest {

    @Test
    public void checkAddToCart() {
        SoftAssert softAssert = new SoftAssert();
        loginPage.open();
        loginPage.login("standard_user", "secret_sauce");
        productsPage.addToCart("Sauce Labs Backpack");
        productsPage.clickCart();
        assertEquals(yourCartPage.getTitle(), "Your Cart", "Something went wrong. Your Cart wasn't found");
        String expectedProductName = "Sauce Labs Backpack";
        String actualProductName = yourCartPage.getAddedProductName();
        assertEquals(actualProductName, expectedProductName, "The expected product was not found in the cart.");
        softAssert.assertAll();
    }

    @Test
    public void checkRemoveFromCart(){
        SoftAssert softAssert = new SoftAssert();
        loginPage.open();
        loginPage.login("standard_user", "secret_sauce");
        productsPage.addToCart("Sauce Labs Backpack");
        productsPage.clickCart();
        yourCartPage.clickToRemoveButton("Sauce Labs Backpack");
        WebElement removedItem = yourCartPage.findRemovedCartItemElement();
        assertFalse(removedItem.isDisplayed(), "Product wasn't removed from the cart");
        softAssert.assertAll();
    }

    @Test
    public void continueShoppingButtonClick() {
        SoftAssert softAssert = new SoftAssert();
        loginPage.open();
        loginPage.login("standard_user", "secret_sauce");
        productsPage.addToCart("Sauce Labs Backpack");
        productsPage.clickCart();
        yourCartPage.clickToContinueShoppingButton();
        assertEquals(productsPage.getTitle(), "Products", "Title wasn't found");
    }
}
