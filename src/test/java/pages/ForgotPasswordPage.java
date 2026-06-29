package pages;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class ForgotPasswordPage extends BasePage{

    public ForgotPasswordPage(WebDriver driver) {
        super(driver);
    }

    // Селектор кнопки "Войти"
    private final By loginButton = By.linkText("Войти");

    @Step("Нажатие на кнопку «Войти»")
    public void clickLoginButton(){
        wait.until(ExpectedConditions.elementToBeClickable(loginButton)).click();
    }
}
