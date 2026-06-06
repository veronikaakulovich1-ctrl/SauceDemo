package pages;

import dto.CustomerInfo;
import dto.CustomerInfoFactory;
import io.qameta.allure.Step;
import lombok.extern.log4j.Log4j2;
import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;

@Log4j2
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
       try {
           wait.until(ExpectedConditions.visibilityOfElementLocated(FIRSTNAME_FIELD));
       } catch (TimeoutException e) {
           log.error(e.getMessage());
           Assert.fail("Page isn't opened");
       }
        return this;
    }

    @Step("Попытка продолжить заказ с невалидными данными: '{firstname}', '{lastname}', '{zipcode}'")
    public CheckoutYourInformationPage continueOrderExpectingError(String firstname, String lastname, String zipcode) {
        log.info("Entering first_name '{}' last_name '{}' and zipcode '{}' on a Checkout Information page", firstname, lastname, zipcode);
        driver.findElement(FIRSTNAME_FIELD).sendKeys(firstname);
        driver.findElement(LASTNAME_FIELD).sendKeys(lastname);
        driver.findElement(ZIP_CODE_FIELD).sendKeys(zipcode);
        driver.findElement(CONTINUE_BUTTON).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(ERROR_MESSAGE_FOR_CHECKOUT));
        return this;
    }

    @Step("Ввод валидных данных для оформления заказа")
    public CheckoutOverviewPage continueOrder() {
        return continueOrder(CustomerInfoFactory.getCustomer());
    }

    @Step("Ввод данных для оформления заказа: '{customer.firstName}', '{customer.lastName}', '{customer.zipCode}'")
    public CheckoutOverviewPage continueOrder(CustomerInfo customer) {
        log.info("Continue order with created customer '{}'", customer);
        fillCheckoutForm(customer);
        driver.findElement(CONTINUE_BUTTON).click();
        return new CheckoutOverviewPage(driver).isPageOpened();
    }

    private void fillCheckoutForm(CustomerInfo customer) {
        driver.findElement(FIRSTNAME_FIELD).sendKeys(customer.getFirstName());
        driver.findElement(LASTNAME_FIELD).sendKeys(customer.getLastName());
        driver.findElement(ZIP_CODE_FIELD).sendKeys(customer.getZipCode());
    }

    @Step("Получение ошибки на странице Checkout Information")
    public String getErrorMessageForCheckoutInformationPage() {
        log.info("Error is displayed after entering invalid information");
        wait.until(ExpectedConditions.visibilityOfElementLocated(ERROR_MESSAGE_FOR_CHECKOUT));
        return driver.findElement(ERROR_MESSAGE_FOR_CHECKOUT).getText();
    }

    @Step("Клик по кнопке Cancel на странице Checkout Information")
    public YourCartPage cancel() {
        log.info("Your cart page is displayed after transition upon Cancel button");
        driver.findElement(CANCEL_BUTTON).click();
        return new YourCartPage(driver).isPageOpened();
    }
}
