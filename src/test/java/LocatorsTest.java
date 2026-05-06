import org.openqa.selenium.By;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
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
    }
}
