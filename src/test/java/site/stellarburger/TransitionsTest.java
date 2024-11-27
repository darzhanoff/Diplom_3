package site.stellarburger;

import io.qameta.allure.Description;
import io.qameta.allure.junit4.DisplayName;
import io.restassured.response.ValidatableResponse;
import org.junit.Test;
import org.junit.After;
import org.junit.Before;
import org.openqa.selenium.WebDriver;

import site.stellarburger.pageObjects.LogInPage;
import site.stellarburger.pageObjects.MainPage;
import site.stellarburger.pageObjects.PersonalAccountPage;
import site.stellarburger.user.User;
import site.stellarburger.user.UserClient;
import site.stellarburger.user.UserRandomizer;

public class TransitionsTest {
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
    @DisplayName("Переход в личный кабинет по клику на кнопку «Личный кабинет»")
    @Description ("Проверка: переход в личный кабинет по кнопке открывает Личный кабинет")
    public void transitionToPersonalAccount() {
        MainPage mainPage = new MainPage(driver);
        LogInPage logInPage = new LogInPage(driver);
        PersonalAccountPage personalAccountPage = new PersonalAccountPage(driver);

        mainPage.waitForLoadMainPage();
        mainPage.logInButtonClick();

        logInPage.waitForLoadLogInPage();
        logInPage.enteringEmailField(user.getEmail());
        logInPage.enteringPasswordField(user.getPassword());
        logInPage.enterButtonClick();

        mainPage.waitForLoadMainPage();
        mainPage.personalAccountButtonClick();

        personalAccountPage.waitForLoadPersonalAccountPage();
    }

    @Test
    @DisplayName("Переход в конструктор по клику на Конструктор из личного кабинета")
    @Description ("Проверка: переход в конструктор по кнопке из Личного кабинета работает")
    public void transitionToConstructor() {
        MainPage mainPage = new MainPage(driver);
        LogInPage logInPage = new LogInPage(driver);
        PersonalAccountPage personalAccountPage = new PersonalAccountPage(driver);

        mainPage.waitForLoadMainPage();
        mainPage.logInButtonClick();

        logInPage.waitForLoadLogInPage();
        logInPage.enteringEmailField(user.getEmail());
        logInPage.enteringPasswordField(user.getPassword());
        logInPage.enterButtonClick();

        mainPage.waitForLoadMainPage();
        mainPage.personalAccountButtonClick();

        personalAccountPage.waitForLoadPersonalAccountPage();
        personalAccountPage.constructorButtonClick();

        mainPage.waitForLoadMainPage();
    }

    @Test
    @DisplayName("Переход на главную страницу по клику на логотип из личного кабинета")
    @Description ("Проверка: переход на главную страницу по логотипу из Личного кабинета работает")
    public void transitionToMainPageByClickingLogo() {
        MainPage mainPage = new MainPage(driver);
        LogInPage logInPage = new LogInPage(driver);
        PersonalAccountPage personalAccountPage = new PersonalAccountPage(driver);

        mainPage.waitForLoadMainPage();
        mainPage.logInButtonClick();

        logInPage.waitForLoadLogInPage();
        logInPage.enteringEmailField(user.getEmail());
        logInPage.enteringPasswordField(user.getPassword());
        logInPage.enterButtonClick();

        mainPage.waitForLoadMainPage();
        mainPage.personalAccountButtonClick();

        personalAccountPage.waitForLoadPersonalAccountPage();
        personalAccountPage.stellarBurgersLogoClick();

        mainPage.waitForLoadMainPage();
    }
}
