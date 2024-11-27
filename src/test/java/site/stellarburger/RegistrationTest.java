package site.stellarburger;

import io.qameta.allure.Description;
import io.qameta.allure.junit4.DisplayName;
import io.restassured.response.ValidatableResponse;
import org.junit.Test;
import org.junit.After;
import org.junit.Before;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import site.stellarburger.pageObjects.LogInPage;
import site.stellarburger.pageObjects.RegistrationPage;
import site.stellarburger.user.UserAuthInfo;
import site.stellarburger.user.User;
import site.stellarburger.user.UserClient;
import site.stellarburger.user.UserRandomizer;


public class RegistrationTest {
    private WebDriver driver;
    private UserClient userClient;
    private User user;
    private String accessToken;

    @Before
    public void setUp() {
        driver = WebDriverCreator.createWebDriver();
        driver.get("https://stellarburgers.nomoreparties.site/register");
        userClient = new UserClient();
        user = UserRandomizer.randomUser();
    }

    @After
    public void tearDown() {
        if (accessToken != null) {
            userClient.deleteUser(accessToken);
        }
            driver.quit();
    }

    @Test
    @DisplayName("Успешная регистрация пользователя")
    @Description ("Проверка: пользователь успешно зарегистрирован")
    public void successfulRegistration() {
        RegistrationPage registrationPage = new RegistrationPage(driver);
        LogInPage logInPage = new LogInPage(driver);
        registrationPage.waitForLoadRegistrationPage();
        registrationPage.enteringNameField(user.getName());
        registrationPage.enteringEmailField(user.getEmail());
        registrationPage.enteringPasswordField(user.getPassword());
        registrationPage.registrationButtonClick();

        logInPage.waitForLoadLogInPage();
        new WebDriverWait(driver, Duration.ofSeconds(5)).until(
                ExpectedConditions.urlToBe("https://stellarburgers.nomoreparties.site/login")
        );
        assertEquals("Неверный URL после регистрации",
                "https://stellarburgers.nomoreparties.site/login",
                driver.getCurrentUrl()
        );
        ValidatableResponse loginResponse = userClient.logIn(new UserAuthInfo(user.getEmail(), user.getPassword()));
        int statusCode = loginResponse.extract().statusCode();
        assertEquals("Пользователь не был создан", 200, statusCode);
        accessToken = loginResponse.extract().path("accessToken");
    }

    @Test
    @DisplayName("Регистрация с коротким паролем меньше 6 символов")
    @Description ("Проверка: пользователь не зарегистрирован, если пароль меньше 6 символов")
    public void registrationShortPassword() {
        RegistrationPage registrationPage = new RegistrationPage(driver);
        registrationPage.waitForLoadRegistrationPage();
        registrationPage.enteringNameField(user.getName());
        registrationPage.enteringEmailField(user.getEmail());
        registrationPage.enteringPasswordField("12345");
        registrationPage.registrationButtonClick();

        assertTrue("Ошибка для короткого пароля не отображается", registrationPage.errorInPasswordFieldIsVisible());
    }
}

