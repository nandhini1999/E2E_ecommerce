package InternetHerokuApp;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;

import java.time.Duration;

public class ShadowDom {

    public static void main(String[] args)
    {
        WebDriver driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
        driver.get("https://the-internet.herokuapp.com/shadowdom");

        // Shadow Host -> Shadow Root -> Access Element

        WebElement shadowHost = driver.findElement(By.tagName("my-paragraph"));

        JavascriptExecutor js = (JavascriptExecutor) driver;
//        SearchContext shadowRoot = (SearchContext) js.executeScript("return arguments[0].shadowRoot",shadowHost);
        String text = (String) js
                .executeScript(
                        "return arguments[0].shadowRoot.querySelector('slot').assignedNodes()[0].textContent;",
                        shadowHost);
        System.out.println(text);
        driver.quit();
    }
}
