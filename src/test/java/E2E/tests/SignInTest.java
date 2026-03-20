package E2E.tests;

import E2E.TestComponents.BaseTest;
import PageObjects.SignIn;
import org.testng.annotations.Test;

public class SignInTest extends BaseTest {
    @Test
    public void VerifyLogin()
    {
        SignIn signIn = openWeb();
        signIn.LoginUser();
    }





}
