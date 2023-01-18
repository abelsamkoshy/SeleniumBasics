package seleniumcommands;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.List;

public class WebTableDemo {
    public static void main(String args[]){
        WebDriver driver;
        driver = new ChromeDriver();
        driver.get("https://www.w3schools.com/html/html_tables.asp");
        List<WebElement> rowsElements =driver.findElements(By.xpath("//table[@id='customers']//tbody//tr"));
        System.out.println(rowsElements.size());
        int rowSize= rowsElements.size();
        List<WebElement> columnElements =driver.findElements(By.xpath("//table[@id='customers']//tbody//tr[2]//td"));
        System.out.println(columnElements.size());
        int colSize= columnElements.size();

        for(int i=1;i<=rowSize;i++){
            for (int j=1; i<=colSize;j++){
                System.out.print(driver.findElement(By.xpath("//table[@id='customers']//tbody//tr["+i+"]//td["+j+"]")).getText());
            }
            System.out.println();
        }
    }
}
