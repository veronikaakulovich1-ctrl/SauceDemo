package pages;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class CheckoutCompletePage extends BasePage {

    private final By TITLE = By.cssSelector("[data-test=title]");
    private final By BACK_HOME_BUTTON = By.xpath("//button[@name='back-to-products']");

    public CheckoutCompletePage(WebDriver driver) {
        super(driver);
    }

    @Override
    public CheckoutCompletePage isPageOpened() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(TITLE));
        return this;
    }

    @Step("Отображение тайтла на странице Checkout Complete")
    public String getTitle() {
        return driver.findElement(TITLE).getText();
    }

    @Step("Клик по кнопке Back Home")
    public ProductsPage backToProducts() {
        driver.findElement(BACK_HOME_BUTTON).click();
        return new ProductsPage(driver).isPageOpened();
    }
}
