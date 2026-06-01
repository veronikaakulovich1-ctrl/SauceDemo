package pages;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class SauceLabsPage extends BasePage {

    private static final By SIGN_UP_FOR_FREE_BUTTON = By.xpath(
            "//button[contains(@class,'MuiButton-root') and contains(., 'Sign up for free')]");

    public SauceLabsPage(WebDriver driver) {
        super(driver);
    }

    @Override
    public SauceLabsPage isPageOpened() {
        wait.until(ExpectedConditions.urlContains("saucelabs.com"));
        return this;
    }

    @Step("Получение текста кнопки Sign up for free на странице Sauce Labs")
    public String getSignUpButtonText() {
        return driver.findElement(SIGN_UP_FOR_FREE_BUTTON).getText().trim();
    }
}
