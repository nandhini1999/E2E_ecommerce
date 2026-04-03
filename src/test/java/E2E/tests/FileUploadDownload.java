package E2E.tests;

import E2E.TestComponents.BaseTest;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.List;

public class FileUploadDownload extends BaseTest {

    WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

    @Test
    public void verifyUploadDownload() {
        driver.get("https://rahulshettyacademy.com/upload-download-test/");
        WebElement upload = driver.findElement(By.cssSelector("input[type='file']"));
        String path = System.getProperty("user.home") + "\\Downloads\\download.xlsx";
        upload.sendKeys(path);

        WebElement toast = driver.findElement(By.cssSelector(".Toastify__toast-body div:nth-child(2)"));
        wait.until(ExpectedConditions.visibilityOf(toast));
        wait.until(ExpectedConditions.invisibilityOf(toast));

        String priceColumn = driver.findElement(By.xpath("//div[text()=\"Price\"]")).getAttribute("data-column-id");
        WebElement actualPrice = driver.findElement(By.xpath("//div[text()=\"Apple\"]/parent::div/parent::div/div[@id=\"cell-" + priceColumn + "-undefined\"]"));

        if (Integer.parseInt(actualPrice.getText()) == 345) {
            System.out.println("It's present");
        }
    }

    @Test(enabled = true)
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
        driver.findElement(By.xpath("//a[text()='862930.jpg']")).click();
        Thread.sleep(4000);
    }
}
