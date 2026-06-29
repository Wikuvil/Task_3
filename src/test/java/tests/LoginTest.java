package tests;

import api.client.UserApi;
import api.models.TestUser;
import config.BrowserType;
import config.LoginEntryPoint;
import io.qameta.allure.Step;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EnumSource;

public class LoginTest extends BaseTest{

    private TestUser testUser;
    private String accessToken;

    @BeforeEach
    public void setUp() {
        testUser = createTestUser();
        accessToken = testUser.getAccessToken();
    }

    @AfterEach
    public void tearDown(){
        if (accessToken != null) {
            UserApi.deleteUser(accessToken);
        }
        super.tearDown();
    }

    @Step("Запуск браузера и переход на страницу авторизации через {entryPoint} в браузере: {browser}")
    private void openLoginPage(BrowserType browser, LoginEntryPoint entryPoint) {
        openPage(browser);

        switch (entryPoint) {
            case LOGIN_BUTTON:
                homePage.clickLoginButton();
                break;
            case PERSONAL_ACCOUNT:
                homePage.clickPersonalAccountButton();
                break;
            case REGISTER_PAGE:
                homePage.clickLoginButton();
                loginPage.clickRegisterButton();
                registerPage.clickLoginButton();
                break;
            case RECOVERY_PASSWORD_PAGE:
                homePage.clickLoginButton();
                loginPage.clickRecoveryPasswordButton();
                forgotPasswordPage.clickLoginButton();
                break;
            default:
                throw new IllegalArgumentException("Неизвестная точка входа: " + entryPoint);
        }
    }

    private void performLoginAndVerify(BrowserType browser, LoginEntryPoint entryPoint) {
        openLoginPage(browser, entryPoint);
       loginPage.loginAsUser(testUser.getEmail(), testUser.getPassword());

        Assertions.assertTrue(
                constructorPage.isElementVisible(constructorPage.getMakeOrderButton()),
                String.format("Кнопка «Оформить заказ» не отображается после авторизации через %s в %s.", entryPoint, browser)
        );

        Assertions.assertFalse(
                driver.getCurrentUrl().contains("/login"),
                "После успешной авторизации URL страницы все еще содержит  /login"
        );
    }

    @ParameterizedTest
    @EnumSource(BrowserType.class)
    @DisplayName("Вход по кнопке «Войти в аккаунт» на главной странице")
    void userLoginTestFromLoginButton(BrowserType browser) {
        performLoginAndVerify(browser, LoginEntryPoint.LOGIN_BUTTON);
    }

    @ParameterizedTest
    @EnumSource(BrowserType.class)
    @DisplayName("Вход через кнопку «Личный кабинет»")
    void userLoginTestFromPersonalAccountButton(BrowserType browser) {
        performLoginAndVerify(browser, LoginEntryPoint.PERSONAL_ACCOUNT);
    }

    @ParameterizedTest
    @EnumSource(BrowserType.class)
    @DisplayName("Вход через кнопку в форме регистрации")
    void userLoginTestFromRegisterPage(BrowserType browser) {
        performLoginAndVerify(browser, LoginEntryPoint.REGISTER_PAGE);
    }

    @ParameterizedTest
    @EnumSource(BrowserType.class)
    @DisplayName("Вход через кнопку в форме восстановления пароля")
    void userLoginTestFromPasswordRecoveryPage(BrowserType browser) {
        performLoginAndVerify(browser, LoginEntryPoint.RECOVERY_PASSWORD_PAGE);
    }

}
