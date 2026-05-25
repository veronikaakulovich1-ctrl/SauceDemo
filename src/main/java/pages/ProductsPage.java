package pages;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class ProductsPage extends BasePage {

    private final By TITLE = By.cssSelector("[data-test = title]");
    private final By CART = By.xpath("//a[@class='shopping_cart_link']");
    private final String ADD_TO_CART_PATTERN = "//*[text()='%s']//ancestor::div[@class='inventory_item']//button[text()='Add to cart']";

    public ProductsPage(WebDriver driver) {
        super(driver);
    }

    @Step("Получение тайтла на странице Product")
    public String getTitle() {
        return driver.findElement(TITLE).getText();
    }

    @Step("Добавление товара в корзину")
    public void addToCart(String product) {
        driver.findElement(By.xpath(String.format(ADD_TO_CART_PATTERN, product))).click();
    }

    @Step("Клик по кнопке Cart")
    public void clickCart() {
        driver.findElement(CART).click();
    }
}
