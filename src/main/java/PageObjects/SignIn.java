package PageObjects;


import AbstractComponents.AbstractComponent;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class SignIn extends AbstractComponent {

    WebDriver driver;

    public SignIn(WebDriver driver)
    {
        super(driver);
        this.driver = driver;
        PageFactory.initElements(driver,this);
    }

    //Elements
    By LoginLocator = By.xpath("//a[@href=\"/login\"]");

    @FindBy(xpath="//h2[text()='Login to your account']")
    WebElement LoginScreenEle;

    public void LoginUser()
    {
        waitForEleVisible(LoginLocator);
        driver.findElement(LoginLocator).click();
        waitForWebEleVisible(LoginScreenEle);
    }
}
