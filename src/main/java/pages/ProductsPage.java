package pages;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;

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
        wait.until(ExpectedConditions.visibilityOfElementLocated(TITLE));
        return this;
    }

    @Step("Получение тайтла на странице Product")
    public String getTitle() {
        return driver.findElement(TITLE).getText();
    }

    @Step("Добавление товара '{product}' в корзину")
    public ProductsPage addToCart(String product) {
        driver.findElement(By.xpath(String.format(ADD_TO_CART_PATTERN, product))).click();
        return this;
    }

    @Step("Клик по кнопке Cart")
    public YourCartPage openCart() {
        driver.findElement(CART).click();
        return new YourCartPage(driver).isPageOpened();
    }
}
