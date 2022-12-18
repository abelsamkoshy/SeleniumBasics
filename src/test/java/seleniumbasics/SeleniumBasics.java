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
       // driver.close();
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
    @Test
    public void TC_004_verifyValidationMessage() {
        driver.get("https://selenium.obsqurazone.com/form-submit.php");
        WebElement submitButton1 = driver.findElement(By.xpath("//button[text() ='Submit form']"));
        submitButton1.click();
        WebElement firstNameValidation = driver.findElement(By.xpath("(//div[@class ='invalid-feedback'])[1]"));
        String firstValMsg = firstNameValidation.getText();
        String firstNameExpected = "Please enter First name.";
        Assert.assertEquals(firstNameExpected, firstValMsg, "Invalid first name validation Message");
        WebElement lastNameValidation = driver.findElement(By.xpath("(//div[contains(@class,'invalid-feedback')])[2]"));
        String actualLastNameValidation = lastNameValidation.getText();
        String expectedLastNameValidation = "Please enter Last name.";
        Assert.assertEquals(actualLastNameValidation, expectedLastNameValidation, "Invalid last name validation Message");
        WebElement usernameValidation = driver.findElement(By.xpath("(//div[@class='invalid-feedback'])[3]"));
        String actualUsernameValidation = usernameValidation.getText();
        String expectedUsernameValidation = "Please choose a username.";
        Assert.assertEquals(actualUsernameValidation, expectedUsernameValidation, "Invalid username validation Message");
        WebElement cityValidation = driver.findElement(By.xpath("/html/body/section/div/div/div[2]/div/div/div[2]/form/div[2]/div[1]/div[1]"));
        String actualCityValidation= cityValidation.getText();
        String expectedCityValidation ="Please provide a valid city.";
        Assert.assertEquals(actualCityValidation,expectedCityValidation,"Invalid city validation Message");
        WebElement stateValidation = driver.findElement(By.xpath("/html/body/section/div/div/div[2]/div/div/div[2]/form/div[2]/div[2]/div[1]"));
        String actualStateValidation = stateValidation.getText();
        String expectedStateValidation ="Please provide a valid state.";
        Assert.assertEquals(actualStateValidation,expectedStateValidation,"Invalid state validation Message");
        WebElement zipValidation= driver.findElement(By.xpath("/html/body/section/div/div/div[2]/div/div/div[2]/form/div[2]/div[3]/div[1]"));
        String actualZipValidation= zipValidation.getText();
        String expectedZipValidation="Please provide a valid zip.";
        Assert.assertEquals(actualZipValidation,expectedZipValidation,"Invalid zip validation Message");
        WebElement agreeValidation = driver.findElement(By.cssSelector("body > section > div > div > div.col-lg-9.col-md-9.col-sm-12.col-xs-12 > div > div > div.card-body > form > div.form-group > div > div"));
        String actualAgreeValidation = agreeValidation.getText();
        String expectedAgreeValidation="You must agree before submitting.";
        Assert.assertEquals(actualAgreeValidation,expectedAgreeValidation,"Invalid agree validation Message");
    }
    @Test
    public void TC_005_submitFormNOZip(){
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
        //zip.sendKeys("123");
        WebElement checkbox = driver.findElement(By.xpath("//input[@type='checkbox' and@id='invalidCheck']"));
        checkbox.click();
        WebElement submitButton = driver.findElement(By.xpath("//button[text() ='Submit form']"));
        submitButton.click();

        WebElement zipValidation= driver.findElement(By.xpath("/html/body/section/div/div/div[2]/div/div/div[2]/form/div[2]/div[3]/div[1]"));
        String actualZipValidation=zipValidation.getText();
        String expectedZipValidation="Please provide a valid zip.";
        Assert.assertEquals(actualZipValidation,expectedZipValidation,"Invalid zip validation");
}
}
