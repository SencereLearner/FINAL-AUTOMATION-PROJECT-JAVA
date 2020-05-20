package POM.TestCases;
import POM.ObjectRepository.BaseClass;
import POM.ObjectRepository.Pages.CheckBrokenLinksPage;
import POM.ObjectRepository.Pages.CyberLiabilityPageObject;
import POM.ObjectRepository.Pages.HomePageObject;
import POM.ObjectRepository.Pages.MidOfAppLoginPage;
import POM.ObjectRepository.TestNGListeners;
import org.apache.commons.io.FileUtils;
import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebElement;
import org.testng.ITestResult;
import org.testng.annotations.*;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Method;
import java.util.List;

//@Listeners(TestNGListeners.class)
public class InsureonTest extends BaseClass {
  private Logger logger = Logger.getLogger("InsureonTest");

  private HomePageObject hpo;
  private CyberLiabilityPageObject clp;
  private MidOfAppLoginPage mop;
  private CheckBrokenLinksPage blp;

  @BeforeTest
  public void initializeBrowser () throws IOException {
    _webdriver = initializeDriver();
    BasicConfigurator.configure();
    _webdriver.get(properties.getProperty("url"));
  }

  @Test
  public void homePageNavigation () throws InterruptedException {

    hpo = new HomePageObject(_webdriver);
    clp = new CyberLiabilityPageObject(_webdriver);
    mop = new MidOfAppLoginPage(_webdriver);
    blp = new CheckBrokenLinksPage(_webdriver);

    hpo.hover1();
    hpo.hover2();
    hpo.hover3();
    hpo.dropDown1();
    hpo.dropDown2();
    hpo.scrollDownAndUp();
    hpo.cyberLiabilityButtonClick();//done with first page

    clp.carriersLogoImage();
    clp.clickLogInButton();//done with second page

    mop.enterInEmailField();
    mop.enterPasswordField();
    mop.getQuotesButton();//done with third page

    blp.clickOnLogoButton();
    hpo.takeScreenshot();
    hpo.closeTab();
    Thread.sleep(700);
    SwitchFocus(_webdriver);
    blp.closeTab();
    Thread.sleep(700);
    SwitchFocus(_webdriver);
  }

  @Test
  public void verifyTitle () throws InterruptedException {
    logger.warn("The title is: " + _webdriver.getTitle());
    clp.closeTab();
    Thread.sleep(700);
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
      InsureonTest it = new InsureonTest(); // using reflections
      Class cl = it.getClass();
      //print the class name
      //counting total methods in the class and printing out each method's name or name and signature of the class
      Method[] methods = cl.getDeclaredMethods();
      System.out.println("Total methods passed: " + methods.length);//count of methods
      for (Method temp : methods) {//printing the methods names
        System.out.println("Method: " + temp.getName() + " --->>> has passed");//name only
      }
      System.out.println("Class named " + getClass().getSimpleName() + " --->>> has passed");
    }
  }

  @AfterTest
  public void closeBrowser () {
    _webdriver.quit();
    _webdriver = null;//to save memory
    }
  }

