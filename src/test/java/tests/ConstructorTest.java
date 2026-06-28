package tests;

import config.BrowserType;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EnumSource;

public class ConstructorTest extends BaseTest{

    @ParameterizedTest
    @EnumSource(BrowserType.class)
    @DisplayName("Переход к разделу «Булки»")
    void tabBunsShouldBeActive(BrowserType browser) {
        openPage(browser);

        constructorPage.clickSaucesButton();
        constructorPage.clickBunsButton();

        Assertions.assertTrue(
                constructorPage.isTabBunsActive(),
                "Раздел «Булки» остается неактивным после перехода к нему"
        );

    }

    @ParameterizedTest
    @EnumSource(BrowserType.class)
    @DisplayName("Переход к разделу «Соусы»")
    void tabSaucesShouldBeActive(BrowserType browser) {
        openPage(browser);

        constructorPage.clickSaucesButton();

        Assertions.assertTrue(
                constructorPage.isTabSaucesActive(),
                "Раздел «Соусы» остается неактивным после перехода к нему"
        );

    }

    @ParameterizedTest
    @EnumSource(BrowserType.class)
    @DisplayName("Переход к разделу «Начинки»")
    void tabFillingsShouldBeActive(BrowserType browser) {
        openPage(browser);

        constructorPage.clickFillingsButton();

        Assertions.assertTrue(
                constructorPage.isTabFillingsActive(),
                "Раздел «Начинки» остается неактивным после перехода к нему"
        );

    }

    @ParameterizedTest
    @EnumSource(BrowserType.class)
    @DisplayName("Раздел «Булки» активен по умолчанию")
    void tabBunsShouldBeActiveByDefault(BrowserType browser) {
        openPage(browser);

        Assertions.assertTrue(
                constructorPage.isTabBunsActive(),
                "Раздел «Булки» неактивен по умолчанию"
        );

    }

}
