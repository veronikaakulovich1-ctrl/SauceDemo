package pages;

import io.qameta.allure.Step;
import lombok.extern.log4j.Log4j2;
import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;

@Log4j2
public class LoginPage extends BasePage {

    private final By USERNAME_FIELD = By.id("user-name");
    private final By PASSWORD_FIELD = By.id("password");
    private final By LOGIN_BUTTON = By.id("login-button");
    private final By ERROR_MESSAGE = By.cssSelector("[data-test=error]");

    public LoginPage(WebDriver driver) {
        super(driver);
    }

    @Step("Открытие страницы Login")
    public LoginPage open() {
        log.info("Log in page is displayed");
        driver.get(BASE_URL);
        return this;
    }

    @Override
    public LoginPage isPageOpened() {
      try {
          wait.until(ExpectedConditions.visibilityOfElementLocated(LOGIN_BUTTON));
      } catch (TimeoutException e) {
          log.error(e.getMessage());
          Assert.fail("Page isn't opened");
      }
        return this;
    }

    @Step("Вход в систему с именем пользователя: '{user}' и паролем '{password}'")
    public ProductsPage login(String user, String password) {
        log.info("Log in with user '{}' and password '{}'", user, password);
        driver.findElement(USERNAME_FIELD).sendKeys(user);
        driver.findElement(PASSWORD_FIELD).sendKeys(password);
        driver.findElement(LOGIN_BUTTON).click();
        return new ProductsPage(driver).isPageOpened();
    }

    @Step("Попытка входа с ошибкой: '{user}' и паролем '{password}'")
    public LoginPage loginWithInvalidCred(String user, String password) {
        log.info("Log in with invalid credentials user '{}' and password '{}'", user, password);
        driver.findElement(USERNAME_FIELD).sendKeys(user);
        driver.findElement(PASSWORD_FIELD).sendKeys(password);
        driver.findElement(LOGIN_BUTTON).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(ERROR_MESSAGE));
        return this;
    }

    @Step("Получение ошибки на странице Login")
    public String getErrorMessage() {
        log.info("Error message is displayed after entering invalid credentials");
        return driver.findElement(ERROR_MESSAGE).getText();
    }

    @Step("Отображение кнопки Login на странице Login")
    public WebElement getLoginButton() {
        return driver.findElement(LOGIN_BUTTON);
    }
}
