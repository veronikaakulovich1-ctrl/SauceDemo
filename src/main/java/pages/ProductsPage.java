package pages;

import io.qameta.allure.Step;
import lombok.extern.log4j.Log4j2;
import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;

@Log4j2
public class ProductsPage extends BasePage {

    private final By TITLE = By.cssSelector("[data-test=title]");
    private final By CART = By.xpath("//a[@class='shopping_cart_link']");
    private final String ADD_TO_CART_PATTERN =
            "//*[text()='%s']//ancestor::div[@class='inventory_item']//button[text()='Add to cart']";

    public ProductsPage(WebDriver driver) {
        super(driver);
    }

    @Override
    public ProductsPage isPageOpened() {
       try {
           wait.until(ExpectedConditions.visibilityOfElementLocated(TITLE));
       } catch (TimeoutException e) {
           log.error(e.getMessage());
           Assert.fail("Page isn't opened");
       }
        return this;
    }

    @Step("Получение тайтла на странице Product")
    public String getTitle() {
        log.info("Product Page title is displayed");
        return driver.findElement(TITLE).getText();
    }

    @Step("Добавление товара '{product}' в корзину")
    public ProductsPage addToCart(String product) {
        log.info("Product '{}' was added to the cart", product);
        driver.findElement(By.xpath(String.format(ADD_TO_CART_PATTERN, product))).click();
        return this;
    }

    @Step("Клик по кнопке Cart")
    public YourCartPage openCart() {
        log.info("Transition into Your Cart Page upon clicking cart");
        driver.findElement(CART).click();
        return new YourCartPage(driver).isPageOpened();
    }
}
