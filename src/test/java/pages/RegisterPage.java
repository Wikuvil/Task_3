package pages;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class RegisterPage extends BasePage{
    public RegisterPage(WebDriver driver) {
        super(driver);
    }

    // Селектор поля "Имя"
    private final By userNameField = By.xpath("(//input[@name='name'])[1]");
    // Селектор поля "Email"
    private final By emailField = By.xpath("(//input[@name='name'])[2]");
    // Селектор поля "Пароль"
    private final By passwordField = By.name("Пароль");

    private final By InvalidPasswordErrorText = By.xpath("//p[normalize-space()='Некорректный пароль']");

    // Селектор кнопки "Зарегистрироваться"
    private final By registerButton = By.xpath("//button[normalize-space()='Зарегистрироваться']");
    // Селектор кнопки "Войти"
    private final By loginButton = By.linkText("Войти");

    @Step("Заполнение поля «Имя»")
    public void fillUserNameField(String userName){
        wait.until(ExpectedConditions.visibilityOfElementLocated(userNameField)).sendKeys(userName);
    }

    @Step("Заполнение поля «Email»")
    public void fillEmailField(String email){
        wait.until(ExpectedConditions.visibilityOfElementLocated(emailField)).sendKeys(email);
    }

    @Step("Заполнение поля «Пароль»")
    public void fillPasswordField(String password){
        wait.until(ExpectedConditions.visibilityOfElementLocated(passwordField)).sendKeys(password);
    }

    @Step("Нажатие на кнопку «Зарегистрироваться»")
    public void clickRegisterButton(){
        wait.until(ExpectedConditions.elementToBeClickable(registerButton)).click();
    }

    @Step("Получение текста ошибки при длине пароля менее 6")
    public String getErrorMessageText(){
        return wait.until(ExpectedConditions.visibilityOfElementLocated(InvalidPasswordErrorText)).getText();
    }

    @Step("Нажатие на кнопку «Войти»")
    public void clickLoginButton(){
        wait.until(ExpectedConditions.elementToBeClickable(loginButton)).click();
    }

}
