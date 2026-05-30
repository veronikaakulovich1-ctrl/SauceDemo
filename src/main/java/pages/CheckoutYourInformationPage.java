package pages;

import dto.CustomerInfo;
import dto.CustomerInfoFactory;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class CheckoutYourInformationPage extends BasePage {

    private final By FIRSTNAME_FIELD = By.xpath("//input[@id='first-name']");
    private final By LASTNAME_FIELD = By.xpath("//input[@id='last-name']");
    private final By ZIP_CODE_FIELD = By.xpath("//input[@id='postal-code']");
    private final By CONTINUE_BUTTON = By.xpath("//input[@name='continue']");
    private final By CANCEL_BUTTON = By.xpath("//button[@name='cancel']");
    private final By ERROR_MESSAGE_FOR_CHECKOUT = By.cssSelector("[data-test=error]");

    public CheckoutYourInformationPage(WebDriver driver) {
        super(driver);
    }

    @Override
    public CheckoutYourInformationPage isPageOpened() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(FIRSTNAME_FIELD));
        return this;
    }

    @Step("Попытка продолжить заказ с невалидными данными: '{firstname}', '{lastname}', '{zipcode}'")
    public CheckoutYourInformationPage continueOrderExpectingError(String firstname, String lastname, String zipcode) {
        driver.findElement(FIRSTNAME_FIELD).sendKeys(firstname);
        driver.findElement(LASTNAME_FIELD).sendKeys(lastname);
        driver.findElement(ZIP_CODE_FIELD).sendKeys(zipcode);
        driver.findElement(CONTINUE_BUTTON).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(ERROR_MESSAGE_FOR_CHECKOUT));
        return this;
    }

    @Step("Ввод валидных данных для оформления заказа")
    public CheckoutOverviewPage continueOrder() {
        fillCheckoutForm(CustomerInfoFactory.defaultCustomer());
        driver.findElement(CONTINUE_BUTTON).click();
        return new CheckoutOverviewPage(driver).isPageOpened();
    }

    private void fillCheckoutForm(CustomerInfo customer) {
        driver.findElement(FIRSTNAME_FIELD).sendKeys(customer.firstName());
        driver.findElement(LASTNAME_FIELD).sendKeys(customer.lastName());
        driver.findElement(ZIP_CODE_FIELD).sendKeys(customer.zipCode());
    }

    @Step("Получение ошибки на странице Checkout Information")
    public String getErrorMessageForCheckoutInformationPage() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(ERROR_MESSAGE_FOR_CHECKOUT));
        return driver.findElement(ERROR_MESSAGE_FOR_CHECKOUT).getText();
    }

    @Step("Клик по кнопке Cancel на странице Checkout Information")
    public YourCartPage cancel() {
        driver.findElement(CANCEL_BUTTON).click();
        return new YourCartPage(driver).isPageOpened();
    }
}
