package E2E.PageObjects;

import E2E.AbstractComponents.AbstractComponent;
import jdk.jfr.Category;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class ProductsPage extends AbstractComponent {

    WebDriver driver;
    List<WebElement> subList;
    public ProductsPage(WebDriver driver)
    {
        super(driver); //driver to abstract class
        this.driver = driver;
        PageFactory.initElements(driver,this);
    }

    @FindBy(css=".inventory_list .inventory_item_description")
    List<WebElement> ProductList;

    @FindBy(css=".pricebar button")
    List<WebElement> buttonNames;


    public String AddtoCart(String productName)
    {
        waitForWebElements(ProductList);
        WebElement subEleAddTocart = null;

        for(WebElement ele : ProductList)
        {
            waitForWebEleVisible(ele);
            WebElement subEleName = ele.findElement(By.cssSelector(".inventory_item_label a div"));
            waitForWebEleVisible(subEleName);
           if(subEleName.getText().trim().equalsIgnoreCase(productName))
           {
               subEleAddTocart = ele.findElement(By.cssSelector(".pricebar button"));
               waitForWebEleVisible(subEleAddTocart);
               EleScroll(subEleAddTocart);
               subEleAddTocart.click();
               break;
           }
        }
        for(WebElement bEle : buttonNames)
        {
            waitForWebEleVisible(bEle);
            if(bEle.getText().equalsIgnoreCase("Remove"))
            {
                return "Remove";
            }
        }
        return "Add";
    }
}
