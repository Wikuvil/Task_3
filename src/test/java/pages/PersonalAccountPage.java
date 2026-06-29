package pages;

import io.qameta.allure.Step;
import lombok.Getter;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class PersonalAccountPage extends BasePage{
    public PersonalAccountPage(WebDriver driver) {
        super(driver);
    }

    // Селектор кнопки "Профиль"
    @Getter
    private final By profileButton = By.linkText("Профиль");

    // Селектор кнопки "Выход"
    @Getter
    private final By logoutButton = By.xpath("//button[contains(normalize-space(),'Выход')]");

    @Step("Нажатие на кнопку «Выход»")
    public void clickLogoutButton(){
        wait.until(ExpectedConditions.elementToBeClickable(logoutButton)).click();
    }
}
