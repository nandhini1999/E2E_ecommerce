package E2E.resources;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

import java.io.File;

public class ExtentReportTG {

    static ExtentReports extentReport;
    public static ExtentReports getExtentTest()
    {
        if(extentReport==null) {
            ExtentSparkReporter reporter = new ExtentSparkReporter(System.getProperty("user.dir")+"\\ExtentReports\\index.html");
            reporter.config().setDocumentTitle("Web E2E Automation");
            reporter.config().setReportName("E2E sauce web Automation");
            extentReport = new ExtentReports();
            extentReport.attachReporter(reporter);
            extentReport.setSystemInfo("Tester", "Nandhinidevi");
        }

        return extentReport;
    }
}
