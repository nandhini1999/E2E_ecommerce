package E2E.TestComponents;

import E2E.PageObjects.ProductsPage;
import E2E.PageObjects.SignIn;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WindowType;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.*;
import tools.jackson.core.type.TypeReference;
import tools.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.Duration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

public class BaseTest {
    public WebDriver driver;
    public Properties prop;
    public DataReader dataReaderObj;
    public Object[][] result;
    public SignIn signIn;
    @BeforeClass
    public void InitiateBrowser() throws IOException {
        prop = new Properties();
        FileInputStream fis = new FileInputStream(System.getProperty("user.dir")+"\\src\\main\\java\\E2E\\resources\\Global.Properties");
        prop.load(fis);
        String browserName =
       (System.getProperty("browser")!=null)? System.getProperty("browser") :  prop.getProperty("browser");

        if(browserName.equalsIgnoreCase("Chrome"))
        {
            ChromeOptions options = new ChromeOptions();
            if(browserName.contains("headless"))
            {
                options.addArguments("--headless");
                options.addArguments("--window-size=2000,900");
            }
            else
            {
                Map<String, Object> prefs = new HashMap<>();
                prefs.put("profile.password_manager_leak_detection", false);
                options.setExperimentalOption("prefs", prefs);
                driver.manage().window().maximize();
            }

            driver = new ChromeDriver(options);


        }
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
    }

    @BeforeClass
    public void jsonReader()
    {
        ObjectMapper mapper = new ObjectMapper();
       String path = System.getProperty("user.dir")+"\\src\\main\\java\\E2E\\Data\\testData.json";
       dataReaderObj = mapper.readValue(new File(path), DataReader.class);
    }


    public Properties errorPropertyReader() throws IOException {
        Properties errorProp = new Properties();
        FileInputStream fis = new FileInputStream(new File(System.getProperty("user.dir")+"\\src\\main\\java\\E2E\\resources\\errorData.Properties"));
        errorProp.load(fis);
        return errorProp;
    }

    @DataProvider(name  = "CheckForm")
    public Object[][] getCheckFormData()
    {
        List<Map<String,String>> dataList = dataReaderObj.getInvalidChForm();
        Object[][] ChResult = new Object[dataList.size()][1];

        for(int i=0;i<dataList.size();i++)
        {
            ChResult[i][0] = dataList.get(i);
        }
    return ChResult;

    }

    @DataProvider(name="Multiple login")
    public Object[][] multiLoginObject()
    {
      List<Map<String,String>> dataList =  dataReaderObj.getMultipleLogin();
      result = new Object[dataList.size()][1];
      for(int i=0;i<dataList.size();i++)
      {
          result[i][0] = dataList.get(i);
      }
      return result;
    }

    public String getScreenshot(String TcName,WebDriver driver) throws IOException {
     File src = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
     String screenShotPath = System.getProperty("user.dir")+"\\ExtentReports\\"+ TcName+".png";
     FileUtils.copyFile(src,new File(screenShotPath));
     return screenShotPath;
    }

    @BeforeMethod
    public void openWeb() {
        String Liveurl = prop.getProperty("url");
        driver.get(Liveurl);
        signIn = new SignIn(driver);
    }
    @AfterMethod
    public void CloseTab()
    {
      driver.switchTo().newWindow(WindowType.TAB);
    }

    @AfterClass
    public void driverTerminate()
    {
        driver.quit();
    }
}
