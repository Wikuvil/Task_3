package tests;

import api.client.UserApi;
import api.models.TestUser;
import config.BrowserType;
import io.qameta.allure.Step;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EnumSource;

public class PersonalAccountTest extends BaseTest{
    private String accessToken;

    @AfterEach
    public void tearDown(){
        if (accessToken != null) {
            UserApi.deleteUser(accessToken);
        }
        super.tearDown();
    }

    @Step
    void openPersonalAccountWithAuth(BrowserType browser){
        TestUser testUser = createTestUser();
        accessToken = testUser.getAccessToken();

        createDriver(browser);
        driver.get(BASE_URL);

        homePage.clickLoginButton();
        loginPage.loginAsUser(testUser.getEmail(), testUser.getPassword());
        homePage.clickPersonalAccountButton();
    }

    @ParameterizedTest
    @EnumSource(BrowserType.class)
    @DisplayName("Переход по клику на «Конструктор» c авторизацией")
    void openConstructorFromPersonalAccountViaConstructorButtonWithAuth(BrowserType browser) {
        openPersonalAccountWithAuth(browser);
        homePage.clickConstructorButton();

        Assertions.assertTrue(
                loginPage.isElementVisible(constructorPage.getBuildBurgerHeader()),
                "После нажатия на кнопку «Конструктор» не отображается заголовок «Соберите бургер»"
        );

        Assertions.assertFalse(
                driver.getCurrentUrl().contains("/login"),
                "После успешной авторизации URL страницы все еще содержит  /login"
        );
    }

    @ParameterizedTest
    @EnumSource(BrowserType.class)
    @DisplayName("Переход по клику на логотип Stellar Burgers c авторизацией")
    void openConstructorFromPersonalAccountViaStellarBurgersLogoWithAuth(BrowserType browser) {
        openPersonalAccountWithAuth(browser);
        homePage.clickStellarBurgersLogo();

        Assertions.assertTrue(
                loginPage.isElementVisible(constructorPage.getBuildBurgerHeader()),
                "После нажатия на кнопку «Конструктор» не отображается заголовок «Соберите бургер»"
        );

        Assertions.assertFalse(
                driver.getCurrentUrl().contains("/login"),
                "После успешной авторизации URL страницы все еще содержит  /login"
        );
    }


    @ParameterizedTest
    @EnumSource(BrowserType.class)
    @DisplayName("Переход по клику на «Конструктор» без авторизации")
    void openConstructorFromPersonalAccountViaConstructorButtonWithoutAuth(BrowserType browser) {
        openPage(browser, LOGIN_URL);
        homePage.clickConstructorButton();

        Assertions.assertTrue(
                loginPage.isElementVisible(constructorPage.getBuildBurgerHeader()),
                "После нажатия на кнопку «Конструктор» не отображается заголовок «Соберите бургер»"
        );

        Assertions.assertFalse(
                driver.getCurrentUrl().contains("/login"),
                "После успешной авторизации URL страницы все еще содержит  /login"
        );
    }

    @ParameterizedTest
    @EnumSource(BrowserType.class)
    @DisplayName("Переход по клику на логотип Stellar Burgers без авторизации")
    void openConstructorFromPersonalAccountViaStellarBurgersLogoWithoutAuth(BrowserType browser) {
        openPage(browser, LOGIN_URL);
        homePage.clickStellarBurgersLogo();

        Assertions.assertTrue(
                loginPage.isElementVisible(constructorPage.getBuildBurgerHeader()),
                "После нажатия на кнопку «Конструктор» не отображается заголовок «Соберите бургер»"
        );

        Assertions.assertFalse(
                driver.getCurrentUrl().contains("/login"),
                "После успешной авторизации URL страницы все еще содержит  /login"
        );
    }

    @ParameterizedTest
    @EnumSource(BrowserType.class)
    @DisplayName("Выход по кнопке «Выйти» в личном кабинете")
    void logoutUser(BrowserType browser) {
        openPersonalAccountWithAuth(browser);
        personalAccountPage.clickLogoutButton();

        Assertions.assertTrue(
                loginPage.isElementVisible(loginPage.getLoginHeader()),
                "После выхода из личного кабинета не отображается заголовок «Вход»"
        );

        Assertions.assertTrue(
                driver.getCurrentUrl().contains("/login"),
                "После выхода из личного кабинета URL страницы не содержит /login"
        );
    }

}
