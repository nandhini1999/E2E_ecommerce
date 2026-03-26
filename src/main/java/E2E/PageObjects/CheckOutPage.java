package E2E.PageObjects;

import E2E.AbstractComponents.AbstractComponent;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class CheckOutPage extends AbstractComponent {

    WebDriver driver;
    public CheckOutPage(WebDriver driver)
    {
        super(driver);
        this.driver = driver;
        PageFactory.initElements(driver,this);
    }

@FindBy(css=".title")
WebElement checkoutTitle;

    @FindBy(css="#first-name")
    WebElement Ch_firsName;

    @FindBy(css="#last-name")
    WebElement Ch_lastName;

    @FindBy(id="postal-code")
    WebElement Ch_pincode;

    @FindBy(css="#continue")
    WebElement ContinueButton;

    @FindBy(tagName = "h3")
    WebElement errorMessageEle;

    public String CheckOutLanding()
    {
        return checkoutTitle.getText();
    }

    public String checkoutForm(String firstName,String lastName,String pincode)
    {
        Ch_firsName.sendKeys(firstName);
        Ch_lastName.sendKeys(lastName);
        Ch_pincode.sendKeys(pincode);
        ContinueButton.click();
        waitForWebEleVisible(checkoutTitle);
        return checkoutTitle.getText();
    }

    public String getChErrorMessage(String firstName,String lastName,String pincode)
    {
        if(firstName!=null)
        {
            Ch_firsName.sendKeys(firstName);
        }

        if(lastName!=null)
        {
            Ch_lastName.sendKeys(lastName);
        }

        if(pincode!=null)
        {
            Ch_pincode.sendKeys(pincode);
        }

        ContinueButton.click();
        waitForWebEleVisible(errorMessageEle);
        return errorMessageEle.getText();
    }


}
