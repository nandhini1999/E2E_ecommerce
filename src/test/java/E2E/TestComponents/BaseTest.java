package E2E.TestComponents;

import E2E.tests.SignInTest;
import PageObjects.SignIn;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;

import java.io.FileInputStream;
import java.io.IOException;
import java.time.Duration;
import java.util.Properties;

public class BaseTest {
    public WebDriver driver;
    public Properties prop;

    @BeforeTest
    public void InitiateBrowser() throws IOException {
        prop = new Properties();
        FileInputStream fis = new FileInputStream(System.getProperty("user.dir")+"\\src\\main\\resources\\Global.Properties");
        prop.load(fis);
        String browserName = prop.getProperty("browser");

        if(browserName.equalsIgnoreCase("Chrome"))
        {
           driver = new ChromeDriver();
        }
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
    }

    public SignIn openWeb()
    {
        String Liveurl = prop.getProperty("url");
        driver.get(Liveurl);
        return new SignIn(driver);
    }

    @AfterTest
    public void driverTerminate()
    {
        driver.quit();
    }

}
