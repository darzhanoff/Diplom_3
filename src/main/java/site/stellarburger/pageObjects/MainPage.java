package site.stellarburger.pageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import io.qameta.allure.Step;
import java.time.Duration;
import org.openqa.selenium.WebElement;

public class MainPage {
   private WebDriver driver;

   //Заголовок "Соберите бургер"
   private By mainPageHeader = By.xpath(".//h1[text()='Соберите бургер']");
   //Кнопка личный кабинет
   private By personalAccountButton = By.xpath(".//p[text()='Личный Кабинет']");
   //Кнопка "Булки"
   private By bunButton = By.xpath(".//div[contains(@class, 'tab_tab__1SPyG') and .//span[text()='Булки']]");
   //Кнопка "Соусы"
   private By sauceButton = By.xpath(".//div[contains(@class, 'tab_tab__1SPyG') and .//span[text()='Соусы']]");
   //Кнопка "Начинки"
   private By fillingButton = By.xpath(".//div[contains(@class, 'tab_tab__1SPyG') and .//span[text()='Начинки']]");
   //Кнопка "Войти в аккаунт"
   private By logInButton = By.xpath(".//button[text()='Войти в аккаунт']");
   //Кнопка "Оформить заказ" сменяет кнопку Войти в аккаунт после авторизации
   private By placeAnOrderButton = By.xpath(".//button[text()='Оформить заказ']");

   public MainPage(WebDriver driver) {
      this.driver = driver;
   }

   @Step("Метод ожидания прогрузки главной страницы")
   public void waitForLoadMainPage() {
      new WebDriverWait(driver, Duration.ofSeconds(10))
              .until(ExpectedConditions.visibilityOfElementLocated(mainPageHeader));
   }

   @Step("Клик по кнопке Личный кабинет")
   public void personalAccountButtonClick() {
      driver.findElement(personalAccountButton).click();
   }

   @Step("Клик по кнопке Войти в аккаунт")
   public void logInButtonClick() {
      driver.findElement(logInButton).click();
   }

   @Step("Метод видимости кнопки Оформить заказ")
   public boolean placeAnOrderButtonIsVisible() {
      new WebDriverWait(driver, Duration.ofSeconds(5))
              .until(ExpectedConditions.visibilityOfElementLocated(placeAnOrderButton));
      return driver.findElement(placeAnOrderButton).isDisplayed();
   }

   @Step("Клик по кнопке Булки")
   public void bunButtonClick() {
      driver.findElement(bunButton).click();
   }

   @Step("Клик по кнопке Соусы")
   public void sauceButtonClick() {
      driver.findElement(sauceButton).click();
   }

   @Step("Клик по кнопке Начинки")
   public void fillingButtonClick() {
      driver.findElement(fillingButton).click();
   }

   @Step("Метод проверки выделения кнопки Булки")
   public void bunButtonSelected() {
      WebElement bunButtonElement = driver.findElement(bunButton);
      new WebDriverWait(driver, Duration.ofSeconds(5))
              .until(ExpectedConditions.attributeContains(
                      bunButtonElement,
                      "class",
                      "tab_tab_type_current__2BEPc"));
   }

   @Step("Метод проверки выделения кнопки Соусы")
   public void sauceButtonSelected() {
      WebElement bunButtonElement = driver.findElement(sauceButton);
      new WebDriverWait(driver, Duration.ofSeconds(5))
              .until(ExpectedConditions.attributeContains(
                      bunButtonElement,
                      "class",
                      "tab_tab_type_current__2BEPc"));
   }

   @Step("Метод проверки выделения кнопки Начинки")
   public void fillingButtonSelected() {
      WebElement bunButtonElement = driver.findElement(fillingButton);
      new WebDriverWait(driver, Duration.ofSeconds(5))
              .until(ExpectedConditions.attributeContains(
                      bunButtonElement,
                      "class",
                      "tab_tab_type_current__2BEPc"));
   }
}
