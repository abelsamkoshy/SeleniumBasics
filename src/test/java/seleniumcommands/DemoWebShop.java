package seleniumcommands;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class DemoWebShop {
    public static void main(String[] args) {
        WebDriver driver;
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().deleteAllCookies();
        driver.get("https://demowebshop.tricentis.com/");
        WebElement loginBotton = driver.findElement(By.className("ico-login"));
        loginBotton.click();
        WebElement email = driver.findElement(By.name("Email"));
        email.sendKeys("abel.sam.koshy1947@gmail.com");
        WebElement password = driver.findElement(By.name("Password"));
        password.sendKeys("123456");
        WebElement login = driver.findElement(By.xpath("/html/body/div[4]/div[1]/div[4]/div[2]/div/div[2]/div[1]/div[2]/div[2]/form/div[5]/input"));
        login.click();
        driver.close();
    }
}
