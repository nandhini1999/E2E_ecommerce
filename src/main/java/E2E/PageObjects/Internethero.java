package E2E.PageObjects;

import E2E.AbstractComponents.AbstractComponent;
import org.openqa.selenium.WebDriver;

public class Internethero extends AbstractComponent {

    WebDriver driver;

    public Internethero(WebDriver driver)
    {
        super(driver);
        this.driver = driver;
    }

    public void loginAction()
    {
        driver.get("https://the-internet.herokuapp.com/");
        driver.switchTo().alert().sendKeys("admin");
        driver.switchTo().alert().sendKeys("admin");
        driver.switchTo().alert().accept();
    }
}
