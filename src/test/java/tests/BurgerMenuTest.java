package tests;

import io.qameta.allure.*;
import org.testng.annotations.Test;
import pages.LoginPage;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

public class BurgerMenuTest extends BaseTest {

    @Test(
            description = "Проверка logout для зарегистрированного пользователя",
            testName = "Проверка logout",
            groups = "regression"
    )
    @Owner("Akulovich")
    @Feature("Burger Menu")
    @Severity(SeverityLevel.NORMAL)
    public void checkLogout() {
        loginStep.auth(user, password);
        burgerMenuPage.isPageOpened().openMenu().logout();
        assertTrue(new LoginPage(driver).isPageOpened().getLoginButton().isDisplayed(),
                "Login button is not displayed after logout");
    }

    @Test(
            description = "Проверка перехода на страницу Product через clickAllItems",
            testName = "Проверка перехода на страницу Product через clickAllItems",
            groups = "regression"
    )
    @Owner("Akulovich")
    @Feature("Burger Menu")
    @Severity(SeverityLevel.NORMAL)
    public void checkTransitionToAllItemsMenu() {
        loginStep.auth(user, password);
        productsPage.isPageOpened().openCart();
        String title = burgerMenuPage.isPageOpened()
                .openMenu()
                .openAllItems()
                .getTitle();
        assertEquals(title, "Products", "Title wasn't found");
    }

    @Test(
            description = "Проверка перехода на About Page",
            testName = "Проверка перехода на About Page",
            groups = "regression"
    )
    @Owner("Akulovich")
    @Feature("Burger Menu")
    @Severity(SeverityLevel.NORMAL)
    public void checkTransitionToAboutPage() {
        loginStep.auth(user, password);
        productsPage.isPageOpened().openCart();
        burgerMenuPage.isPageOpened().openMenu().openAbout();
        assertTrue(driver.getCurrentUrl().contains("saucelabs.com"),
                "URL is not as expected after navigating to About page");
    }
}
