package steps;

import lombok.extern.log4j.Log4j2;
import org.openqa.selenium.WebDriver;
import pages.LoginPage;

@Log4j2
public class LoginStep {

    private final WebDriver driver;
    private final LoginPage loginPage;

    public LoginStep(WebDriver driver) {
        this.driver = driver;
        loginPage = new LoginPage(driver);
    }

    public void auth(String user, String password) {
        log.info("User log in witn credentials: user '{}' and password '{}'", user, password);
        loginPage.open()
                .isPageOpened()
                .login(user, password);
    }

    public String loginWithError(String user, String password) {
        log.info("Log in witn invalid credentials '{}' '{}' and getting error message", user, password);
        loginPage.open()
                .isPageOpened()
                .loginWithInvalidCred(user, password);
        return loginPage.getErrorMessage();
    }
}
