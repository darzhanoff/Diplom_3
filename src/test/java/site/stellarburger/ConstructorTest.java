package site.stellarburger;

import io.qameta.allure.Description;
import io.qameta.allure.junit4.DisplayName;
import org.junit.Test;
import org.junit.After;
import org.junit.Before;
import org.openqa.selenium.WebDriver;

import site.stellarburger.pageObjects.MainPage;

public class ConstructorTest {
    private WebDriver driver;

    @Before
    public void setUp() {
        driver = WebDriverCreator.createWebDriver();
        driver.get("https://stellarburgers.nomoreparties.site");
    }

    @After
    public void tearDown() {
        driver.quit();
    }

    @Test
    @DisplayName("Переход к разделу Булки")
    @Description ("Проверка: переход к разделу Булки работает")
    public void switchToBunTab() {
        MainPage mainPage = new MainPage(driver);
        mainPage.waitForLoadMainPage();
        mainPage.fillingButtonClick();
        mainPage.bunButtonClick();
        mainPage.bunButtonSelected();
    }

    @Test
    @DisplayName("Переход к разделу Соусы")
    @Description ("Проверка: переход к разделу Соусы работает")
    public void switchToSauceTab() {
        MainPage mainPage = new MainPage(driver);
        mainPage.waitForLoadMainPage();
        mainPage.sauceButtonClick();
        mainPage.sauceButtonSelected();
    }

    @Test
    @DisplayName("Переход к разделу Начинки")
    @Description ("Проверка: переход к разделу Начинки работает")
    public void switchToFillingTab() {
        MainPage mainPage = new MainPage(driver);
        mainPage.waitForLoadMainPage();
        mainPage.fillingButtonClick();
        mainPage.fillingButtonSelected();
    }
}
