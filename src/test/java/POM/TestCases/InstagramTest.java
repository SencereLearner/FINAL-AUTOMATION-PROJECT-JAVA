package POM.TestCases;

import POM.ObjectRepository.BaseClass;
import POM.ObjectRepository.Pages.InstagramHomePage;
import POM.ObjectRepository.TestNGListeners;
import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.Reporter;
import org.testng.annotations.*;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Method;
import java.util.concurrent.TimeUnit;

//@Listeners(TestNGListeners.class)
public class InstagramTest extends BaseClass {

  private InstagramHomePage inst;

  @BeforeTest
  public void before () throws Throwable{
    _webdriver = initializeDriver();
    _webdriver.manage().deleteAllCookies();
    _webdriver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
  }

  @Test
  public void test () throws Exception {
    _webdriver.get(properties.getProperty("instagramURL"));
    inst = new InstagramHomePage(_webdriver);
    inst.setLoginField();
    inst.setPasswordField();
  }

  @AfterMethod
  public void screenCapture (ITestResult results) throws IOException {
    if (ITestResult.FAILURE == results.getStatus()) {
      System.out.println(getClass().getSimpleName() + " has failed. \nScreenshot has been taken and saved at:" +
              "/Users/firstlast/Desktop/SELENIUM/FINALAUTOMATIONPROJECT/src/test/java/Resources/FailedScreenshots");
      File file = ((TakesScreenshot)_webdriver).getScreenshotAs(OutputType.FILE);
      File screenshotName = new File(System.getProperty("user.dir") + "/src/test/java/Resources/FailedScreenshots/" + getAlphaNumericString(5) + ".png");
      FileUtils.copyFile(file, screenshotName);
    }
    else {
      InstagramTest it = new InstagramTest(); // using reflections
      Class cl = it.getClass();
      //print the class name
      //counting total methods in the class and printing out each method's name or name and signature of the class
      Method[] methods = cl.getDeclaredMethods();
      System.out.println("Total methods passed: " + methods.length);//count of methods
      for (Method temp : methods) {//printing the methods names
        System.out.println("Each method: " + temp.getName() + " --->>> has passed");//name only
      }
      System.out.println("Class named " + getClass().getSimpleName() + " --->>> has passed");
    }
  }

  @AfterTest
  public void after () {
    try {
      System.out.println();
    }
    finally {
      _webdriver.close();
      System.out.println("The browser has been successfully closed");
    }
  }
}
