package E2E.tests;

import E2E.TestComponents.BaseTest;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
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
}
