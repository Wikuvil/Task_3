package tests;

import api.client.UserApi;
import config.BrowserType;
import generators.UserCredsGenerator;
import io.qameta.allure.Description;
import io.qameta.allure.Step;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EnumSource;

public class RegisterTest extends BaseTest{
    private String email;
    private String password;
    private String accessToken;

    @AfterEach
    public void tearDown(){
        if (email != null && password != null){
            accessToken = UserApi.loginUser(email, password);
        }
        if (accessToken != null) {
            UserApi.deleteUser(accessToken);
        }
        super.tearDown();
    }

    @ParameterizedTest
    @EnumSource(BrowserType.class)
    @DisplayName("Успешная регистрация пользователя")
    void successfulUserRegisterTest(BrowserType browser) {
        String userName = UserCredsGenerator.randomName();
        email = UserCredsGenerator.randomEmail();
        password = UserCredsGenerator.randomPassword();

        openRegisterPage(browser);
        registerPage.fillUserNameField(userName);
        registerPage.fillEmailField(email);
        registerPage.fillPasswordField(password);
        registerPage.clickRegisterButton();

        Assertions.assertTrue(
                loginPage.isElementVisible(loginPage.getLoginHeader()),
                "Заголовок «Вход» не отображается после регистрации"
        );

        Assertions.assertTrue(
                driver.getCurrentUrl().contains("/login"),
                "После регистрации произошел переход на страницу авторизации"
        );
    }

    @ParameterizedTest
    @EnumSource(BrowserType.class)
    @DisplayName("Ошибка для некорректного пароля при регистрации пользователя")
    @Description("Минимальный пароль — шесть символов.")
    void registerWithShortPasswordShowsErrorTest(BrowserType browser) {
        String userName = UserCredsGenerator.randomName();
        String email = UserCredsGenerator.randomEmail();
        String shortPassword = UserCredsGenerator.randomPassword(5);
        String expectedErrorMessage = "Некорректный пароль";

        openRegisterPage(browser);
        registerPage.fillUserNameField(userName);
        registerPage.fillEmailField(email);
        registerPage.fillPasswordField(shortPassword);
        registerPage.clickRegisterButton();

        String actualErrorMessage = registerPage.getErrorMessageText();
        Assertions.assertEquals(expectedErrorMessage, actualErrorMessage);

        Assertions.assertTrue(
                driver.getCurrentUrl().contains("/register"),
                "После ошибки о некорректном пароле произошел переход из окна регистрации"
        );

    }

    @Step("Переход на страницу регистрации в браузере: {browser}")
    private void openRegisterPage(BrowserType browser) {
        createDriver(browser);
        driver.get(BASE_URL);
        homePage.clickLoginButton();
        loginPage.clickRegisterButton();
    }
}
