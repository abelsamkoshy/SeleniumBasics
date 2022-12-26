package seleniumcommands;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.Test;

public class Locators {
    public static void main(String[] args) {
        WebDriver driver;
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().deleteAllCookies();
//        driver.get("https://selenium.obsqurazone.com/simple-form-demo.php");
//        WebElement valueA = driver.findElement(By.id("value-a"));
//        valueA.sendKeys("31");
//        WebElement valueB = driver.findElement(By.xpath("/html/body/section/div/div/div[2]/div[2]/div/div[2]/form/div[2]/input"));
//        valueB.sendKeys("45");
//        WebElement getTotal = driver.findElement(By.id("button-two"));
//        getTotal.click();
//        WebElement getTotalValue = driver.findElement(By.id("message-two"));
//        String totalPrint = getTotalValue.getText();
//        System.out.println(totalPrint);
//
//        WebElement messageField = driver.findElement(By.id("single-input-field"));
//        messageField.sendKeys("Test");
//        WebElement showElement = driver.findElement(By.id("button-one"));
//        showElement.click();
//        WebElement getMessage= driver.findElement(By.id("message-one"));
//        String myMessage =getMessage.getText();
//        System.out.println(myMessage);


        // driver.close();
    }
}
