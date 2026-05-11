package tests;

import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;

public class CheckoutYourInformationTest extends BaseTest{

    @Test
    public void checkCheckoutYourInformationWithPositiveCred() {
        loginPage.open();
        loginPage.login("standard_user", "secret_sauce");
        productsPage.addToCart("Sauce Labs Backpack");
        productsPage.clickCart();
        yourCartPage.clickToCheckoutButton();
        checkoutYourInformationPage.continueOrder("veronika", "akulovich", "123456");
        assertEquals(checkoutOverviewPage.getTitle(), "Checkout: Overview", "Title wasn't found");
    }

    @Test
    public void checkCheckoutYourInformationWithEmptyFirstName() {
        loginPage.open();
        loginPage.login("standard_user", "secret_sauce");
        productsPage.addToCart("Sauce Labs Backpack");
        productsPage.clickCart();
        yourCartPage.clickToCheckoutButton();
        checkoutYourInformationPage.continueOrder("", "akulovich", "123456");
        assertEquals(checkoutYourInformationPage.getErrorMessageForCheckoutInformationPage(), "Error: First Name is required", "Something went wrong. Error wasn't found");
    }

    @Test
    public void checkCheckoutYourInformationWithEmptyLastName() {
        loginPage.open();
        loginPage.login("standard_user", "secret_sauce");
        productsPage.addToCart("Sauce Labs Backpack");
        productsPage.clickCart();
        yourCartPage.clickToCheckoutButton();
        checkoutYourInformationPage.continueOrder("veronika", "", "123456");
        assertEquals(checkoutYourInformationPage.getErrorMessageForCheckoutInformationPage(), "Error: Last Name is required", "Something went wrong. Error wasn't found");
    }

    @Test
    public void checkCheckoutYourInformationWithEmptyZipCode() {
        loginPage.open();
        loginPage.login("standard_user", "secret_sauce");
        productsPage.addToCart("Sauce Labs Backpack");
        productsPage.clickCart();
        yourCartPage.clickToCheckoutButton();
        checkoutYourInformationPage.continueOrder("veronika", "akulovich", "");
        assertEquals(checkoutYourInformationPage.getErrorMessageForCheckoutInformationPage(), "Error: Postal Code is required", "Something went wrong. Error wasn't found");
    }

    @Test
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
