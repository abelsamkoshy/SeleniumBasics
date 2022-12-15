package seleniumcommands;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class MercuryTours {
    public static void main(String[] args) {
        WebDriver driver;
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().deleteAllCookies();
        driver.get("https://demo.guru99.com/test/newtours/");
       // WebElement register1 = driver.findElement(By.linkText("REGISTER"));
        WebElement register1= driver.findElement(By.partialLinkText("REGI "));
        register1.click();
        WebElement firstName = driver.findElement(By.name("firstName"));
        firstName.sendKeys("Abel");
        WebElement lastName = driver.findElement(By.name("lastName"));
        lastName.sendKeys("Sam");
        WebElement phoneNumber = driver.findElement(By.name("phone"));
        phoneNumber.sendKeys("1234556");
        WebElement email = driver.findElement(By.id("userName"));
        email.sendKeys("abel@gmail.com");

        WebElement address = driver.findElement(By.xpath("/html/body/div[2]/table/tbody/tr/td[2]/table/tbody/tr[4]/td/table/tbody/tr/td[2]/table/tbody/tr[5]/td/form/table/tbody/tr[7]/td[2]/input"));
        address.sendKeys("Pathanamthitta");
        WebElement city = driver.findElement(By.xpath("/html/body/div[2]/table/tbody/tr/td[2]/table/tbody/tr[4]/td/table/tbody/tr/td[2]/table/tbody/tr[5]/td/form/table/tbody/tr[8]/td[2]/input"));
        city.sendKeys("Pathanamthitta");
        WebElement state = driver.findElement(By.xpath("/html/body/div[2]/table/tbody/tr/td[2]/table/tbody/tr[4]/td/table/tbody/tr/td[2]/table/tbody/tr[5]/td/form/table/tbody/tr[9]/td[2]/input"));
        state.sendKeys("Kerala");
        WebElement pincode = driver.findElement(By.xpath("/html/body/div[2]/table/tbody/tr/td[2]/table/tbody/tr[4]/td/table/tbody/tr/td[2]/table/tbody/tr[5]/td/form/table/tbody/tr[10]/td[2]/input"));
        pincode.sendKeys("675445");

        WebElement username = driver.findElement(By.cssSelector("#email"));
        username.sendKeys("abel@gmail.com");
        WebElement password = driver.findElement(By.cssSelector("body > div:nth-child(5) > table > tbody > tr > td:nth-child(2) > table > tbody > tr:nth-child(4) > td > table > tbody > tr > td:nth-child(2) > table > tbody > tr:nth-child(5) > td > form > table > tbody > tr:nth-child(14) > td:nth-child(2) > input[type=password]"));
        password.sendKeys("asdf");
        WebElement confirmPassword = driver.findElement(By.cssSelector("body > div:nth-child(5) > table > tbody > tr > td:nth-child(2) > table > tbody > tr:nth-child(4) > td > table > tbody > tr > td:nth-child(2) > table > tbody > tr:nth-child(5) > td > form > table > tbody > tr:nth-child(15) > td:nth-child(2) > input[type=password]"));
        confirmPassword.sendKeys("asdf");
        WebElement register2= driver.findElement(By.name("submit"));
        register2.click();
        //driver.close();
    }
}
