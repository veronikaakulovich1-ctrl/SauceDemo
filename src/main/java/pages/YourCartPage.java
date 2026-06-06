package pages;

import io.qameta.allure.Step;
import lombok.extern.log4j.Log4j2;
import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;

import java.util.ArrayList;
import java.util.List;

@Log4j2
public class YourCartPage extends BasePage {

    private final String REMOVE_BUTTON =
            "//div[text()='%s']/ancestor::div[@class='cart_item']//button[text()='Remove']";
    private final By CONTINUE_SHOPPING_BUTTON = By.xpath("//button[@name='continue-shopping']");
    private final By CHECKOUT_BUTTON = By.xpath("//button[@name='checkout']");
    private final By TITLE = By.cssSelector("[data-test=title]");
    private final By CART_ITEM_NAME = By.xpath("//div[@data-test='inventory-item-name']");
    private final By REMOVED_CART_ITEM = By.xpath("//div[@class='removed_cart_item']");

    public YourCartPage(WebDriver driver) {
        super(driver);
    }

    @Override
    public YourCartPage isPageOpened() {
       try {
           wait.until(ExpectedConditions.visibilityOfElementLocated(TITLE));
       } catch (TimeoutException e) {
           log.error(e.getMessage());
           Assert.fail("Page isn't opened");
       }
        return this;
    }

    @Step("Получение тайтла на странице Your cart")
    public String getTitle() {
        log.info("Your cart title is displayed");
        return driver.findElement(TITLE).getText();
    }

    @Step("Удаление товара '{product}' из корзины")
    public YourCartPage removeProduct(String product) {
        log.info("Product '{}' was removed from cart", product);
        driver.findElement(By.xpath(String.format(REMOVE_BUTTON, product))).click();
        return this;
    }

    @Step("Клик по кнопке Continue Shopping на странице корзины")
    public ProductsPage continueShopping() {
        log.info("Product pagw is opened upon Continue shopping button");
        driver.findElement(CONTINUE_SHOPPING_BUTTON).click();
        return new ProductsPage(driver).isPageOpened();
    }

    @Step("Клик по кнопке Checkout на странице корзины")
    public CheckoutYourInformationPage checkout() {
        log.info("Checkout information page is displayed upon clicking Checkout button");
        driver.findElement(CHECKOUT_BUTTON).click();
        return new CheckoutYourInformationPage(driver).isPageOpened();
    }

    @Step("Получение информации о добавленном товаре в корзину")
    public String getAddedProductName() {
        log.info("Get added Product name to the cart");
        return driver.findElement(CART_ITEM_NAME).getText();
    }

    @Step("Получение информации об удаленных товарах из корзины")
    public WebElement findRemovedCartItemElement() {
        return driver.findElement(REMOVED_CART_ITEM);
    }

    public String getProductNameFromCart(int index) {
        return driver.findElements(By.cssSelector(".inventory_item_name"))
                .get(index)
                .getText();
    }

    public ArrayList<String> getProductName() {
        List<WebElement> allProductsElements = driver.findElements(By.cssSelector(".inventory_item_name"));
        ArrayList<String> names = new ArrayList<>();
        for (WebElement product : allProductsElements) {
            names.add(product.getText());
        }
        return names;
    }
}
