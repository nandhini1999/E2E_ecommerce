package E2E.PageObjects;


import E2E.AbstractComponents.AbstractComponent;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class SignIn extends AbstractComponent {

    public WebDriver driver;
    public ProductsPage productpage;

    //Constructor
    public SignIn(WebDriver driver)
    {
        super(driver);
        this.driver = driver;
        PageFactory.initElements(driver,this);
    }

    //Elements
    @FindBy(css=".login_logo")
    WebElement LoginScreenEle;

    @FindBy(css="input[id='user-name']")
    WebElement Login_userNameEle;

    @FindBy(css="input[id='password']")
    WebElement Login_passwordEle;

    @FindBy(css="input[id='login-button']")
    WebElement Login_ButtonEle;

    @FindBy(css=".title")
    WebElement productTitle;

    @FindBy(tagName = "h3")
    WebElement errorMessageEle;

    public boolean LoginUser(String userName,String passWord)
    {
        waitForWebEleVisible(LoginScreenEle);
        //Entering cred
        Login_userNameEle.sendKeys(userName);
        Login_passwordEle.sendKeys(passWord);
        Login_ButtonEle.click();
       return isDisplayed(productTitle);
    }

    public String getLoginErrorMessage()
    {
        waitForWebEleVisible(errorMessageEle);
        return errorMessageEle.getText();
    }
}
