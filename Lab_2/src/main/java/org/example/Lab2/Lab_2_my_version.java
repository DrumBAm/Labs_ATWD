package org.example.Lab2;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.time.Duration;

public class Lab_2_my_version{
    private WebDriver chromeDriver;

    @BeforeClass(alwaysRun = true)
    public  void setUp(){
        WebDriverManager.chromedriver().setup();
        ChromeOptions chromeOptions = new ChromeOptions();
        chromeOptions.addArguments("--start-fullscreen");
        chromeOptions.setImplicitWaitTimeout(Duration.ofSeconds(15));
        this.chromeDriver = new ChromeDriver(chromeOptions);
    }

    @Test(priority = 1)
    public void clickElementTest() {
        chromeDriver.get("https://www.google.com");

        // Клік по елементу (пошукове поле)
        WebElement searchBox = chromeDriver.findElement(By.name("q"));
        searchBox.click();
    }

    @Test(priority = 2)
    public void inputDataAndCheckTest() {
        // Введення даних у пошукове поле та перевірка наявності даних
        String searchText = "ntu dp";
        WebElement searchBox = chromeDriver.findElement(By.name("q"));
        searchBox.sendKeys(searchText);
        String enteredText = searchBox.getAttribute("value");
        Assert.assertEquals(enteredText, searchText, "Введені дані не співпадають з очікуваними.");
    }

    @Test(priority = 3)
    public void findElementByXPathTest() {
        // Знаходження елементу за допомогою XPath (приклад: першого результату пошуку)
        WebElement firstSearchResult = chromeDriver.findElement(By.xpath("/html/body/div[1]/div[3]/form/div[1]/div[1]/div[2]/div[4]/div[2]/div[1]/div/ul/li[1]/div/div[2]/div[1]/div[1]/span"));
        System.out.println("Перший результат пошуку: " + firstSearchResult.getText());
    }

    @Test(priority = 4)
    public void checkConditionTest() {
        // Перевірка умови (приклад: перевірка наявності тексту у першому результаті пошуку)
        WebElement firstSearchResult = chromeDriver.findElement(By.xpath("/html/body/div[1]/div[3]/form/div[1]/div[1]/div[2]/div[4]/div[2]/div[1]/div/ul/li[1]/div/div[2]/div[1]/div[1]/span"));
        Assert.assertTrue(firstSearchResult.getText().contains("ntu dp"), "Текст 'ntu dp' не знайдено у першому результаті пошуку.");
    }

    @AfterClass
    public void tearDown() {
        chromeDriver.quit();
    }
}
