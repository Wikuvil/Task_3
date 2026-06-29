package tests;

import api.client.UserApi;
import api.models.TestUser;
import config.BrowserType;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EnumSource;

public class HomePageTest extends BaseTest{
    private String accessToken;

    @AfterEach
    public void tearDown(){
        if (accessToken != null) {
            UserApi.deleteUser(accessToken);
        }
        super.tearDown();
    }

    @ParameterizedTest
    @EnumSource(BrowserType.class)
    @DisplayName("Переход по клику на «Личный кабинет» после авторизации")
    void openPersonalAccountWithAuth(BrowserType browser) {
        TestUser testUser = createTestUser();
        accessToken = testUser.getAccessToken();

        openPage(browser);

        homePage.clickLoginButton();
        loginPage.loginAsUser(testUser.getEmail(), testUser.getPassword());
        homePage.clickPersonalAccountButton();

        Assertions.assertTrue(
                homePage.isElementVisible(personalAccountPage.getProfileButton())
        );

        Assertions.assertTrue(
                driver.getCurrentUrl().contains("/profile"),
                "После успешной авторизации URL страницы все еще содержит  /login"
        );
    }

    @ParameterizedTest
    @EnumSource(BrowserType.class)
    @DisplayName("Переход по клику на «Личный кабинет» без авторизации")
    void openPersonalAccountWithoutAuth(BrowserType browser) {
        openPage(browser);

        homePage.clickPersonalAccountButton();

        Assertions.assertTrue(
                loginPage.isElementVisible(loginPage.getLoginHeader()),
                "После нажатия на кнопку «Личный кабинет» без авторизации не отображается заголовок «Вход»"
        );

        Assertions.assertTrue(
                driver.getCurrentUrl().contains("/login"),
                "После нажатия на кнопку «Личный кабинет» без авторизации не открывается окно входа"
        );
    }
}
