package E2E.tests;

import E2E.AbstractComponents.AbstractComponent;
import E2E.PageObjects.*;
import E2E.TestComponents.BaseTest;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.io.IOException;
import java.util.Map;
import java.util.Properties;

public class ErrorCasesTest extends BaseTest {

    public Properties prop;
    public SignIn signIn;
    public CartPage cartPage;
    public CheckOutPage checkoutpage;
    public ProductsPage productpage;
    public AbstractComponent abstractComponent;

    @BeforeClass
    public void setUp() throws IOException {
        prop = errorPropertyReader();
        signIn = new SignIn(driver);
        productpage = new ProductsPage(driver);
        cartPage = new CartPage(driver);
        checkoutpage = new CheckOutPage(driver);
        abstractComponent = new AbstractComponent(driver);
    }

@Test
public void invalidLogin() throws IOException {
boolean flag = signIn.LoginUser(dataReaderObj.getInvalidEmail(), dataReaderObj.getInvalidPassword());
Assert.assertFalse(flag);
String webErrorMessage = signIn.getLoginErrorMessage();
  String ErrorMessage = prop.getProperty("error.invalidLogin");
  Assert.assertEquals(webErrorMessage,ErrorMessage);

}
@Test(dataProvider="CheckForm")
public void emptyChFormSubmission(Map<String,String> data)
{
    signIn.LoginUser(dataReaderObj.getEmail(),dataReaderObj.getPassword());
    String buttonName = productpage.AddtoCart(dataReaderObj.getProductName());
    Assert.assertTrue(buttonName.trim().equalsIgnoreCase("Remove"));
    boolean flag = cartPage.VerifyCartPage(dataReaderObj.getProductName());
    Assert.assertTrue(flag);

    String firstName = data.get("firstName");
    String lastName = data.get("lastName");
    String pincode = data.get("pincode");

    String emptyData = checkoutpage.getChErrorMessage(firstName,lastName,pincode);

        if (firstName == null) {
            Assert.assertEquals(emptyData, prop.getProperty("error.emptyFirstName"), "Failure on checkout Empty firstName");
        }

       else if (lastName == null) {

            Assert.assertEquals(emptyData, prop.getProperty("error.emptyLastName"), "Failure on checkout Empty lastName");
        }

        else if (pincode == null) {
            Assert.assertEquals(emptyData, prop.getProperty("error.emptyPincode"), "Failure on checkout Empty pincode");
        }

        abstractComponent.GotoCart();
        abstractComponent.clearCart();

}

}
