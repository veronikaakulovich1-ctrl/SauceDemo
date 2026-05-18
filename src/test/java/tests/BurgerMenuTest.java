package tests;

import org.testng.Assert;
import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

public class BurgerMenuTest extends BaseTest {

    @Test(
            description = "Проверка logout для зарегистрированного пользователя",
            testName = "Проверка logout",
            groups = "regression"
    )
    public void checkLogout() {
        loginPage.open();
        loginPage.login("standard_user", "secret_sauce");
        burgerMenuPage.clickBurgerMenuButton();
        burgerMenuPage.clickLogoutMenuButton();
        boolean isLoginButtonDisplayed = loginPage.getLoginButton().isDisplayed();
        assertTrue(isLoginButtonDisplayed, "Login button is not displayed after logout");
    }

    @Test(
            description = "Проверка перехода на страницу Product через clickAllItems",
            testName = "Проверка перехода на страницу Product через clickAllItems",
            groups = "regression"
    )
    public void checkTransitionToAllItemsMenu() {
        loginPage.open();
        loginPage.login("standard_user", "secret_sauce");
        productsPage.clickCart();
        burgerMenuPage.clickBurgerMenuButton();
        burgerMenuPage.clickAllItemsMenuButton();
        assertEquals(productsPage.getTitle(), "Products", "Title wasn't found");
    }

    @Test(
            description = "Проверка перехода на About Page",
            testName = "Проверка перехода на About Page",
            groups = "regression"
    )
    public void checkTransitionToAboutPage() {
        loginPage.open();
        loginPage.login("standard_user", "secret_sauce");
        productsPage.clickCart();
        burgerMenuPage.clickBurgerMenuButton();
        burgerMenuPage.clickAboutItemMenuButton();
        assertEquals(driver.getCurrentUrl(), "https://saucelabs.com/", "URL is not as expected after navigating to About page");
    }
}
