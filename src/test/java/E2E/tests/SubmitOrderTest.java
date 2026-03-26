package E2E.tests;

import E2E.PageObjects.CartPage;
import E2E.PageObjects.CheckOutPage;
import E2E.PageObjects.FinalPage;
import E2E.PageObjects.ProductsPage;
import E2E.TestComponents.BaseTest;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class SubmitOrderTest extends BaseTest {
    public ProductsPage productpage;
public CartPage cartPage;
public CheckOutPage checkoutpage;
public FinalPage finalpage;

    @BeforeClass
    public void setUp() {
        productpage = new ProductsPage(driver);
       cartPage = new CartPage(driver);
       checkoutpage = new CheckOutPage(driver);
       finalpage = new FinalPage(driver);
    }

    @Test
    public void SubmitOrder()
    {
        signIn.LoginUser(dataReaderObj.getEmail(),dataReaderObj.getPassword());
   String buttonName = productspage.AddtoCart(dataReaderObj.getProductName());
   Assert.assertTrue(buttonName.trim().equalsIgnoreCase("Remove"));
  boolean flag = cartPage.VerifyCartPage(dataReaderObj.getProductName());
  Assert.assertTrue(flag);
  String checkOutTitle = checkoutpage.CheckOutLanding();
  Assert.assertEquals(checkOutTitle,"Checkout: Your Information");
  String FinalPageTitle = checkoutpage.checkoutForm(dataReaderObj.getFirstName(),dataReaderObj.getLastName(), dataReaderObj.getPincode());
  Assert.assertEquals(FinalPageTitle,"Checkout: Overview");
       String ThankYouNote = finalpage.placeOrder();
       Assert.assertEquals(ThankYouNote,"Thank you for your order!");
    }
}
