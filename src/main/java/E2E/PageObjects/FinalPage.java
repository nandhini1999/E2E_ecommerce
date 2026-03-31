package E2E.PageObjects;

import E2E.AbstractComponents.AbstractComponent;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class FinalPage extends AbstractComponent {

    WebDriver driver;
    public FinalPage(WebDriver driver)
    {
        super(driver);
        this.driver = driver;
        PageFactory.initElements(driver,this);
    }

//comment to raise PR
    @FindBy(id ="finish")
    WebElement finishButton;

    @FindBy(css=".complete-header")
    WebElement thankYouNote;

    public String placeOrder()
    {
        EleScroll(finishButton);
        waitForWebEleVisible(finishButton);
        finishButton.click();
        waitForWebEleVisible(thankYouNote);
       return thankYouNote.getText();
    }
}
