package seleniumbasics;

import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;


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
    public void setUp() {
        testInitialize("chrome");
    }

    @AfterMethod
    public void tearDown() {
       // driver.close();
       // driver.quit();
    }

    @Test
    public void TC_001_verifyObsquraTitle() {
        driver.get("https://selenium.obsqurazone.com/index.php");
        String actualTitle = driver.getTitle();
        String expectedTitle = "Obsqura Testing";
        Assert.assertEquals(actualTitle, expectedTitle, "Invalid Title Found");
    }

    @Test
    public void TC_002_verifyTwoInputFieldMessage() {
        driver.get("https://selenium.obsqurazone.com/simple-form-demo.php");
        WebElement value1 = driver.findElement(By.id("value-a"));
        value1.sendKeys("88");
        WebElement value2 = driver.findElement(By.id("value-b"));
        value2.sendKeys("22");
        WebElement getTotal = driver.findElement(By.id("button-two"));
        getTotal.click();
        WebElement total = driver.findElement(By.id("message-two"));
        String actualTotal = total.getText();
        String expectedTotal = "Total A + B : 100";
        Assert.assertEquals(actualTotal, expectedTotal, "Invalid Total");
    }

    @Test
    public void TC_003_verifySingleInputFieldMessage() {
        driver.get("https://selenium.obsqurazone.com/simple-form-demo.php");
        WebElement message = driver.findElement(By.id("single-input-field"));
        message.sendKeys("Check Message");
        WebElement showMessageButton = driver.findElement(By.id("button-one"));
        showMessageButton.click();
        WebElement showMessage = driver.findElement(By.id("message-one"));
        String showMessageActual = showMessage.getText();
        String showMessageExpected = "Your Message : Check Message";
        Assert.assertEquals(showMessageExpected, showMessageActual, "Invalid Message");

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
        String actualCityValidation = cityValidation.getText();
        String expectedCityValidation = "Please provide a valid city.";
        Assert.assertEquals(actualCityValidation, expectedCityValidation, "Invalid city validation Message");
        WebElement stateValidation = driver.findElement(By.xpath("/html/body/section/div/div/div[2]/div/div/div[2]/form/div[2]/div[2]/div[1]"));
        String actualStateValidation = stateValidation.getText();
        String expectedStateValidation = "Please provide a valid state.";
        Assert.assertEquals(actualStateValidation, expectedStateValidation, "Invalid state validation Message");
        WebElement zipValidation = driver.findElement(By.xpath("/html/body/section/div/div/div[2]/div/div/div[2]/form/div[2]/div[3]/div[1]"));
        String actualZipValidation = zipValidation.getText();
        String expectedZipValidation = "Please provide a valid zip.";
        Assert.assertEquals(actualZipValidation, expectedZipValidation, "Invalid zip validation Message");
        WebElement agreeValidation = driver.findElement(By.cssSelector("body > section > div > div > div.col-lg-9.col-md-9.col-sm-12.col-xs-12 > div > div > div.card-body > form > div.form-group > div > div"));
        String actualAgreeValidation = agreeValidation.getText();
        String expectedAgreeValidation = "You must agree before submitting.";
        Assert.assertEquals(actualAgreeValidation, expectedAgreeValidation, "Invalid agree validation Message");
    }

    @Test
    public void TC_005_verifySubmitFormNOZip() {
        driver.get("https://selenium.obsqurazone.com/form-submit.php");
        WebElement firstName = driver.findElement(By.xpath("//input[@class='form-control'][1]"));
        firstName.sendKeys("Abel");
        WebElement lastName = driver.findElement(By.xpath("//input[@class='form-control' and@placeholder='Last name']"));
        lastName.sendKeys("Koshy");
        WebElement username = driver.findElement(By.xpath("//input[contains(@placeholder,'Username')]"));
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

        WebElement zipValidation = driver.findElement(By.xpath("/html/body/section/div/div/div[2]/div/div/div[2]/form/div[2]/div[3]/div[1]"));
        String actualZipValidation = zipValidation.getText();
        String expectedZipValidation = "Please provide a valid zip.";
        Assert.assertEquals(actualZipValidation, expectedZipValidation, "Invalid zip validation");
    }
@Test
    public void TC_006_verifySubmitForm() {
        driver.get("https://selenium.obsqurazone.com/form-submit.php");
        WebElement firstName = driver.findElement(By.xpath("//input[@class='form-control'][1]"));
        firstName.sendKeys("Abel");
        WebElement lastName = driver.findElement(By.xpath("//input[@class='form-control' and@placeholder='Last name']"));
        lastName.sendKeys("Koshy");
        WebElement username = driver.findElement(By.xpath("//input[contains(@placeholder,'Username')]"));
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
    }
    @Test
    public void TC_007_verifyNewsLetter(){
        driver.get("https://demowebshop.tricentis.com/");
        WebElement newsletterField= driver.findElement(By.cssSelector("#newsletter-email"));
        newsletterField.sendKeys("abel@gmail.com");
        WebElement subscribeButton= driver.findElement(By.cssSelector("#newsletter-subscribe-button"));
        subscribeButton.click();
    }
    @Test
    public void TC_008_verifyInstantDemoForm(){
        driver.get("https://phptravels.com/demo/");
        WebElement firstName= driver.findElement(By.cssSelector("input[name='first_name']"));
        firstName.sendKeys("Abel");
        WebElement lastName= driver.findElement(By.cssSelector("input[class='last_name input mb1']"));
        lastName.sendKeys("Koshy");
        WebElement businessName= driver.findElement(By.cssSelector("input[placeholder='Business Name']"));
        businessName.sendKeys("Abel Sam Koshy");
        WebElement email = driver.findElement(By.cssSelector("input[class='email input mb1']"));
        email.sendKeys("abel@gmail.com");
        WebElement value1 = driver.findElement(By.cssSelector("span[id='numb1']"));
        String valueA = value1.getText();
        int number1 = Integer.parseInt(valueA);
        WebElement value2 = driver.findElement(By.cssSelector("span[id='numb2']"));
        String valueB = value2.getText();
        int number2 = Integer.parseInt(valueB);
        WebElement resultField = driver.findElement(By.cssSelector("input[id='number']"));
        int result = number1+number2;
        String Result1 = String.valueOf(result);
        resultField.sendKeys(Result1);
        WebElement submitButton = driver.findElement(By.cssSelector("button[id='demo']"));
        submitButton.click();
    }
    @Test
    public void TC_009_verifyQuitAndClose(){
        driver.get("https://demo.guru99.com/popup.php");
        WebElement clickHere = driver.findElement(By.xpath("//a[text()='Click Here']"));
        clickHere.click();
    }
    @Test
    public void TC_010_verifyNavigateTo(){
       // driver.get("https://demowebshop.tricentis.com/");
        driver.navigate().to("https://demowebshop.tricentis.com/");
    }
    @Test
    public void TC_011_verifyRefresh(){
        driver.get("https://demowebshop.tricentis.com/");

        driver.navigate().refresh();
    }
    @Test
    public void TC_012_verifyForwardAndBackword() throws InterruptedException {
        driver.get("https://demowebshop.tricentis.com/");
        WebElement login = driver.findElement(By.xpath("//a[text()='Log in']"));
        login.click();
        driver.navigate().back();
        Thread.sleep(10000);
        driver.navigate().forward();
    }
    @Test
    public void TC_013_verifyWebElementCommands() throws InterruptedException {
        driver.get("https://selenium.obsqurazone.com/ajax-form-submit.php");
        WebElement subject = driver.findElement(By.xpath("//input[@id='subject']"));
        subject.sendKeys("Selenium");
        WebElement description = driver.findElement(By.xpath("//textarea[@id='description']"));
        description.sendKeys("Automation Testing");
        subject.clear();
        String classAttributeValue =subject.getAttribute("class");
        System.out.println(classAttributeValue);
        String tagname= subject.getTagName();
        System.out.println(tagname);
        subject.sendKeys("WebElement commands");
        WebElement submit = driver.findElement(By.xpath("//input[@class='btn btn-primary']"));
        submit.click();
        Thread.sleep(10000);
        WebElement message = driver.findElement(By.xpath("//div[@id='message-one']"));
        String messageActual = message.getText();
        String messageExpected = "Form has been submitted successfully!";
        Assert.assertEquals(messageExpected, messageActual, "Invalid Message");

    }
    @Test
    public void TC_014_verifyIsDisplayed(){
        driver.get("https://selenium.obsqurazone.com/ajax-form-submit.php");
        WebElement subject = driver.findElement(By.xpath("//input[@id='subject']"));
        subject.sendKeys("Selenium");
        boolean status=subject.isDisplayed();
        Assert.assertTrue(status,"subject field is not displayed");
    }
    @Test
    public void TC_015_verifyIsSelected(){
        driver.get("https://selenium.obsqurazone.com/check-box-demo.php");
        WebElement click= driver.findElement(By.xpath("//input[@id='gridCheck']"));
        boolean statusBeforeClick= click.isSelected();
        System.out.println(statusBeforeClick);
        Assert.assertFalse(statusBeforeClick,"checkbox is selected");
        click.click();
        boolean status= click.isSelected();
        System.out.println(status);
        Assert.assertTrue(status,"checkbox not selected");
    }
    @Test
    public void TC_016_verfyIsEnabled(){
        driver.get("https://selenium.obsqurazone.com/ajax-form-submit.php");
        WebElement submit = driver.findElement(By.xpath("//input[@class='btn btn-primary']"));
        boolean status = submit.isEnabled();
        System.out.println(status);
        Assert.assertTrue(status,"submit button not enabled");
        Point point= submit.getLocation();
        System.out.println(point.x +", "+point.y);
        Dimension dim= submit.getSize();
        System.out.println(dim.height+","+ dim.width);
        String backgroundColor =submit.getCssValue("background-color");
        System.out.println(backgroundColor);
        WebElement element=driver.findElement(By.tagName("input"));
        System.out.println(element);
        List<WebElement> elements =driver.findElements(By.tagName("input"));
        System.out.println(elements);
        submit.submit();

    }
    @Test
    public void TC_017_verifyDiffBwFindElementAndFindElements(){
        driver.get("https://selenium.obsqurazone.com/radio-button-demo.php");
//        WebElement maleRadioButton = driver.findElement(By.id("inlineRadio11"));
//        maleRadioButton.click();
        List<WebElement> genders = driver.findElements(By.xpath("//input[@name='student-gender']"));
        for(int i=0;i< genders.size();i++){
            String gender= genders.get(i).getAttribute("value");
            if(gender.equals("Male")){
                genders.get(i).click();
            }
        }
    }
    @Test
    public void TC_018_verifyMultipleWindowHandling() {
        driver.get("https://demo.guru99.com/popup.php");
        String parentWindow = driver.getWindowHandle();
        System.out.println("parent window id = " + parentWindow);
        WebElement clickLink = driver.findElement(By.xpath("//a[text()='Click Here']"));
        clickLink.click();
        Set<String> handles = driver.getWindowHandles();
        System.out.println(handles);
        Iterator<String> handleids = handles.iterator();
        while (handleids.hasNext()) {
            String childWindow = handleids.next();
            if (!childWindow.equals(parentWindow)) {
                driver.switchTo().window(childWindow);
                WebElement emailField = driver.findElement(By.name("emailid"));
                emailField.sendKeys("priya@gmail.com");
                WebElement submitButton = driver.findElement(By.name("btnLogin"));
                submitButton.click();
                driver.close();
            }
        }
        driver.switchTo().window(parentWindow);
    }

    @Test
    public void TC_019_verifyMultipleWindowHandlingHomeWork1() {
        driver.get("https://demoqa.com/browser-windows");
        String parentWindow = driver.getWindowHandle();
        WebElement newTabButton = driver.findElement(By.id("tabButton"));
        newTabButton.click();
        Set<String> handles = driver.getWindowHandles();
        Iterator<String> handleids = handles.iterator();
        while (handleids.hasNext()) {
            String childWindow = handleids.next();
            if (!childWindow.equals(parentWindow)) {
                driver.switchTo().window(childWindow);
                WebElement text = driver.findElement(By.id("sampleHeading"));
                String actualText = text.getText();
                String expectedText = "This is a sample page";
                Assert.assertEquals(actualText, expectedText, "Text displayed is not correct");
                driver.close();
            }
        }
        driver.switchTo().window(parentWindow);
    }

    @Test
    public void TC_020_verifyMultipleWindowHandlingHomeWork2() {
        driver.get("https://demoqa.com/browser-windows");
        String parentWindow = driver.getWindowHandle();
        WebElement newWindowbutton = driver.findElement(By.id("windowButton"));
        newWindowbutton.click();
        Set<String> handles = driver.getWindowHandles();
        Iterator<String> handleids = handles.iterator();
        while (handleids.hasNext()) {
            String childWindow = handleids.next();
            if (! childWindow.equals(parentWindow)) {
                driver.switchTo().window(childWindow);
                WebElement text = driver.findElement(By.id("sampleHeading"));
                String actualText = text.getText();
                String expectedText = "This is a sample page";
                Assert.assertEquals(actualText, expectedText, "Text displayed is not correct");
                driver.close();
            }
        }
        driver.switchTo().window(parentWindow);
    }

    @Test
    public void TC_021_verifyMultipleWindowHandlingHomeWork3() {
        driver.get("https://demoqa.com/browser-windows");
        String parentWindow = driver.getWindowHandle();
        WebElement windowMessageButton = driver.findElement(By.id("messageWindowButton"));
        windowMessageButton.click();
        Set<String> handles = driver.getWindowHandles();
        Iterator<String> handleids = handles.iterator();
        while (handleids.hasNext()) {
            String childWindow = handleids.next();
            if (! childWindow.equals(parentWindow)) {
                driver.switchTo().window(childWindow);
                WebElement text = driver.findElement(By.xpath("/html/body/text()"));
                String actualText = text.getText();
                String expectedText = "Knowledge increases by sharing but not by saving. Please share this website with your friends and in your organization.";
                Assert.assertEquals(actualText, expectedText, "Text displayed is not correct");
                driver.close();
            }
        }
        driver.switchTo().window(parentWindow);
    }
    @Test
    public void TC_022_verifySimpleAlert(){
        driver.get("https://selenium.obsqurazone.com/javascript-alert.php");
        WebElement clickMe= driver.findElement(By.xpath("//button[@class='btn btn-success']"));
        clickMe.click();
        Alert alert=driver.switchTo().alert();
        String alertText = alert.getText();
        System.out.println(alertText);
        alert.accept();
    }
    @Test
    public void TC_023_verifyConfirmationAlert(){
        driver.get("https://selenium.obsqurazone.com/javascript-alert.php");
        WebElement clickMe = driver.findElement(By.xpath("//button[@class='btn btn-warning']"));
        clickMe.click();
        Alert alert = driver.switchTo().alert();
        String alertText = alert.getText();
        System.out.println(alertText);
        alert.dismiss();
    }
    @Test
    public void TC_024_verifyPromptAlert(){
        driver.get("https://selenium.obsqurazone.com/javascript-alert.php");
        WebElement clickMe = driver.findElement(By.xpath("//button[@class='btn btn-danger']"));
        clickMe.click();
        Alert alert =driver.switchTo().alert();
        String alertText =alert.getText();
        System.out.println(alertText);
        alert.sendKeys("SAM");
        alert.accept();
    }
    @Test
    public void TC_025_verifyTextInFrame(){
        driver.get("https://demoqa.com/frames");
        List<WebElement> frames=driver.findElements(By.tagName("iframe"));
        int numberOfFrames= frames.size();
        System.out.println(numberOfFrames);
      //  driver.switchTo().frame("iframe");
      //  driver.switchTo().frame("sampleHeading");
        WebElement frame= driver.findElement(By.id("frame1"));
        driver.switchTo().frame(frame);
        WebElement heading = driver.findElement(By.id("sampleHeading"));
        String text= heading.getText();
        System.out.println(text);
        driver.switchTo().parentFrame();
      //  driver.switchTo().defaultContent();
    }
    @Test
    public void TC_026_verifyRightClick(){
        driver.get("https://demo.guru99.com/test/simple_context_menu.html");
        WebElement rightClick = driver.findElement(By.xpath("//span[@class='context-menu-one btn btn-neutral']"));
        Actions action = new Actions(driver);
        action.contextClick(rightClick).build().perform();
        //action.contextClick().build().perform();
    }
    @Test
    public void TC_027_verifyDoubleClick(){
        driver.get("https://demo.guru99.com/test/simple_context_menu.html");
        WebElement doubleClick= driver.findElement(By.xpath("//button[text()='Double-Click Me To See Alert']"));
        Actions actions= new Actions(driver);
        actions.doubleClick(doubleClick).build().perform();
        Alert alert = driver.switchTo().alert();
        alert.accept();
    }
    @Test
    public void TC_028_verifyMouseOver(){
        driver.get("https://demoqa.com/menu/");
        WebElement mainItem1= driver.findElement(By.xpath("//a[text()='Main Item 1']"));
        Actions action = new Actions(driver);
      // action.moveToElement(mainItem1).build().perform();
      //  action.moveToElement(mainItem1,50,50).build().perform();
        action.moveByOffset(40,80).build().perform();
    }
    @Test
    public void TC_029_verifyDragAndDrop(){
        driver.get("https://demoqa.com/droppable");
        WebElement dragMe= driver.findElement(By.xpath("//div[@id='draggable']"));
        WebElement dropHere= driver.findElement(By.xpath("//div[@id='droppable']"));
        Actions action = new Actions(driver);
        action.dragAndDrop(dragMe,dropHere).build().perform();

    }
    @Test
    public void TC_030_verifyDragAndDropByOffset(){
        driver.get("https://demoqa.com/dragabble");
        WebElement drag= driver.findElement(By.xpath("//div[text()='Drag me']"));
        Actions action = new Actions(driver);
        action.dragAndDropBy(drag,100,100).build().perform();
    }
    @Test
    public void TC_031_verifyDragAndResizeHW(){
        driver.get("https://demoqa.com/resizable");
        WebElement resize = driver.findElement(By.xpath("//*[@id=\"resizableBoxWithRestriction\"]/span"));
        Actions action = new Actions(driver);
        //action.clickAndHold(resize).moveByOffset(200,100).build().perform();
        action.dragAndDropBy(resize,200,100).build().perform();
    }
    @Test
    public void TC_032_verifyDragAndDropHW(){
        driver.get("https://selenium.obsqurazone.com/drag-drop.php");
        WebElement drag1 = driver.findElement(By.xpath("//span[text()='Draggable n°1']"));
        WebElement drag2 = driver.findElement(By.xpath("//span[text()='Draggable n°2']"));
        WebElement drag3=driver.findElement(By.xpath("//span[text()='Draggable n°3']"));
        WebElement drag4 = driver.findElement(By.xpath("//span[text()='Draggable n°4']"));
        WebElement drop = driver.findElement(By.xpath("//div[@dropzone='move']"));

        Actions action = new Actions(driver);
        //action.clickAndHold(drag).moveToElement(drop).release().build().perform();
        action.dragAndDrop(drag1,drop).build().perform();
        action.dragAndDrop(drag2,drop).build().perform();
        action.dragAndDrop(drag3,drop).build().perform();
        action.dragAndDrop(drag4,drop).build().perform();
        //action.dragAndDropBy();
    }
    @Test
    public void verifyValuesInDropDown(){
        driver.get("https://demo.guru99.com/test/newtours/register.php");
        List<String> expDropDownList = new ArrayList<>();
        expDropDownList.add("ALBANIA");
        expDropDownList.add("ALGERIA");
        expDropDownList.add("AMERICAN SAMOA");
        expDropDownList.add("ANDORRA");
        List<String> actDropDownList = new ArrayList<>();
        WebElement countryDropDown= driver.findElement(By.xpath("//select[@name='country']"));
        Select select = new Select(countryDropDown);
        List <WebElement> dropDownOption =select.getOptions();
        System.out.println(dropDownOption.size());
        for(int i=0; i<4;i++){
            //System.out.println(dropDownOption.get(i).getText());
            actDropDownList.add(dropDownOption.get(i).getText());
        }
        System.out.println(actDropDownList);
        Assert.assertEquals(actDropDownList,expDropDownList,"DropDown values not found");
        //select.selectByVisibleText("INDIA");
        //select.selectByIndex(23);
        select.selectByValue("INDIA");
        select.getAllSelectedOptions();
    }
@Test
    public void verifyMethodsInSelectClass(){
        driver.get("https://www.softwaretestingmaterial.com/sample-webpage-to-automate/");
        WebElement dropDown = driver.findElement(By.xpath("(//select[@class='spTextField'])[1]"));
        Select select= new Select(dropDown);
        boolean multipleStatus =select.isMultiple();
        System.out.println(multipleStatus);
        select.selectByVisibleText("Performance Testing");
        select.selectByValue("msmanual");
        List<WebElement> selectedOption= select.getAllSelectedOptions();
        for(int i=0; i< selectedOption.size();i++){
            System.out.println(selectedOption.get(i).getText());
        }
    select.deselectByValue("msmanual");
    select.deselectAll();
}
}
