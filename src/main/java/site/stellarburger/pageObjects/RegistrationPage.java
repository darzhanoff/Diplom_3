package site.stellarburger.pageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import io.qameta.allure.Step;
import java.time.Duration;

public class RegistrationPage {
    private WebDriver driver;

    //Заголовок "Регистрация"
    private By registrationPageHeader = By.xpath(".//h2[text()='Регистрация']");
    //Поле "Имя"
    private By nameField = By.xpath(".//label[text()='Имя']/parent::div/input[@type='text']");
    //Поле "Email"
    private By emailField = By.xpath(".//label[text()='Email']/parent::div/input[@type='text']");
    //Поле "Пароль"
    private By passwordField = By.xpath(".//label[text()='Пароль']/parent::div/input[@type='password']");
    //Кнопка "Зарегистрироваться"
    private By registrationButton = By.xpath(".//button[text()='Зарегистрироваться']");
    //Ошибка при вводе некорректного пароля
    private By errorInPasswordField = By.xpath(".//p[text()='Некорректный пароль']");
    //Кнопка "Войти"
    private By enterButton = By.xpath(".//a[text()='Войти']");


    public RegistrationPage(WebDriver driver) {
        this.driver = driver;
    }

    @Step("Метод ожидания прогрузки страницы регистрации")
    public void waitForLoadRegistrationPage() {
        new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.visibilityOfElementLocated(registrationPageHeader));
    }

    @Step("Метод заполнения поля Имя")
    public void enteringNameField(String name){
        driver.findElement(nameField).sendKeys(name);
    }

    @Step("Метод заполнения поля Email")
    public void enteringEmailField(String email){
        driver.findElement(emailField).sendKeys(email);
    }

    @Step("Метод заполнения поля Пароль")
    public void enteringPasswordField(String password){
        driver.findElement(passwordField).sendKeys(password);
    }

    @Step("Клик по кнопке Зарегистрироваться")
    public void registrationButtonClick() {
        driver.findElement(registrationButton).click();
    }

    @Step("Метод видимости появления ошибки")
    public boolean errorInPasswordFieldIsVisible() {
        return driver.findElement(errorInPasswordField).isDisplayed();
    }

    @Step("Клик по кнопке Войти")
    public void enterButtonClick() {
        driver.findElement(enterButton).click();
    }
}
