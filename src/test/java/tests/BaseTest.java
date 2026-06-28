package tests;

import api.client.UserApi;
import api.models.TestUser;
import api.models.User;
import config.BrowserType;
import driver.WebDriverFactory;
import generators.UserGenerator;
import io.qameta.allure.Step;
import org.junit.jupiter.api.AfterEach;
import org.openqa.selenium.WebDriver;
import pages.*;

public abstract class BaseTest {
    protected WebDriver driver;
    protected static final String BASE_URL = "https://qa-stellarburgers.education-services.ru/";
    protected static final String LOGIN_URL = BASE_URL + "login";

    protected HomePage homePage;
    protected RegisterPage registerPage;
    protected LoginPage loginPage;
    protected PersonalAccountPage personalAccountPage;
    protected ConstructorPage constructorPage;
    protected ForgotPasswordPage forgotPasswordPage;

    protected void createDriver(BrowserType browser) {
        driver = WebDriverFactory.createDriver(browser);
        initPages();
    }

    protected void openPage(BrowserType browser) {
        createDriver(browser);
        driver.get(BASE_URL);
    }

    protected void openPage(BrowserType browser, String URL) {
        createDriver(browser);
        driver.get(URL);
    }

    private void initPages() {
        homePage = new HomePage(driver);
        registerPage = new RegisterPage(driver);
        loginPage = new LoginPage(driver);
        personalAccountPage = new PersonalAccountPage(driver);
        constructorPage = new ConstructorPage(driver);
        forgotPasswordPage = new ForgotPasswordPage(driver);
    }

    @Step("Создание тестового пользователя через API")
    protected TestUser createTestUser() {
        User user = UserGenerator.randomUser();
        String accessToken = UserApi.createUserReturnToken(user);
        return TestUser.builder()
                .email(user.getEmail())
                .password(user.getPassword())
                .accessToken(accessToken)
                .build();
    }

    @AfterEach
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}