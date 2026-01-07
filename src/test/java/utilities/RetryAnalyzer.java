package utilities;

import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;
import org.testng.IRetryAnalyzer;
import org.testng.ITestResult;

public class RetryAnalyzer implements IRetryAnalyzer {

    private static final Logger log = LogManager.getLogger(RetryAnalyzer.class);

    private int retryCount = 0;
    private static final int maxRetryCount = 2; // Set the maximum number of retries

    @Override
    public boolean retry(ITestResult result) {
        if (retryCount < maxRetryCount) {
            log.warn("Retrying test '{}' ({} of {})", result.getName(), retryCount + 1, maxRetryCount);
            retryCount++;
            return true;
        }
        return false;
    }
}
