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

        driver.get("https://selenium.obsqurazone.com/form-submit.php");
        WebElement firstName= driver.findElement(By.xpath("//input[@class='form-control'][1]"));
        firstName.sendKeys("Abel");
        WebElement lastName= driver.findElement(By.xpath("//input[@class='form-control' and@placeholder='Last name']"));
        lastName.sendKeys("Koshy");
        WebElement username= driver.findElement(By.xpath("//input[contains(@placeholder,'Username')]"));
        username.sendKeys("abelsam");
        WebElement city = driver.findElement(By.xpath("//input[starts-with(@id,'validationCustom03')]"));
        city.sendKeys("Pathanamathitta");
        WebElement state = driver.findElement(By.xpath("//div[@class='col-md-3 mb-3']//input[@placeholder='State']"));
        state.sendKeys("Kerala");
        WebElement zip = driver.findElement(By.xpath("//div[@class='col-md-3 mb-3']//input[@placeholder='Zip']"));
        zip.sendKeys("123456");
        WebElement checkbox = driver.findElement(By.xpath("//input[@type='checkbox' and@id='invalidCheck']"));
        checkbox.click();
        WebElement submitButton = driver.findElement(By.xpath("//button[text() ='Submit form']"));
        submitButton.click();




        // driver.close();
    }
}
