package pages;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class BurgerMenuPage extends BasePage {

    public BurgerMenuPage(WebDriver driver) {
        super(driver);
    }

    private final By BURGER_MENU_BUTTON = By.xpath("//button[@id='react-burger-menu-btn']");
    private final By ALL_ITEMS_MENU = By.xpath("//a[@id='inventory_sidebar_link']");
    private final By ABOUT_ITEM_MENU = By.xpath("//a[@id='about_sidebar_link']");
    private final By LOGOUT_MENU = By.xpath("//a[@id='logout_sidebar_link']");
    private final By RESET_APP_STATE_MENU = By.xpath("//a[@id='reset_sidebar_link']");

    @Step("Открытие бургер-меню")
    public void clickBurgerMenuButton() {
        driver.findElement(BURGER_MENU_BUTTON).click();
    }

    @Step("Клик по кнопке All Items")
    public void clickAllItemsMenuButton() {
        driver.findElement(ALL_ITEMS_MENU).click();
    }

    @Step("Клик по кнопке About Item")
    public void clickAboutItemMenuButton() {
        driver.findElement(ABOUT_ITEM_MENU).click();
    }

    @Step("Клик по кнопке Logout")
    public void clickLogoutMenuButton() {
        driver.findElement(LOGOUT_MENU).click();
    }

    public void clickResetAppStateMenuButton() {
        driver.findElement(RESET_APP_STATE_MENU).click();
    }
}
