package site.stellarburger;

import io.qameta.allure.Description;
import io.qameta.allure.junit4.DisplayName;
import io.restassured.response.ValidatableResponse;
import org.junit.Test;
import org.junit.After;
import org.junit.Before;
import org.junit.Assert;
import org.openqa.selenium.WebDriver;

import site.stellarburger.pageObjects.LogInPage;
import site.stellarburger.pageObjects.MainPage;
import site.stellarburger.pageObjects.RegistrationPage;
import site.stellarburger.pageObjects.PasswordRecoveryPage;
import site.stellarburger.user.User;
import site.stellarburger.user.UserClient;
import site.stellarburger.user.UserRandomizer;

public class LogInTest {
    private WebDriver driver;
    private UserClient userClient;
    private User user;
    private String accessToken;

    @Before
    public void setUp() {
        driver = WebDriverCreator.createWebDriver();
        driver.get("https://stellarburgers.nomoreparties.site/");
        userClient = new UserClient();
        user = UserRandomizer.randomUser();
        ValidatableResponse response = userClient.createUser(user);
        String rawToken = (String) response.extract().path("accessToken");
        accessToken = rawToken.replaceFirst("Bearer ", "");
    }

    @After
    public void tearDown() {
        if (accessToken != null) {
            userClient.deleteUser(accessToken);
        }
        driver.quit();
    }

    @Test
    @DisplayName("Вход по кнопке «Войти в аккаунт» на главной странице")
    @Description ("Проверка: пользователь залогинился по кнопке Войти на главной странице")
    public void logInByLoginButton() {
        MainPage mainPage = new MainPage(driver);
        LogInPage logInPage = new LogInPage(driver);

        mainPage.waitForLoadMainPage();
        mainPage.logInButtonClick();

        logInPage.waitForLoadLogInPage();
        logInPage.enteringEmailField(user.getEmail());
        logInPage.enteringPasswordField(user.getPassword());
        logInPage.enterButtonClick();

        Assert.assertTrue("Кнопка 'Оформить заказ' не отображается после входа",
                mainPage.placeAnOrderButtonIsVisible());
    }

    @Test
    @DisplayName("Вход по кнопке «Личный кабинет» на главной странице")
    @Description ("Проверка: пользователь залогинился по кнопке Личный кабинет на главной странице")
    public void logInByPersonalAccountButton() {
        MainPage mainPage = new MainPage(driver);
        LogInPage logInPage = new LogInPage(driver);

        mainPage.waitForLoadMainPage();
        mainPage.personalAccountButtonClick();

        logInPage.waitForLoadLogInPage();
        logInPage.enteringEmailField(user.getEmail());
        logInPage.enteringPasswordField(user.getPassword());
        logInPage.enterButtonClick();

        Assert.assertTrue("Кнопка 'Оформить заказ' не отображается после входа",
                mainPage.placeAnOrderButtonIsVisible());
    }

    @Test
    @DisplayName("Вход через кнопку 'Войти' в форме регистрации")
    @Description ("Проверка: пользователь залогинился по кнопке Войти в форме регистрации")
    public void logInButtonInRegistrationPage() {
        MainPage mainPage = new MainPage(driver);
        RegistrationPage registrationPage = new RegistrationPage(driver);
        LogInPage logInPage = new LogInPage(driver);

        mainPage.waitForLoadMainPage();
        mainPage.personalAccountButtonClick();

        logInPage.registerButtonClick();

        registrationPage.waitForLoadRegistrationPage();
        registrationPage.enterButtonClick();

        logInPage.waitForLoadLogInPage();
        logInPage.enteringEmailField(user.getEmail());
        logInPage.enteringPasswordField(user.getPassword());
        logInPage.enterButtonClick();

        Assert.assertTrue("Кнопка 'Оформить заказ' не отображается после входа",
                mainPage.placeAnOrderButtonIsVisible());
    }

    @Test
    @DisplayName("Вход через кнопку 'Войти' в форме восстановления пароля")
    @Description ("Проверка: пользователь залогинился по кнопке Войти в форме восстановления пароля")
    public void logInButtonInPasswordRecoveryPage() {
        MainPage mainPage = new MainPage(driver);
        PasswordRecoveryPage passwordRecoveryPage = new PasswordRecoveryPage(driver);
        LogInPage logInPage = new LogInPage(driver);

        mainPage.waitForLoadMainPage();
        mainPage.personalAccountButtonClick();

        logInPage.passwordRecoverButtonClick();

        passwordRecoveryPage.waitForLoadPasswordRecoveryPage();
        passwordRecoveryPage.enterButtonClick();

        logInPage.waitForLoadLogInPage();
        logInPage.enteringEmailField(user.getEmail());
        logInPage.enteringPasswordField(user.getPassword());
        logInPage.enterButtonClick();

        Assert.assertTrue("Кнопка 'Оформить заказ' не отображается после входа",
                mainPage.placeAnOrderButtonIsVisible());
    }
}
