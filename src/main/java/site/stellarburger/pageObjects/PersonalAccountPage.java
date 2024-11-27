package site.stellarburger.pageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import io.qameta.allure.Step;
import java.time.Duration;

public class PersonalAccountPage {
    private WebDriver driver;

    //Надпись в личном кабинете
    private By personalAccPageInscription = By.xpath(".//p[contains(text(), 'В этом разделе вы можете изменить')]");
    //Кнопка "Конструктор"
    private By constructorButton = By.xpath(".//p[text()='Конструктор']");
    //Логотип StellarBurgers
    private By stellarBurgersLogo = By.xpath(".//div[contains(@class, 'logo__2D0X2')]");
    //Кнопка "Выход"
    private By exitButton = By.xpath(".//button[text()='Выход']");

    public PersonalAccountPage(WebDriver driver) {
        this.driver = driver;
    }

    @Step("Метод ожидания прогрузки личного кабинета")
    public void waitForLoadPersonalAccountPage() {
        new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.visibilityOfElementLocated(personalAccPageInscription));
    }

    @Step("Клик по кнопке Конструктора")
    public void constructorButtonClick() {
        driver.findElement(constructorButton).click();
    }

    @Step("Клик по логотипу StellarBurgers")
    public void stellarBurgersLogoClick() {
        driver.findElement(stellarBurgersLogo).click();
    }

    @Step("Клик по кнопке Выход")
    public void exitButtonClick() {
        driver.findElement(exitButton).click();
    }
}
