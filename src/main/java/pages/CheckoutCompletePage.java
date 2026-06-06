package pages;

import io.qameta.allure.Step;
import lombok.extern.log4j.Log4j2;
import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;

@Log4j2
public class CheckoutCompletePage extends BasePage {

    private final By TITLE = By.cssSelector("[data-test=title]");
    private final By BACK_HOME_BUTTON = By.xpath("//button[@name='back-to-products']");

    public CheckoutCompletePage(WebDriver driver) {
        super(driver);
    }

    @Override
    public CheckoutCompletePage isPageOpened() {
       try {
           wait.until(ExpectedConditions.visibilityOfElementLocated(TITLE));
       } catch (TimeoutException e) {
           log.error(e.getMessage());
           Assert.fail("Page isn't opened");
       }
        return this;
    }

    @Step("Отображение тайтла на странице Checkout Complete")
    public String getTitle() {
        log.info("Checkout Completed title is displayed");
        return driver.findElement(TITLE).getText();
    }

    @Step("Клик по кнопке Back Home")
    public ProductsPage backToProducts() {
        log.info("Product page is opened after transition opon Back Home button");
        driver.findElement(BACK_HOME_BUTTON).click();
        return new ProductsPage(driver).isPageOpened();
    }
}
