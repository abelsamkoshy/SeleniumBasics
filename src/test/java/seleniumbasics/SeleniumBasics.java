package seleniumbasics;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class SeleniumBasics {
    WebDriver driver;

    public void testInitialize(String browser) {
        if (browser.equals("chrome")) {
            driver = new ChromeDriver();
        } else if (browser.equals("Edge")) {
            driver = new EdgeDriver();
        } else if (browser.equals("Firefox")) {
            driver = new FirefoxDriver();
        } else {
            throw new RuntimeException("Invalid Browser");
        }
        driver.manage().window().maximize();
        driver.manage().deleteAllCookies();
    }
    @BeforeMethod
    public void setUp(){
        testInitialize("chrome");
    }
    @AfterMethod
    public void tearDown(){
        driver.close();
    }
    @Test
    public void TC_001_verifyObsquraTitle(){
        driver.get("https://selenium.obsqurazone.com/index.php");
        String actualTitle = driver.getTitle();
        String expectedTitle="Obsqura Testing";
        Assert.assertEquals(actualTitle,expectedTitle,"Invalid Title Found");
    }
    @Test
    public void TC_002_verifyTwoInputFieldMessage(){
        driver.get("https://selenium.obsqurazone.com/simple-form-demo.php");
        WebElement value1 = driver.findElement(By.id("value-a"));
        value1.sendKeys("88");
        WebElement value2 = driver.findElement(By.id("value-b"));
        value2.sendKeys("22");
        WebElement getTotal = driver.findElement(By.id("button-two"));
        getTotal.click();
        WebElement total = driver.findElement(By.id("message-two"));
        String actualTotal = total.getText();
        String expectedTotal ="Total A + B : 100";
        Assert.assertEquals(actualTotal,expectedTotal,"Invalid Total");
    }
    @Test
    public void TC_003_verifySingleInputFieldMessage(){
        driver.get("https://selenium.obsqurazone.com/simple-form-demo.php");
        WebElement message = driver.findElement(By.id("single-input-field"));
        message.sendKeys("Check Message");
        WebElement showMessageButton = driver.findElement(By.id("button-one"));
        showMessageButton.click();
        WebElement showMessage = driver.findElement(By.id("message-one"));
        String showMessageActual = showMessage.getText();
        String showMessageExpected ="Your Message : Check Message";
        Assert.assertEquals(showMessageExpected,showMessageActual,"Invalid Message");

    }

}
