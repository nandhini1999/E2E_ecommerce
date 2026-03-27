package E2E.TestComponents;

import E2E.resources.ExtentReportTG;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import org.testng.annotations.BeforeClass;

import java.io.IOException;

public class Listener implements ITestListener {

    private ExtentReports extentReport = ExtentReportTG.getExtentTest();
    private ExtentTest extentTest;
    private ThreadLocal<ExtentTest> extentThread = new ThreadLocal<ExtentTest>();

    public void onTestStart(ITestResult result)
    {
        System.out.println("Test case started");
         String tcName = result.getMethod().getMethodName();
        extentTest =  extentReport.createTest(tcName);
        extentThread.set(extentTest);
    }

    public void onTestSuccess(ITestResult result)
    {
        extentThread.get().log(Status.PASS,"Test Case Successfull");
    }

    public void onTestFailure(ITestResult result)
    {
        String path= null;
        WebDriver driver=null;
        extentThread.get().fail(result.getThrowable());

        try {
            driver = (WebDriver)result.getTestClass().getRealClass().getField("driver").get(result.getInstance());
        } catch (Exception e) {
            System.out.println(e);
        }

        String TestCaseName = result.getMethod().getMethodName();
        try {
            BaseTest baseTest = (BaseTest) result.getInstance();
          path =  baseTest.getScreenshot(TestCaseName,driver);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        extentThread.get().addScreenCaptureFromPath(path,result.getMethod().getMethodName());
    }

    public void onFinish(ITestContext context)
    {
        extentReport.flush();

    }
}
