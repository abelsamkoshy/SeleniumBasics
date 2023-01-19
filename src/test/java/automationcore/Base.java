package automationcore;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;

import java.io.File;
import java.io.IOException;

public class Base {
    public WebDriver driver;

    @BeforeMethod
    @Parameters({"browser","base_url"})
    public void setUP(String browserName,String url){
        driver = DriverFactory.testInitializa("chrome");
        driver.get(url);
    }
    @AfterMethod
    public void tearDown(ITestResult result) throws IOException {
        if(result.getStatus()== ITestResult.FAILURE){
            TakesScreenshot takeScreenshot=(TakesScreenshot)driver;
            File screenshot=takeScreenshot.getScreenshotAs(OutputType.FILE);
            FileUtils.copyFile(screenshot, new File("./Screenshots/"+result.getName()+".png"));
        }
        driver.quit();
    }
}
