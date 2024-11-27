package site.stellarburger.pageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import io.qameta.allure.Step;
import java.time.Duration;

public class PasswordRecoveryPage {
    private WebDriver driver;

    //Заголовок "Восстановление пароля"
    private By passwordRecoveryPageHeader = By.xpath(".//h2[text()='Восстановление пароля']");
    //Кнопка Войти
    private By enterButton = By.xpath(".//a[text()='Войти']");

    public PasswordRecoveryPage(WebDriver driver) {
        this.driver = driver;
    }

    @Step("Метод ожидания прогрузки страницы восстановления пароля")
    public void waitForLoadPasswordRecoveryPage() {
        new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.visibilityOfElementLocated(passwordRecoveryPageHeader));
    }

    @Step("Клик по кнопке Войти")
    public void enterButtonClick(){
        driver.findElement(enterButton).click();
    }
}
