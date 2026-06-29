package pages;

import io.qameta.allure.Step;
import lombok.Getter;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class LoginPage extends BasePage{
    public LoginPage(WebDriver driver) {
        super(driver);
    }

    // Селектор заголовка "Вход"
    @Getter
    private final By loginHeader = By.xpath("//h2[normalize-space()='Вход']");

    // Селектор поля "Email"
    private final By emailField = By.name("name");
    // Селектор поля "Пароль"
    private final By passwordField = By.name("Пароль");

    // Селектор кнопки "Войти"
    private final By loginButton = By.xpath("//button[normalize-space()='Войти']");

    // Селектор кнопки "Восстановить пароль"
    private final By recoveryPasswordButton = By.linkText("Восстановить пароль");
    // Селектор кнопки "Зарегистрироваться"
    private final By registerButton = By.linkText("Зарегистрироваться");

    @Step("Нажатие на гиперссылку «Зарегистрироваться» для перехода в форму регистрации")
    public void clickRegisterButton(){
        wait.until(ExpectedConditions.elementToBeClickable(registerButton)).click();
    }

    @Step("Нажатие на кнопку «Войти»")
    public void clickLoginButton(){
        wait.until(ExpectedConditions.elementToBeClickable(loginButton)).click();
    }

    @Step("Нажатие на кнопку «Восстановить пароль»")
    public void clickRecoveryPasswordButton(){
        wait.until(ExpectedConditions.elementToBeClickable(recoveryPasswordButton)).click();
    }

    @Step("Заполнение поля «Email»")
    public void fillEmailField(String email){
        wait.until(ExpectedConditions.visibilityOfElementLocated(emailField)).sendKeys(email);
    }

    @Step("Заполнение поля «Пароль»")
    public void fillPasswordField(String password){
        wait.until(ExpectedConditions.visibilityOfElementLocated(passwordField)).sendKeys(password);
    }

    @Step("Заполнение формы авторизации с email {email}")
    public void loginAsUser(String email, String password) {
        fillEmailField(email);
        fillPasswordField(password);
        clickLoginButton();
    }

}
