package AbstractComponents;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class AbstractComponent {

    WebDriver driver;
    WebDriverWait wait;
    public AbstractComponent(WebDriver driver)
    {
        this.driver = driver;
        wait = new WebDriverWait(driver,Duration.ofSeconds(10));
    }

    public void waitForEleVisible(By LocatorEle)
    {
        wait.until(ExpectedConditions.visibilityOfElementLocated(LocatorEle));
    }

    public void waitForWebEleVisible(WebElement WebEle)
    {
        wait.until(ExpectedConditions.visibilityOf(WebEle));
    }

}
