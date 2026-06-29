package pages;

import io.qameta.allure.Step;
import lombok.Getter;
import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class ConstructorPage extends BasePage{
    public ConstructorPage(WebDriver driver) {
        super(driver);
    }

    // Селектор кнопки "Оформить заказ"
    @Getter
    private final By makeOrderButton = By.xpath("//button[normalize-space()='Оформить заказ']");
    // Селектор заголовка "Соберите бургер"
    @Getter
    private final By buildBurgerHeader = By.xpath("//h1[normalize-space()='Соберите бургер']");

    // Селектор кнопки "Булки"
    @Getter
    private final By bunsButton = By.xpath("//span[text()='Булки']/ancestor::div[contains(@class, 'tab_tab__1SPyG')]");
    // Селектор кнопки "Соусы"
    @Getter
    private final By saucesButton = By.xpath("//span[text()='Соусы']/ancestor::div[contains(@class, 'tab_tab__1SPyG')]");
    // Селектор кнопки "Начинки"
    @Getter
    private final By fillingsButton = By.xpath("//span[text()='Начинки']/ancestor::div[contains(@class, 'tab_tab__1SPyG')]");

    @Step("Нажатие на кнопку «Булки»")
    public void clickBunsButton(){
        wait.until(ExpectedConditions.elementToBeClickable(bunsButton)).click();
    }

    @Step("Нажатие на кнопку «saucesButton»")
    public void clickSaucesButton(){
        wait.until(ExpectedConditions.elementToBeClickable(saucesButton)).click();
    }

    @Step("Нажатие на кнопку «fillingsButton»")
    public void clickFillingsButton(){
        wait.until(ExpectedConditions.elementToBeClickable(fillingsButton)).click();
    }

    public boolean isTabActive(By tabLocator) {
        final String ACTIVE_TAB_CLASS = "tab_tab_type_current__2BEPc";
        try {
            wait.until(ExpectedConditions.attributeContains(tabLocator, "class", ACTIVE_TAB_CLASS));
            return true;
        } catch (TimeoutException e) {
            return false;
        }
    }

    @Step("Раздел «Булки» активен")
    public boolean isTabBunsActive(){
        return isTabActive(bunsButton);
    }

    @Step("Раздел «Соусы» активен")
    public boolean isTabSaucesActive(){
        return isTabActive(saucesButton);
    }

    @Step("Раздел «Начинки» активен")
    public boolean isTabFillingsActive(){
        return isTabActive(fillingsButton);
    }
}
