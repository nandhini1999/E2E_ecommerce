package E2E.PageObjects;

import E2E.AbstractComponents.AbstractComponent;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class CartPage extends AbstractComponent {

    public WebDriver driver;
    public CartPage(WebDriver driver)
    {
        super(driver);
        this.driver = driver;
        PageFactory.initElements(driver,this);
    }

    @FindBy(css=".cart_item")
    List<WebElement> cartItemsList;

    @FindBy(css=".checkout_button")
    WebElement CheckOutButton;

    public boolean VerifyCartPage(String productName)
    {
        GotoCart();
        waitForWebElements(cartItemsList);

        for(WebElement cartItemEle : cartItemsList)
        {
            waitForWebEleVisible(cartItemEle);
            WebElement subCartEle = cartItemEle.findElement(By.cssSelector(".inventory_item_name"));
            if(subCartEle.getText().trim().equalsIgnoreCase(productName))
            {
                CheckOutButton.click();
                return true;
            }
        }

        return false;
    }
}
