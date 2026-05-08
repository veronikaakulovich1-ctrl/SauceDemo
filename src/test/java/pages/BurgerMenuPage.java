package pages;

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

    public void clickBurgerMenuButton() {
        driver.findElement(BURGER_MENU_BUTTON).click();
    }

    public void clickAllItemsMenuButton() {
        driver.findElement(ALL_ITEMS_MENU).click();
    }

    public void clickAboutItemMenuButton() {
        driver.findElement(ABOUT_ITEM_MENU).click();
    }

    public void clickLogoutMenuButton() {
        driver.findElement(LOGOUT_MENU).click();
    }

    public void clickResetAppStateMenuButton() {
        driver.findElement(RESET_APP_STATE_MENU).click();
    }
}
