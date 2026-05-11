package tests;

import org.testng.Assert;
import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

public class BurgerMenuTest extends BaseTest {

    @Test
    public void checkLogout() {
        loginPage.open();
        loginPage.login("standard_user", "secret_sauce");
        burgerMenuPage.clickBurgerMenuButton();
        burgerMenuPage.clickLogoutMenuButton();
        boolean isLoginButtonDisplayed = loginPage.getLoginButton().isDisplayed();
        assertTrue(isLoginButtonDisplayed, "Login button is not displayed after logout");
    }

    @Test
    public void checkTransitionToAllItemsMenu() {
        loginPage.open();
        loginPage.login("standard_user", "secret_sauce");
        productsPage.clickCart();
        burgerMenuPage.clickBurgerMenuButton();
        burgerMenuPage.clickAllItemsMenuButton();
        assertEquals(productsPage.getTitle(), "Products", "Title wasn't found");
    }

    @Test
    public void checkTransitionToAboutPage() {
        loginPage.open();
        loginPage.login("standard_user", "secret_sauce");
        productsPage.clickCart();
        burgerMenuPage.clickBurgerMenuButton();
        burgerMenuPage.clickAboutItemMenuButton();
        assertEquals(driver.getCurrentUrl(), "https://saucelabs.com/", "URL is not as expected after navigating to About page");
    }
}
