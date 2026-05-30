package steps;

import org.openqa.selenium.WebDriver;
import pages.LoginPage;

public class LoginStep {

    private final WebDriver driver;
    private final LoginPage loginPage;

    public LoginStep(WebDriver driver) {
        this.driver = driver;
        loginPage = new LoginPage(driver);
    }

    public void auth(String user, String password) {
        loginPage.open()
                .isPageOpened()
                .login(user, password);
    }

    public String loginWithError(String user, String password) {
        loginPage.open()
                .isPageOpened()
                .loginWithInvalidCred(user, password);
        return loginPage.getErrorMessage();
    }
}
