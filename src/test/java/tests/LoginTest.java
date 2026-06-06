package tests;

import io.qameta.allure.*;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;

public class LoginTest extends BaseTest {

    @Test(
            description = "Проверка логина с валидными логином и паролем",
            testName = "Проверка логина с валидными логином и паролем",
            groups = "smoke"
    )
    @Owner("Akulovich")
    @Feature("Login")
    @Severity(SeverityLevel.CRITICAL)
    public void checkLoginWithPositiveCred() {
        loginStep.auth(user, password);
        assertEquals(productsPage.isPageOpened().getTitle(), "Products", "Title wasn't found");
    }

    @DataProvider(name = "Параметризированный тест для негативного логина")
    public Object[][] loginData() {
        return new Object[][]{
                {"", password, "Epic sadface: Username is required"},
                {user, "", "Epic sadface: Password is required"},
                {"test", "test", "Epic sadface: Username and password do not match any user in this service"}
        };
    }

    @Test(dataProvider = "Параметризированный тест для негативного логина",
            description = "Проверка логина по негативным сценариям",
            testName = "Проверка логина по негативным сценариям",
            groups = "regression"
    )
    @Owner("Akulovich")
    @Feature("Login")
    @Severity(SeverityLevel.NORMAL)
    public void chekLoginWithNegativeCred(String user, String password, String errorMessage) {
        String actualError = loginStep.loginWithError(user, password);
        assertEquals(actualError, errorMessage, "Error message isn't displayed");
    }
}
