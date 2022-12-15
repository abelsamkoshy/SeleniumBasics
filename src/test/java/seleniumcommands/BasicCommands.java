package seleniumcommands;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class BasicCommands {
    public static void main(String[] args) {
        WebDriver driver;
        //System.setProperty("webdriver.chrome.driver","\"C:\\Users\\abels\\IdeaProjects\\seleniumbasics\\src\\test\\resources\\driverfiles\\chromedriver.exe");
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().deleteAllCookies();
        driver.get("https://selenium.obsqurazone.com/index.php");
        String title = driver.getTitle();
        System.out.println(title);
        String sourcecode = driver.getPageSource();
        System.out.println(sourcecode);
        String currentUrl = driver.getCurrentUrl();
        System.out.println(currentUrl);
        driver.close();
//
//        /**launching edge browser**/
//        driver = new EdgeDriver();
//        driver.get("https://selenium.obsqurazone.com/index.php");
//        driver.manage().window().maximize();
//        driver.close();
//
//        /**launching firefox browser**/
//        driver = new FirefoxDriver();
//        driver.get("https://selenium.obsqurazone.com/index.php");
//        driver.manage().window().maximize();
//        driver.close();


    }
}
