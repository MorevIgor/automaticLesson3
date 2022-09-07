package ru.netology;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import static org.junit.jupiter.api.Assertions.assertEquals;
import io.github.bonigarcia.wdm.WebDriverManager;

import java.util.List;

public class SelTest {

    private WebDriver driver;

    @BeforeAll
    static void setUpAll() {
       // System.setProperty("webdriver.chrome.driver", "driver/win/chromedriver.exe");
        WebDriverManager.chromedriver().setup();
    }

    @BeforeEach
    void setUp() {
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--disable-dev-shm-usage");
        options.addArguments("--no-sandbox");
        options.addArguments("--headless");
        driver = new ChromeDriver(options);
    }

    @AfterEach
    void tearsDown() {
        driver.quit();
        driver = null;
    }

    @Test
    void shouldTestSomething() {
        driver.get("http://localhost:9999");
        driver.findElement(By.cssSelector("[type=\"text\"]")).sendKeys("Василий");
        driver.findElement(By.cssSelector("[type=\"tel\"]")).sendKeys("+79876543210");
        driver.findElement(By.className("checkbox__box") ).click();
        driver.findElement(By.className("button__text")).click();
        String text = driver.findElement(By.className("paragraph")).getText();
        assertEquals("Ваша заявка успешно отправлена! Наш менеджер свяжется с вами в ближайшее время.", text.trim());
    }

    @Test
    void nameLatinic(){
        driver.get("http://localhost:9999");
        List<WebElement> elements1 = driver.findElements(By.className("input__sub"));

        driver.findElement(By.cssSelector("[type=\"text\"]")).sendKeys("Vasya");
        driver.findElement(By.cssSelector("[type=\"tel\"]")).sendKeys("+79876543210");
        driver.findElement(By.className("checkbox__box") ).click();
        driver.findElement(By.className("button__text")).click();
        String text = elements1.get(0).getText();
        assertEquals("Имя и Фамилия указаные неверно. Допустимы только русские буквы, пробелы и дефисы.", text.trim());
    }

    @Test
    void telNumberNotValid() {
        driver.get("http://localhost:9999");
        List<WebElement> elements1 = driver.findElements(By.className("input__sub"));

        driver.findElement(By.cssSelector("[type=\"text\"]")).sendKeys("Василий");
        driver.findElement(By.cssSelector("[type=\"tel\"]")).sendKeys("+7987654321");
        driver.findElement(By.className("checkbox__box") ).click();
        driver.findElement(By.className("button__text")).click();
        String text = elements1.get(1).getText();
        assertEquals("Телефон указан неверно. Должно быть 11 цифр, например, +79012345678.", text.trim());
    }

    @Test
    void notClickCheckBox() {
        driver.get("http://localhost:9999");
        driver.findElement(By.cssSelector("[type=\"text\"]")).sendKeys("Василий");
        driver.findElement(By.cssSelector("[type=\"tel\"]")).sendKeys("+79876543210");
        //driver.findElement(By.className("checkbox__box") ).click();
        driver.findElement(By.className("button__text")).click();
        String text = driver.findElement(By.className("input_invalid")).getText();
        assertEquals("Я соглашаюсь с условиями обработки и использования моих персональных данных и разрешаю сделать запрос в бюро кредитных историй", text.trim());
    }
}