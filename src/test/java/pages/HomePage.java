package pages;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class HomePage extends BasePage{
    public HomePage(WebDriver driver) {
        super(driver);
    }

    // Селектор логотипа "Stellar Burgers"
    private final By stellarBurgersLogo = By.xpath("//*[local-name()='svg' and @fill='none']");
    // Селектор кнопки "Войти"
    private final By loginButton = By.xpath("//button[normalize-space()='Войти в аккаунт']");
    // Селектор кнопки "Личный Кабинет"
    private final By personalAccountButton = By.xpath("//p[normalize-space()='Личный Кабинет']");
    // Селектор кнопки "Конструктор"
    private final By constructorButton = By.xpath("//p[normalize-space()='Конструктор']");
    @Step("Нажатие на кнопку «Войти в аккаунт»")
    public void clickLoginButton(){
        wait.until(ExpectedConditions.elementToBeClickable(loginButton)).click();
    }

    @Step("Нажатие на кнопку «Личный кабинет»")
    public void clickPersonalAccountButton(){
        wait.until(ExpectedConditions.elementToBeClickable(personalAccountButton)).click();
    }

    @Step("Нажатие на кнопку «Конструктор»")
    public void clickConstructorButton(){
        wait.until(ExpectedConditions.elementToBeClickable(constructorButton)).click();
    }

    @Step("Нажатие на лого «Stellar Burgers»")
    public void clickStellarBurgersLogo(){
        wait.until(ExpectedConditions.elementToBeClickable(stellarBurgersLogo)).click();
    }
}
