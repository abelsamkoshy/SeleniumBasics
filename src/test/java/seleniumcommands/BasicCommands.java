package seleniumcommands;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;

public class BasicCommands {
    public static void main(String[] args) {
        //System.setProperty("webdriver.chrome.driver","\"C:\\Users\\abels\\IdeaProjects\\seleniumbasics\\src\\test\\resources\\driverfiles\\chromedriver.exe");
        WebDriver driver = new ChromeDriver();
        driver.get("https://selenium.obsqurazone.com/index.php");
        driver.manage().window().maximize();
        driver.close();

        // WebDriver driver2 = new EdgeDriver();
        // WebDriver driver3 = new OperaDriver();


    }
}
