package E2E.TestComponents;

import org.testng.IRetryAnalyzer;
import org.testng.ITestResult;

public class Retry implements IRetryAnalyzer {

    int count =1;
    int maxCount = 2;
    @Override
    public boolean retry(ITestResult result) {
        while(count<maxCount) {
            count++;
        return true;
        }
        return false;
    }
}
