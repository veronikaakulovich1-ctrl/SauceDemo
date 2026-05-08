package tests;

import org.testng.Assert;
import org.testng.annotations.Test;

public class CheckOutOverviewTest extends BaseTest{

    @Test
    public void checkGetProductPriceSumWithTotalPrice() {
        loginPage.open();
        loginPage.login("standard_user", "secret_sauce");
        productsPage.addToCart("Sauce Labs Backpack");
        productsPage.addToCart("Sauce Labs Bolt T-Shirt");
        productsPage.clickCart();
        yourCartPage.clickToCheckoutButton();
        checkoutYourInformationPage.continueOrder("veronika", "akulovich", "123456");
        boolean checkIsEquals = checkoutOverviewPage.calculateTotalPriceOnCheckout().equals(checkoutOverviewPage.getSummarySubtotalPrice());
        Assert.assertTrue(checkIsEquals, "Something went wrong. Price doesn't match");
    }
}
