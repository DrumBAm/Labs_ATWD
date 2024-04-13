package org.example;

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
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.time.Duration;

public class Lab_2 {
    private WebDriver chromeDriver;

    private static final String baseUrl = "https://www.nmu.org.ua/ua/";

    @BeforeClass(alwaysRun = true)
    public  void setUp(){
        WebDriverManager.chromedriver().setup();
        ChromeOptions chromeOptions = new ChromeOptions();
        chromeOptions.addArguments("--start-fullscreen");
        chromeOptions.setImplicitWaitTimeout(Duration.ofSeconds(15));
        this.chromeDriver = new ChromeDriver(chromeOptions);
    }

    @BeforeMethod
    public  void preconditions(){
        chromeDriver.get(baseUrl);
    }

    @AfterClass(alwaysRun = true)
    public void tearDown(){chromeDriver.quit();}

    @Test
    public void testHeaderExist(){
        WebElement header = chromeDriver.findElement(By.id("heder"));
        Assert.assertNotNull(header, "Header element not found on the page.");
        Assert.assertNotNull(header);
    }

    @Test
    public void testClickOnForStudent(){
        WebElement forStudentButton = chromeDriver.findElement(By.xpath("/html/body/center/div[4]/div/div[1]/ul/li[1]/a/span"));
        Assert.assertNotNull(forStudentButton);
        forStudentButton.click();
        Assert.assertNotEquals(chromeDriver.getCurrentUrl(), baseUrl);
    }

    @Test
    public void testSearchFieldOnFroStudentPage(){
        String studentPageUrl = "search";
        chromeDriver.get(baseUrl + studentPageUrl);
        WebElement searchField = chromeDriver.findElement(By.name("search"));
        Assert.assertNotNull(searchField);
        System.out.println(String.format("Name attribute: %s", searchField.getAttribute("name")) +
                String.format("\nID attribute: %s", searchField.getAttribute("id")) +
                String.format("\nType attribute: %s", searchField.getAttribute("type")) +
                String.format("\nValue attribute: %s",searchField.getAttribute("value")) +
                String.format("\nPosition: (%d;%d)",searchField.getLocation().x, searchField.getLocation().y) +
                String.format("\nSize: (%d;%d)",searchField.getSize().height, searchField.getSize().width)

        );
        String inputValue = "I need info";
        searchField.sendKeys(inputValue);
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        Assert.assertEquals(searchField.getAttribute("value"), inputValue);
        searchField.sendKeys(Keys.ENTER);
        Assert.assertNotEquals(chromeDriver.getCurrentUrl(), studentPageUrl);
    }


    @Test
    public void testSlider() {
        chromeDriver.get(baseUrl);

        Duration time = Duration.ofSeconds(2);

        WebDriverWait wait = new WebDriverWait(chromeDriver, time);

        WebElement nextButton = wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("next")));
        WebElement previousButton = wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("prev")));

        while (!nextButton.getAttribute("class").contains("disabled")){
            nextButton.click();
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        while (!previousButton.getAttribute("class").contains("disabled")){
            previousButton.click();
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        wait.until(ExpectedConditions.attributeContains(previousButton, "class", "disabled"));
        wait.until(ExpectedConditions.not(ExpectedConditions.attributeContains(nextButton, "class", "disabled")));
    }

}
