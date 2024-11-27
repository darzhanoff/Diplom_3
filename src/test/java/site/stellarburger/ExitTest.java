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

public class ExitTest {
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
    @DisplayName("Выход из личного кабинета по клику кнопки Выйти")
    @Description ("Проверка: после клика по кнопке Выйти пользователь выходит из системы")
    public void exit() {
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
        personalAccountPage.exitButtonClick();

        logInPage.waitForLoadLogInPage();
    }
}