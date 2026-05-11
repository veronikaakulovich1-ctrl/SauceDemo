package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

public class CheckoutOverviewPage extends BasePage {

    private final By TITLE_CHECKOUT_OVERVIEW = By.cssSelector("[data-test = title]");
    private final By CART_ITEMS = By.cssSelector(".cart_item");
    private final By CART_ITEMS_PRICE = By.cssSelector(".inventory_item_price");
    private final By SUMMARY_SUBTOTAL_PRICE = By.xpath("//div[@class='summary_subtotal_label']");
    private final By FINISH_BUTTON = By.xpath("//button[@name='finish']");
    private final By CANCEL_BUTTON = By.xpath("//button[@name='cancel']");

    public CheckoutOverviewPage(WebDriver driver) {
        super(driver);
    }

    public String getTitle() {
        return driver.findElement(TITLE_CHECKOUT_OVERVIEW).getText();
    }

    public String calculateTotalPriceOnCheckout() {
        List<WebElement> cartItems = driver.findElements(CART_ITEMS);
        if (cartItems.isEmpty()) {
            return "0.00";
        }
        Double totalPrice = 0.00;
        for (WebElement item : cartItems) {
            String priceText = item.findElement(CART_ITEMS_PRICE).getText();
            double price = Double.parseDouble(priceText.replace("$", ""));
            totalPrice += price;
        }
        return totalPrice.toString();
    }

    public String getSummarySubtotalPrice() {
        String fullText = driver.findElement(SUMMARY_SUBTOTAL_PRICE).getText();
        return fullText.replaceAll("[^\\d.]", "").trim();
    }

    public void clickFinishButton() {
        driver.findElement(FINISH_BUTTON).click();
    }
}
