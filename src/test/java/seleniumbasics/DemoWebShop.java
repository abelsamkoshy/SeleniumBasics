package seleniumbasics;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.*;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class DemoWebShop {
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
    @BeforeMethod(alwaysRun = true)
    @Parameters({"browser","base_url"})
    public void setUp(String browserName,String url) {
        testInitialize(browserName);
        driver.get(url);
    }

    @AfterMethod(alwaysRun = true)
    public void tearDown(ITestResult result) throws IOException {
        if(result.getStatus()==ITestResult.FAILURE){
            TakesScreenshot takeScreenshot=(TakesScreenshot)driver;
            File screenshot=takeScreenshot.getScreenshotAs(OutputType.FILE);
            FileUtils.copyFile(screenshot, new File("./Screenshots/"+result.getName()+".png"));
        }
        driver.close();
        // driver.quit();
    }
    @Test(priority = 1,enabled = true,description = "Verify Titl for DemoWebShop",groups = {"Regression"})
    public void TC_001_verifyDemoWebShopTitle() throws IOException {
        String actualTitle = driver.getTitle();
        List<ArrayList<String>> data = ExcelUtility.excelDataReader("\\src\\test\\resources\\TestData.xlsx", "HomePage");
        String expectedTitle = data.get(0).get(1);
        Assert.assertEquals(actualTitle, expectedTitle, "Invalid Title found");
    }
    @Test(priority = 2,enabled = true,description = "Verify Titl for DemoWebShop",groups = {"Regression"})
    public void TC_002_verifyLogin(){
        WebElement login1= driver.findElement(By.xpath("//a[@class='ico-login']"));
        login1.click();
        String mail = "abelSam@gmail.com";
        WebElement email= driver.findElement(By.xpath("//input[@id='Email']"));
        email.sendKeys(mail);
        WebElement password = driver.findElement(By.xpath("//input[@id='Password']"));
        password.sendKeys("Abel@123");
        WebElement loginButton = driver.findElement(By.xpath("//input[@class='button-1 login-button']"));
        loginButton.click();
        WebElement userAccount= driver.findElement(By.xpath("(//a[@class='account'])[1]"));
        String actMail= userAccount.getText();
        Assert.assertEquals(mail,actMail,"Login Failed");
    }

    @Test(priority = 3,enabled = true,description = "Verify Titl for DemoWebShop",groups = {"Smoke"})
    public void TC_003_verifyRegistration() throws IOException {
        WebElement reg1= driver.findElement(By.xpath("//a[@class='ico-register']"));
        reg1.click();
        List<WebElement> gender= driver.findElements(By.xpath("//input[@name='Gender']"));
        selectGender("M",gender);
        List<ArrayList<String>> data = ExcelUtility.excelDataReader("\\src\\test\\resources\\TestData.xlsx", "Registration");
        WebElement firstName=driver.findElement(By.id("FirstName"));
        String fName= data.get(0).get(1);
        firstName.sendKeys(fName);
        WebElement lastName=driver.findElement(By.id("LastName"));
        String lName= data.get(1).get(1);
        lastName.sendKeys(lName);
        WebElement emailField=driver.findElement(By.id("Email"));
        String email= RandomDataUtility.getRandomEmail();
        emailField.sendKeys(email);
        WebElement passwordField=driver.findElement(By.id("Password"));
        String pword= data.get(3).get(1);
        passwordField.sendKeys(pword);
        WebElement passwordConfirm=driver.findElement(By.id("ConfirmPassword"));
        passwordConfirm.sendKeys(pword);
        WebElement register=driver.findElement(By.id("register-button"));
        register.click();
        WebElement userAccount=driver.findElement(By.xpath("//div[@class='header-links']//a[@class='account']"));
        String actualEmail=userAccount.getText();
        Assert.assertEquals(actualEmail,email,"Registration failed");
    }

    public void selectGender(String gen,List<WebElement> gender){
        for(int i= 0; i< gender.size();i++){
            String genderValue= gender.get(i).getAttribute("value");
            if(genderValue.equals(gen)){
                gender.get(i).click();

            }
        }

    }
    @Test(dataProvider = "Credentials",priority = 4,enabled = true,description = "Verify Titl for DemoWebShop",groups = {"Regression","Sanity"})
    public void TC_004_verifyInvalidLogin(String userName, String pword){
        WebElement login1= driver.findElement(By.xpath("//a[@class='ico-login']"));
        login1.click();
        String mail = userName;
        WebElement email= driver.findElement(By.xpath("//input[@id='Email']"));
        email.sendKeys(mail);
        WebElement password = driver.findElement(By.xpath("//input[@id='Password']"));
        password.sendKeys(pword);
        WebElement loginButton = driver.findElement(By.xpath("//input[@class='button-1 login-button']"));
        loginButton.click();
        WebElement error = driver.findElement(By.xpath("//div[@class='validation-summary-errors']//span"));
        String actMsg=error.getText();
        String expMsg= "Login was unsuccessful. Please correct the errors and try again.";
        Assert.assertEquals(actMsg,expMsg,"error message not found");
    }
    @DataProvider(name = "Credentials")
    public Object[][] userCredentials(){
        Object[][] data= {{"abelS@gmail.com","Abel@123"},{"abelSam@gmail.com","Abel@1234"},{"abelS@gmail.com","Abel"}};
        return data;
    }
    @Test(priority = 5,enabled = true,description = "Verify Titl for DemoWebShop",groups = {"Sanity"})
    public void TC_005_verifyRegistrationDataRandomGeneration(){
        WebElement reg1= driver.findElement(By.xpath("//a[@class='ico-register']"));
        reg1.click();
        String fName= RandomDataUtility.getfName();
        String lName = RandomDataUtility.getlName();
        String email= RandomDataUtility.getRandomEmail();
        String pWord = fName+"@123";
        List<WebElement> gender= driver.findElements(By.xpath("//input[@name='Gender']"));
        selectGender("M",gender);
        WebElement firstName=driver.findElement(By.id("FirstName"));
        firstName.sendKeys(fName);
        WebElement lastName=driver.findElement(By.id("LastName"));
        lastName.sendKeys(lName);
        WebElement emailField=driver.findElement(By.id("Email"));
        emailField.sendKeys(email);
        WebElement passwordField=driver.findElement(By.id("Password"));
        passwordField.sendKeys(pWord);
        WebElement passwordConfirm=driver.findElement(By.id("ConfirmPassword"));
        passwordConfirm.sendKeys(pWord);
        WebElement register=driver.findElement(By.id("register-button"));
        register.click();
        WebElement userAccount=driver.findElement(By.xpath("//div[@class='header-links']//a[@class='account']"));
        String actualEmail=userAccount.getText();
        Assert.assertEquals(actualEmail,email,"Registration failed");
    }
    @Test(dataProvider = "Login",priority = 5,enabled = true,description = "Verify Titl for DemoWebShop",groups = {"Sanity"})
    public void TC_006_verifyLoginByParameterization(String userName, String pword){
        WebElement login1= driver.findElement(By.xpath("//a[@class='ico-login']"));
        login1.click();
        String mail = userName;
        WebElement email= driver.findElement(By.xpath("//input[@id='Email']"));
        email.sendKeys(mail);
        WebElement password = driver.findElement(By.xpath("//input[@id='Password']"));
        password.sendKeys(pword);
        WebElement loginButton = driver.findElement(By.xpath("//input[@class='button-1 login-button']"));
        loginButton.click();
        WebElement userAccount= driver.findElement(By.xpath("(//a[@class='account'])[1]"));
        String actMail= userAccount.getText();
        Assert.assertEquals(mail,actMail,"Login Failed");
    }
    @DataProvider(name = "Login")
    public Object[][] login(){
        Object[][] loginData={{"abelSam@gmail.com","Abel@123"}};
        return loginData;
    }
    @Test(priority = 5,enabled = true,description = "Verify Titl for DemoWebShop",groups = {"Smoke"})
    @Parameters({"userName","pword"})
    public void TC_007_verifyLoginByParameteriztion(String uName, String password){
        WebElement login1= driver.findElement(By.xpath("//a[@class='ico-login']"));
        login1.click();
        WebElement email= driver.findElement(By.xpath("//input[@id='Email']"));
        email.sendKeys(uName);
        WebElement password1 = driver.findElement(By.xpath("//input[@id='Password']"));
        password1.sendKeys(password);
        WebElement loginButton = driver.findElement(By.xpath("//input[@class='button-1 login-button']"));
        loginButton.click();
        WebElement userAccount= driver.findElement(By.xpath("(//a[@class='account'])[1]"));
        String actMail= userAccount.getText();
        Assert.assertEquals(uName,actMail,"Login Failed");
    }
}
