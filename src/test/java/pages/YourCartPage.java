package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class YourCartPage extends BasePage {

    private final String REMOVE_BUTTON = "//button[text()='Remove'][1]";
    private final By CONTINUE_SHOPPING_BUTTON = By.xpath("//button[@name='continue-shopping']");
    private final By CHECKOUT_BUTTON = By.xpath("//button[@name='checkout']");
    private final By TITLE = By.cssSelector("[data-test = title]");
    private final By CART_ITEM_NAME = By.xpath("//div[@data-test='inventory-item-name']");
    private final By REMOVED_CART_ITEM = By.xpath("//div[@class='removed_cart_item']");

    public YourCartPage(WebDriver driver) {
        super(driver);
    }

    public String getTitle() {
        return driver.findElement(TITLE).getText();
    }

    public void clickToRemoveButton(String product) {
        driver.findElement(By.xpath(String.format(REMOVE_BUTTON, product))).click();
    }

    public void clickToContinueShoppingButton() {
        driver.findElement(CONTINUE_SHOPPING_BUTTON).click();
    }

    public void clickToCheckoutButton() {
        driver.findElement(CHECKOUT_BUTTON).click();
    }

    public String getAddedProductName() {
        return driver.findElement(CART_ITEM_NAME).getText();
    }

    public WebElement findRemovedCartItemElement() {
        return driver.findElement(REMOVED_CART_ITEM);
    }
}
