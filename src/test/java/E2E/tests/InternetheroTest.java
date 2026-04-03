package E2E.tests;

import E2E.PageObjects.Internethero;
import E2E.TestComponents.BaseTest;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.time.Duration;

public class InternetheroTest extends BaseTest {

    WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));

    @Test(enabled = false)
    public void loginPage() throws InterruptedException {
        driver.get("https://admin:admin@the-internet.herokuapp.com/basic_auth");
       WebElement ele = driver.findElement(By.cssSelector("div[id=\"content\"] p"));
       System.out.println(ele.getText());
    }

    @Test(enabled = false)
    public void DragAndDrop()
    {
        driver.get("https://the-internet.herokuapp.com/drag_and_drop");
        Actions a = new Actions(driver);

        WebElement dragEle = driver.findElement(By.id("column-a"));
        wait.until(ExpectedConditions.visibilityOf(dragEle));
        WebElement dropEle = driver.findElement(By.id("column-b"));
        wait.until(ExpectedConditions.visibilityOf(dropEle));
        a.dragAndDrop(dragEle,dropEle).build().perform();
    }

    @Test(enabled = false)
    public void Dropdown()
    {
        driver.get("https://the-internet.herokuapp.com/dropdown");
        WebElement ele = driver.findElement(By.id("dropdown"));
        Select dropdown = new Select(ele);
        dropdown.selectByValue("1");
        System.out.println(dropdown.getFirstSelectedOption().getText());

    }

    @Test(enabled = false)
    public void fileUpload() throws InterruptedException {
        driver.get("https://the-internet.herokuapp.com/upload");
        WebElement upload = driver.findElement(By.id("file-upload"));
        String path = System.getProperty("user.dir")+"\\src\\main\\java\\E2E\\text.txt";
        upload.sendKeys(path);
        driver.findElement(By.id("file-submit")).click();
        String title = driver.findElement(By.tagName("h3")).getText();
        Assert.assertEquals(title,"File Uploaded!");
    }

    @Test(description = "Verifying the download")
    public void fileDownload() throws InterruptedException {
        driver.get("https://the-internet.herokuapp.com/download");
        //added prefs in baseTest while browser initialization
        driver.findElement(By.xpath("//a[text()='Image1.png']")).click();
        Thread.sleep(4000);
    }
}
