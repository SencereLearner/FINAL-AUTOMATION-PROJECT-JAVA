package POM.ObjectRepository;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

public class TestNGListeners extends BaseClass implements ITestListener {

  public void onTestStart(ITestResult result) {
  }

  public void onTestSuccess(ITestResult result) {
  }

//  public void onTestFailure(ITestResult result) {
//    System.out.println("Test Failed");
//    //ScreenshotTakenOnFail(_webdriver, result.getMethod().getMethodName());
//    ScreenshotTakenOnFail(_webdriver, result.getName());//the same as above
//  }

  public void onTestSkipped(ITestResult result) {
  }

  public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
  }

  public void onTestFailedWithTimeout(ITestResult result) {
    this.onTestFailure(result);
  }

  public void onStart(ITestContext context) {
  }

  public void onFinish(ITestContext context) {
  }
}
