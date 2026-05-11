package tests;

import org.openqa.selenium.By;
import org.testng.annotations.Test;

public class LocatorsTest extends BaseTest {

    @Test
    public void checkLocation() {
        driver.get("https://www.saucedemo.com/");
        driver.findElement(By.id("user-name")).sendKeys("standard_user");
        driver.findElement(By.name("password")).sendKeys("secret_sauce");
        driver.findElement(By.className("submit-button")).click();
        driver.findElement(By.linkText("Sauce Labs Backpack")).click();
        driver.findElement(By.tagName("button")).click();
        driver.navigate().back();
        driver.findElement(By.partialLinkText("Sauce Labs Bike Light")).click();
        driver.findElement(By.cssSelector("#add-to-cart")).click();
        driver.navigate().back();
        driver.findElement(By.xpath("(//div[@data-test='inventory-item-name'])[3]")).click();
        driver.findElement(By.cssSelector("button" +
                ".btn.btn_primary" +
                ".btn_small" +
                ".btn_inventory[data-test='add-to-cart']")).click();
        driver.navigate().back();
        driver.findElement(By.xpath("//div[text()='Sauce Labs Backpack']"));
        driver.findElement(By.xpath("//input[contains(@class,'input_error')]"));
        driver.findElement(By.xpath("//div[contains(text(),'Swag')]"));
        driver.findElement(By.xpath("//div[text()='Sauce Labs Backpack']\n" +
                "/ancestor::div[@class='inventory_item']\n" +
                "//button[contains(@class, 'btn_inventory')]"));
        driver.findElement(By.xpath("//div[@data-test='inventory-item']\n" +
                "/descendant::div[@class='inventory_item_price']"));
        driver.findElement(By.xpath("(//div[text()='Sauce Labs Backpack']/following::div[@class='inventory_item_price'])[1]"));
        driver.findElement(By.xpath("//div[text()='Sauce Labs Backpack']/parent::a"));
        driver.findElement(By.xpath("//button[@data-test='add-to-cart-sauce-labs-backpack']\n" +
                "/preceding::div[@class='inventory_item_name'][1]"));
        driver.findElement(By.xpath("//div[@data-test='inventory-item-name' and text()='Sauce Labs Backpack']"));
        driver.findElement(By.cssSelector(".inventory_list"));
        driver.findElement(By.cssSelector(".btn.btn_primary.btn_small.btn_inventory"));
        driver.findElement(By.cssSelector("#menu_button_container"));
        driver.findElement(By.cssSelector("textarea"));
        driver.findElement(By.cssSelector("footer.footer"));
        driver.findElement(By.cssSelector("[data-test='header-container']"));
        driver.findElement(By.cssSelector("[data-test~='item-4-img-link']"));
        driver.findElement(By.cssSelector("[data-test|='item-4-title']"));
        driver.findElement(By.cssSelector("[data-test^=shopping]"));
        driver.findElement(By.cssSelector("[data-test$=light]"));
        driver.findElement(By.cssSelector("[data-test*=add-to-cart]"));
    }
}
