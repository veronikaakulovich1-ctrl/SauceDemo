package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class CheckoutYourInformationPage extends BasePage {

    public CheckoutYourInformationPage(WebDriver driver) {
        super(driver);
    }

    private final By FIRSTNAME_FIELD = By.xpath("//input[@id='first-name']");
    private final By LASTNAME_FIELD = By.xpath("//input[@id='last-name']");
    private final By ZIP_CODE_FIELD = By.xpath("//input[@id='postal-code']");
    private final By CONTINUE_BUTTON = By.xpath("//input[@name='continue']");
    private final By CANCEL_BUTTON = By.xpath("//button[@name='cancel']");
    private final By ERROR_MESSAGE_FOR_CHECKOUT = By.cssSelector("[data-test=error]");

    public void continueOrder(String firstname, String lastname, String zipcode) {
        driver.findElement(FIRSTNAME_FIELD).sendKeys(firstname);
        driver.findElement(LASTNAME_FIELD).sendKeys(lastname);
        driver.findElement(ZIP_CODE_FIELD).sendKeys(zipcode);
        driver.findElement(CONTINUE_BUTTON).click();
    }

    public String getErrorMessageForCheckoutInformationPage() {
        return driver.findElement(ERROR_MESSAGE_FOR_CHECKOUT).getText();
    }

    public void clickCancelButton() {
        driver.findElement(CANCEL_BUTTON).click();
    }
}
