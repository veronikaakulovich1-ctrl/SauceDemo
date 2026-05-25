package tests;

import io.qameta.allure.*;
import org.openqa.selenium.By;
import org.testng.Assert;
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
    @Description("Scenario: Check Login with valid credentials" +
            "Given: Login page is opened" +
            "WHEN: Valid user name is inputted" +
            "AND Valid Password is inputted" +
            "AND Login button is tapped" +
            "THEN: Product page is displayed")
    @Severity(SeverityLevel.CRITICAL)

    public void checkLoginWithPositiveCred() {
        loginPage.open();
        loginPage.login("standard_user", "secret_sauce");
        assertEquals(productsPage.getTitle(), "Products", "Title wasn't found");
    }

    @DataProvider(name = "Параметризированный тест для негативного логина")
    public Object[][] loginData() {
        return new Object[][]{
                {"", "secret_sauce", "Epic sadface: Username is required"},
                {"standard_user", "", "Epic sadface: Password is required"},
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
    @Description("Scenario Outline: Check Login with negative scenarios" +
            "Given: Login page is opened" +
            "WHEN: <username> is inputted" +
            "AND <password> is inputted" +
            "AND Login button is tapped" +
            "THEN: <ErrorMessage> is displayed")
    @Severity(SeverityLevel.NORMAL)
    public void chekLoginWithNegativeCred(String user, String password, String errorMessage) {
        loginPage.open();
        loginPage.login(user, password);
        assertEquals(loginPage.getErrorMessage(), errorMessage, "Error message isn't displayed");
    }
}
