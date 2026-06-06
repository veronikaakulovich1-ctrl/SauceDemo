package pages;

import io.qameta.allure.Step;
import lombok.extern.log4j.Log4j2;
import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;

@Log4j2
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
       try {
           wait.until(ExpectedConditions.visibilityOfElementLocated(BURGER_MENU_BUTTON));
       }catch (TimeoutException e) {
           log.error(e.getMessage());
           Assert.fail("Page isn't opened");
       }
        return this;
    }

    @Step("Открытие бургер-меню")
    public BurgerMenuPage openMenu() {
        log.info("Burger-nemu is opened");
        driver.findElement(BURGER_MENU_BUTTON).click();
        return this;
    }

    @Step("Клик по кнопке All Items")
    public ProductsPage openAllItems() {
        log.info("Product page is opened after transition into All items");
        driver.findElement(ALL_ITEMS_MENU).click();
        return new ProductsPage(driver).isPageOpened();
    }

    @Step("Клик по кнопке About Item")
    public SauceLabsPage openAbout() {
        log.info("Page SauceLabs.com is opened after transition upon About Item");
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
        log.info("User log out after clicking log out button");
        driver.findElement(LOGOUT_MENU).click();
        return new LoginPage(driver).isPageOpened();
    }

    public void clickResetAppStateMenuButton() {
        driver.findElement(RESET_APP_STATE_MENU).click();
    }
}
