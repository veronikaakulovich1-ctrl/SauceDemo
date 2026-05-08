package tests;

import org.testng.Assert;
import org.testng.annotations.Test;

public class BurgerMenuTest extends BaseTest {

    @Test
    public void checkLogout() {
        loginPage.open();
        loginPage.login("standard_user", "secret_sauce");
        burgerMenuPage.clickBurgerMenuButton();
        burgerMenuPage.clickLogoutMenuButton();
        boolean isLoginButtonDisplayed = loginPage.getLoginButton().isDisplayed();
        Assert.assertTrue(isLoginButtonDisplayed, "Login button is not displayed after logout");
    }

    @Test
    public void checkTransitionToAllItemsMenu() {
        loginPage.open();
        loginPage.login("standard_user", "secret_sauce");
        productsPage.clickCart();
        burgerMenuPage.clickBurgerMenuButton();
        burgerMenuPage.clickAllItemsMenuButton();
        Assert.assertEquals(productsPage.getTitle(), "Products", "Title wasn't found");
    }

    @Test
    public void checkTransitionToAboutPage() {
        loginPage.open();
        loginPage.login("standard_user", "secret_sauce");
        productsPage.clickCart();
        burgerMenuPage.clickBurgerMenuButton();
        burgerMenuPage.clickAboutItemMenuButton();
        Assert.assertEquals(driver.getCurrentUrl(), "https://saucelabs.com/", "URL is not as expected after navigating to About page");
    }
}
