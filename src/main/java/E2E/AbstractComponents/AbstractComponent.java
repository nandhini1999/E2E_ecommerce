package E2E.AbstractComponents;

import org.jspecify.annotations.Nullable;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.bidi.browsingcontext.Locator;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

public class AbstractComponent {

    WebDriver driver;
    WebDriverWait wait;
    public AbstractComponent(WebDriver driver)
    {
        this.driver = driver;
        wait = new WebDriverWait(driver,Duration.ofSeconds(10));
        PageFactory.initElements(driver,this);
    }

    @FindBy(css=".shopping_cart_container")
    WebElement CartPageEle;

    @FindBy(xpath="//button[text()=\"Remove\"]")
    List<WebElement> RemoveListEle;

    public void waitForEleVisible(By LocatorEle)
    {
        wait.until(ExpectedConditions.visibilityOfElementLocated(LocatorEle));
    }

    public void waitForWebEleVisible(WebElement WebEle)
    {
        wait.until(ExpectedConditions.visibilityOf(WebEle));
    }

    public boolean isDisplayed(WebElement ele)
    {
        try {
         return ele.isDisplayed();}

        catch (Exception e)
        {return false;}
    }

    public void windowScroll() {
        JavascriptExecutor js = (JavascriptExecutor) driver;

        js.executeScript("window.scrollBy(0,500)");
    }

    public void waitForWebElements(List<WebElement> ele)
    {
        wait.until(ExpectedConditions.visibilityOfAllElements(ele));
    }

    public boolean isAlertPresent()
    {
        try {
            wait.until(ExpectedConditions.alertIsPresent());
            return true;
        }
        catch (Exception e) {
            return false;
        }
    }

    public void GotoCart()
    {
        waitForWebEleVisible(CartPageEle);
        CartPageEle.click();
    }

    public void clearCart()
    {
        for(WebElement ele : RemoveListEle)
        {
            waitForWebEleVisible(ele);
            ele.click();
        }
    }

    public void EleScroll(WebElement ele)
    {
       JavascriptExecutor js = (JavascriptExecutor) driver;
       js.executeScript("arguments[0].scrollIntoView(true)",ele);
    }

}
