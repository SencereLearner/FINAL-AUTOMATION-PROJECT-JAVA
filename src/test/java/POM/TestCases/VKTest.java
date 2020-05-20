package POM.TestCases;

import POM.ObjectRepository.BaseClass;
//import POM.ObjectRepository.DataProvider;
import POM.ObjectRepository.DataProviderVK;
import POM.ObjectRepository.Pages.VKPage1;
import POM.ObjectRepository.TestNGListeners;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.*;
import org.apache.log4j.Logger;
import org.apache.log4j.BasicConfigurator;


import java.io.File;
import java.io.IOException;
import java.lang.reflect.Method;

//@Listeners(TestNGListeners.class)
public class VKTest extends BaseClass {
  private Logger logger = Logger.getLogger("VKTest");
  private VKPage1 vkPage1;//initializing globally to be able to use in another @Test test if needed (otherwise would have to create another object of a class)

  @BeforeTest
  @Parameters({"urlVK"})//url comes from TestNg file
  public void initializeBrowser() throws IOException {
    _webdriver = initializeDriver2();
    BasicConfigurator.configure();
  }

  @Test(dataProvider = "LoginDataProviderVK", dataProviderClass = DataProviderVK.class)
  public void homePageNavigation(String email, String password) {

    _webdriver.get(properties2.getProperty("urlFB"));
    logger.info("FireFox browser has been launched with @DataProvider");
    vkPage1 = new VKPage1(_webdriver);

    vkPage1.enterEmail().sendKeys(email);
    vkPage1.enterPassword().sendKeys(password);
    vkPage1.pressLoginButton().click();
    _webdriver.navigate().back();
    //Assert.fail();//to test the screenshot taking method on fail
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
      VKTest it = new VKTest(); // using reflections
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
  public void closeBrowser() {
    try {
    }
     finally {
      logger.warn("Closed the browser even if the test is failed");
      _webdriver.quit();
      _webdriver = null;//to save memory
    }
  }
}

