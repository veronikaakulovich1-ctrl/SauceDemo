package tests;

import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;

public class CheckoutCompleteTest extends BaseTest{

    @Test
    public void andToEndTestForCompleteOrder(){
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

    @Test
    public void checkTransitionByBackHomeButton(){
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
