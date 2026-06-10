package tests;

import io.qameta.allure.testng.AllureTestNg;
import listeners.TestListener;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.testng.ITestContext;
import org.testng.annotations.*;
import pages.*;
import steps.LoginStep;
import utils.PropertyReader;

import java.time.Duration;
import java.util.HashMap;

@Listeners({AllureTestNg.class, TestListener.class})
public class BaseTest {

    WebDriver driver;
    LoginStep loginStep;
    ProductsPage productsPage;
    YourCartPage yourCartPage;
    CheckoutYourInformationPage checkoutYourInformationPage;
    CheckoutOverviewPage checkoutOverviewPage;
    CheckoutCompletePage checkoutCompletePage;
    BurgerMenuPage burgerMenuPage;

    String user = System.getProperty("user", PropertyReader.getProperty("user"));
    String password = System.getProperty("password", PropertyReader.getProperty("password"));

    @Parameters({"browser"})
    @BeforeMethod(
            alwaysRun = true,
            description = "Настройка браузера и параметров"
    )
    public void setup(@Optional("chrome") String browser, ITestContext iTestContext) {
        if (browser.equalsIgnoreCase("chrome")) {
            ChromeOptions options = new ChromeOptions();
            HashMap<String, Object> chromePrefs = new HashMap<>();
            chromePrefs.put("credentials_enable_service", false);
            chromePrefs.put("profile.password_manager_enabled", false);
            options.setExperimentalOption("prefs", chromePrefs);
            options.addArguments("--incognito");
            options.addArguments("--disable-notifications");
            options.addArguments("--disable-popup-blocking");
            options.addArguments("--disable-infobars");
            if (System.getProperty("headless", "true").equals("true")) {
                options.addArguments("--headless");
            }
            options.addArguments("--no-sandbox");
            options.addArguments("--disable-dev-shm-usage");
            options.addArguments("--disable-gpu");
            driver = new ChromeDriver(options);
        } else if (browser.equalsIgnoreCase("edge")) {
            driver = new EdgeDriver();
            EdgeOptions options = new EdgeOptions();
            if (System.getProperty("headless", "true").equals("true")) {
                options.addArguments("--headless");
            }
            options.addArguments("--no-sandbox");
            options.addArguments("--disable-dev-shm-usage");
            options.addArguments("--disable-gpu");
            driver = new EdgeDriver(options);
        } else if (browser.equalsIgnoreCase("firefox")) {
            driver = new FirefoxDriver();
            FirefoxOptions options = new FirefoxOptions();
            if (System.getProperty("headless", "true").equals("true")) {
                options.addArguments("--headless");
            }
            options.addArguments("--no-sandbox");
            options.addArguments("--disable-dev-shm-usage");
            options.addArguments("--disable-gpu");
            driver = new FirefoxDriver(options);
        }

        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

        iTestContext.setAttribute("driver", driver);

        loginStep = new LoginStep(driver);
        productsPage = new ProductsPage(driver);
        yourCartPage = new YourCartPage(driver);
        checkoutYourInformationPage = new CheckoutYourInformationPage(driver);
        checkoutOverviewPage = new CheckoutOverviewPage(driver);
        checkoutCompletePage = new CheckoutCompletePage(driver);
        burgerMenuPage = new BurgerMenuPage(driver);
    }

    @AfterMethod(alwaysRun = true, description = "Закрытие браузера после завершения теста")
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}
