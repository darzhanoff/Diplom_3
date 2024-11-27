package site.stellarburger.pageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import io.qameta.allure.Step;
import java.time.Duration;

public class LogInPage {
    private WebDriver driver;

    //Заголовок "Вход"
    private By logInPageHeader = By.xpath(".//h2[text()='Вход']");
    //Поле "Email"
    private By emailField = By.xpath(".//label[text()='Email']/parent::div/input[@type='text']");
    //Поле "Пароль"
    private By passwordField = By.xpath(".//input[@name='Пароль']/parent::div/input[@type='password']");
    //Кнопка "Войти"
    private By enterButton = By.xpath(".//button[text()='Войти']");
    //Кнопка "Зарегистрироваться"
    private By registerButton = By.xpath(".//a[text()='Зарегистрироваться']");
    //Кнопка "Восстановить пароль"
    private By passwordRecoveryButton = By.xpath(".//a[text()='Восстановить пароль']");

    public LogInPage(WebDriver driver) {
        this.driver = driver;
    }

    @Step("Метод ожидания прогрузки страницы авторизации")
    public void waitForLoadLogInPage() {
        new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.visibilityOfElementLocated(logInPageHeader));
    }

    @Step("Метод заполнения поля Email")
    public void enteringEmailField(String email){
        driver.findElement(emailField).sendKeys(email);
    }

    @Step("Метод заполнения поля Пароль")
    public void enteringPasswordField(String password){
        driver.findElement(passwordField).sendKeys(password);
    }

    @Step("Клик по кнопке Войти")
    public void enterButtonClick(){
        driver.findElement(enterButton).click();
    }

    @Step("Клик по кнопке Зарегистрироваться")
    public void registerButtonClick(){
        driver.findElement(registerButton).click();
    }

    @Step("Клик по кнопке Восстановить пароль")
    public void passwordRecoverButtonClick(){
        driver.findElement(passwordRecoveryButton).click();
    }
}
