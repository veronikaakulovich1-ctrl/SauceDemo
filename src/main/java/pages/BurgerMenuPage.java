package pages;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class BurgerMenuPage extends BasePage {

    private final By BURGER_MENU_BUTTON = By.xpath("//button[@id='react-burger-menu-btn']");
    private final By ALL_ITEMS_MENU = By.xpath("//a[@id='inventory_sidebar_link']");
    private final By ABOUT_ITEM_MENU = By.xpath("//a[@id='about_sidebar_link']");
    private final By LOGOUT_MENU = By.xpath("//a[@id='logout_sidebar_link']");
    private final By RESET_APP_STATE_MENU = By.xpath("//a[@id='reset_sidebar_link']");

    public BurgerMenuPage(WebDriver driver) {
        super(driver);
    }

    @Override
    public BurgerMenuPage isPageOpened() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(BURGER_MENU_BUTTON));
        return this;
    }

    @Step("Открытие бургер-меню")
    public BurgerMenuPage openMenu() {
        driver.findElement(BURGER_MENU_BUTTON).click();
        return this;
    }

    @Step("Клик по кнопке All Items")
    public ProductsPage openAllItems() {
        driver.findElement(ALL_ITEMS_MENU).click();
        return new ProductsPage(driver).isPageOpened();
    }

    @Step("Клик по кнопке About Item")
    public SauceLabsPage openAbout() {
        String originalWindow = driver.getWindowHandle();
        WebElement aboutLink = wait.until(ExpectedConditions.elementToBeClickable(ABOUT_ITEM_MENU));
        String aboutUrl = aboutLink.getAttribute("href");
        aboutLink.click();

        try {
            wait.until(d -> d.getWindowHandles().size() > 1
                    || d.getCurrentUrl().contains("saucelabs.com"));
        } catch (TimeoutException e) {
            driver.get(aboutUrl);
        }

        if (driver.getWindowHandles().size() > 1) {
            driver.getWindowHandles().stream()
                    .filter(handle -> !handle.equals(originalWindow))
                    .findFirst()
                    .ifPresent(handle -> driver.switchTo().window(handle));
        }
        return new SauceLabsPage(driver).isPageOpened();
    }

    @Step("Клик по кнопке Logout")
    public LoginPage logout() {
        driver.findElement(LOGOUT_MENU).click();
        return new LoginPage(driver).isPageOpened();
    }

    public void clickResetAppStateMenuButton() {
        driver.findElement(RESET_APP_STATE_MENU).click();
    }
}
