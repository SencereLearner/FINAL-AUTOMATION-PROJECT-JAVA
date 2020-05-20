package POM.ObjectRepository;

import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.io.FileHandler;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

public class BaseClass {

  public WebDriver _webdriver;
  // FIXME: 4/12/20 Parallel tests running will fail if WebDriver is static, because multiple tests will share one driver. It's only good for sequential run. For parallel tests, need to remove static.

  public Properties properties;
  ////////////////////////////////////////////////////////////////////////////////USED FOR FB TEST. CAN BE DELETED AFTER USE. IT'S A COPY OF initializeDriver METHOD
  public Properties properties2;

  public WebDriver initializeDriver2() throws IOException {

    properties2 = new Properties();
    FileInputStream fileInputStream = new FileInputStream(System.getProperty("user.dir") + "/src/test/java/Resources/globalData2.properties");
    properties2.load(fileInputStream);

    String browserToRun = properties2.getProperty("browserForFBTest");

    if (browserToRun.equalsIgnoreCase("chrome")) {
      System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir") + "/src/test/java/Resources/Drivers/chromedriver");
      _webdriver = new ChromeDriver();
    } else if (browserToRun.equalsIgnoreCase("firefox")) {
      System.setProperty("webdriver.gecko.driver", System.getProperty("user.dir") + "/src/test/java/Resources/Drivers/geckodriver");
      _webdriver = new FirefoxDriver();
    } else if (browserToRun.equalsIgnoreCase("internetExplorer")) {
      System.setProperty("webdriver.gecko.driver", System.getProperty("user.dir") + "/src/test/java/Resources/Drivers/IEDriver");
      _webdriver = new InternetExplorerDriver();
    }

    _webdriver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
    _webdriver.manage().timeouts().pageLoadTimeout(15, TimeUnit.SECONDS);
    return _webdriver;
  }

  ////////////////////////////////////////////////////////////////////////////////USED FOR FB TEST. CAN BE DELETED AFTER USE. IT'S A COPY OF initializeDriver METHOD
  public WebDriver initializeDriver() throws IOException {

    properties = new Properties();
    FileInputStream fileInputStream = new FileInputStream(System.getProperty("user.dir") + "/src/test/java/Resources/globalData.properties");
    properties.load(fileInputStream);

    String browserToRun = properties.getProperty("browser");

    if (browserToRun.contains("chrome")) {
      System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir") + "/src/test/java/Resources/Drivers/chromedriver");
      ChromeOptions options = new ChromeOptions();
      if (browserToRun.contains("headless")) {
        options.addArguments("headless");
      }
      _webdriver = new ChromeDriver(options);

    } else if (browserToRun.equalsIgnoreCase("firefox")) {
      System.setProperty("webdriver.gecko.driver", System.getProperty("user.dir") + "/src/test/java/Resources/Drivers/geckodriver");
      _webdriver = new FirefoxDriver();
    } else if (browserToRun.equalsIgnoreCase("internetExplorer")) {
      System.setProperty("webdriver.gecko.driver", System.getProperty("user.dir") + "/src/test/java/Resources/Drivers/IEDriver");
      _webdriver = new InternetExplorerDriver();
    }

    _webdriver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
    _webdriver.manage().timeouts().pageLoadTimeout(15, TimeUnit.SECONDS);
    return _webdriver;
  }

  public void verifyLinkActive(String linkUrl) {
    try {
      URL url = new URL(linkUrl);

      HttpURLConnection httpURLConnect = (HttpURLConnection) url.openConnection();

      httpURLConnect.setConnectTimeout(3000);

      httpURLConnect.connect();

      if (httpURLConnect.getResponseCode() == 200) {
        System.out.println(linkUrl + " - " + httpURLConnect.getResponseMessage());
      }
      if (httpURLConnect.getResponseCode() == HttpURLConnection.HTTP_NOT_FOUND) {
        System.out.println(linkUrl + " - " + httpURLConnect.getResponseMessage() + " - " + HttpURLConnection.HTTP_NOT_FOUND);
      }
    } catch (Exception e) {

    }
  }

  public void scrollToElement(WebDriver _webdriver, String xpath) {
    WebElement element = _webdriver.findElement(By.xpath(xpath));
    ((JavascriptExecutor) _webdriver).executeScript("arguments[0].scrollIntoView();", element);
  }

  public void newLink(WebDriver _webdriver, String xpath) {
    WebElement link2 = _webdriver.findElement(By.xpath(xpath));
    Actions newTab2 = new Actions(_webdriver);
    newTab2.keyDown(Keys.COMMAND).click(link2).keyUp(Keys.COMMAND).build().perform();
  }

  public void SwitchFocus(WebDriver _webdriver) {
    for (String winHandle : _webdriver.getWindowHandles()) {
      _webdriver.switchTo().window(winHandle);
    }
  }

  public void HighlightElementBlueColor(WebDriver _webdriver, WebElement element) {
    JavascriptExecutor js = (JavascriptExecutor) _webdriver;
    js.executeScript("arguments[0].setAttribute('style', 'background: blue; border: 2px solid red;');", element);
    try {
      Thread.sleep(800);
    } catch (InterruptedException e) {
      e.printStackTrace();
      System.out.println(e.getMessage());
    }
    js.executeScript("arguments[0].setAttribute('style', 'border: solid 2px white')", element);
  }

  public void HighlightElementYellowColor(WebDriver _webdriver, WebElement element) {
    JavascriptExecutor js = (JavascriptExecutor) _webdriver;
    js.executeScript("arguments[0].setAttribute('style', 'background: yellow; border: 2px solid red;');", element);
    try {
      Thread.sleep(800);
    } catch (InterruptedException e) {
      e.printStackTrace();
      System.out.println(e.getMessage());
    }
    js.executeScript("arguments[0].setAttribute('style', 'border: solid 2px white')", element);
  }

  //----------------------------THESE 2 METHODS ARE FOR FLASHING ELEMENTS------------------------
  public void FlashingElement(WebDriver _webdriver, WebElement element) {

    String initialColor = element.getCssValue("backgroundColor");
    for (int i = 0; i < 131; i++) {
      changeColor("rgb(0,250,0)", element, _webdriver);
      changeColor(initialColor, element, _webdriver);
    }
  }

  public void changeColor(String color, WebElement element, WebDriver _webdriver) {
    JavascriptExecutor js = ((JavascriptExecutor) _webdriver);
    js.executeScript("arguments[0].style.backgroundColor = '" + color + "'", element);
  }
  //----------------------------THESE 2 METHODS ARE FOR FLASHING ELEMENTS------------------------

  //CREATE A CUSTOM ALERT
  public void CustomAlert(WebDriver _webdriver, String message) {
    JavascriptExecutor js = (JavascriptExecutor) _webdriver;
    js.executeScript("alert('" + message + "');");
  }

  //CLICK ELEMENT WITH JAVA SCRIPT. THE SAME AS .CLICK();
  public void ClickElementUsingJavaScript(WebDriver _webdriver, WebElement element) {
    JavascriptExecutor js = ((JavascriptExecutor) _webdriver);
    js.executeScript("arguments[0].click();", element);
  }

  //REFRESH BROWSER
  public void RefreshBrowserUsingJavaScript(WebDriver _webdriver) {
    JavascriptExecutor js = ((JavascriptExecutor) _webdriver);
    js.executeScript("history.go(0)");
  }

  //GET PAGE TITLE. THE SAME AS .getTitle();
  public static String GetPageTitle(WebDriver _webdriver) {
    JavascriptExecutor js = ((JavascriptExecutor) _webdriver);
    String title = js.executeScript("return document.title;").toString();
    return title;
    //When call this method it has to be this way, otherwise will not print: System.out.println(AllMethodsClass.GetPageTitle(_webdriver));
  }

  //OPEN A WEB PAGE IN A NEW TAB
  public void OpenWebPageInNewTab(WebDriver _webdriver, String url) {
    ((JavascriptExecutor) _webdriver).executeScript("window.open(arguments[0])", url);
  }

  //GETTING SUBSTRING FROM A STRING (Save money by comparing business insurance quotes from multiple carriers)
  public static String GetSubstring(String text) {
    int start = text.indexOf("business") + "business".length();
    int end = text.substring(start).indexOf("from");
    return text.substring(start).substring(0, end).trim();
  }

  public void ScreenshotTakenOnFail(WebDriver _webdriver, String screenshotName) {
    TakesScreenshot ts = (TakesScreenshot) _webdriver;
    File source = ts.getScreenshotAs(OutputType.FILE);
    try {
      //FileHandler.copy(source, new File("/Users/firstlast/Desktop/TestScreenshots/" + screenshotName + ".png"));
      FileHandler.copy(source, new File(System.getProperty("user.dir") + "/src/test/java/Resources/FailedScreenshots/" + screenshotName + ".png"));

    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  public void HighlightElementBackgroundAndBorder(WebDriver _webdriver, WebElement element) {
    JavascriptExecutor js = (JavascriptExecutor) _webdriver;
    js.executeScript("arguments[0].setAttribute(‘style’, ‘background: blue; border: 2px solid red;’);", element);
//    the bottom code will unhighlight the fields, after being highlighted for half a second.
//    try {
//      Thread.sleep(500);
//    } catch (InterruptedException e) {
//      e.printStackTrace();
//      System.out.println(e.getMessage());
//    }
//    js.executeScript(“arguments[0].setAttribute(‘style’, ‘border: solid 2px white’)”, element);
  }


//  public void HighlightElementBorderOnly(WebDriver _webdriver, WebElement element){
//    JavascriptExecutor js = (JavascriptExecutor) _webdriver;
//    js.executeScript("arguments[0].setAttribute(‘style’, ‘background: blue; border: 2px solid red;’);", element);
////    the bottom code will unhighlight the fields, after being highlighted for half a second.
////    try {
////      Thread.sleep(500);
////    } catch (InterruptedException e) {
////      e.printStackTrace();
////      System.out.println(e.getMessage());
////    }
////    js.executeScript(“arguments[0].setAttribute(‘style’, ‘border: solid 2px white’)”, element);
//  }


  public void ScrollDown(WebDriver webdriver) {
    JavascriptExecutor jse6 = (JavascriptExecutor) webdriver;
    jse6.executeScript("scroll(0,10000)");
  }

  public void ScrollDownToLocateElement(WebDriver _webdriver, String xpath) {
    WebElement element = _webdriver.findElement(By.xpath(xpath));
    ((JavascriptExecutor) _webdriver).executeScript("arguments[0].scrollIntoView();", element);
    //Note: in case test fails here, make sure we look for elements by xpath only, as indicated here.
  }

  public static String CutText(String someText) {
    return someText.substring(20, 26).trim();
  }

  public void WaitUntilElementIsVisible(WebDriver webdriver, String xpath) {
    WebDriverWait wait = new WebDriverWait(webdriver, 5);
    wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath)));
  }
  //used for excel sheet which already exists and filled with data (say we created it manually)
  public String readExcelSheet(int sheet, int row, int column) throws Exception {
    //Specify excel file path
    File src = new File(System.getProperty("user.dir") + "/src/test/java/Resources/TestData.xlsx");
    //Load excel file
    FileInputStream fis = new FileInputStream(src);
    //Load workbook
    XSSFWorkbook wb = new XSSFWorkbook(fis);
    //Specify exact sheet, which we need to lead (index starts with zero)
    XSSFSheet sheet1 = wb.getSheetAt(sheet);
    //Specify which raw and column to use (index starts with zero)
    String excelData = sheet1.getRow(row).getCell(column).getStringCellValue();
    System.out.println("The excel sheet value from sheet " + sheet + " row " + row + " column " + column + " = " + excelData);
    //To avoid memory leak, it's a good practice to close the workbook, after using.
    wb.close();
    return excelData;
  }

  //GENERATE A RANDOM STRING (CAN BE USED FOR SCREENSHOT TAKING METHOD)
  public String getAlphaNumericString(int n) {
    // chose a Character random from a String
    String AlphaNumericString = "ABCDEFGHIJKLMNOPQRSTUVWXYZ"
            + "0123456789"
            + "abcdefghijklmnopqrstuvxyz";

    // create StringBuffer size of AlphaNumericString
    StringBuilder sb = new StringBuilder(n);

    for (int i = 0; i < n; i++) {

      // generate a random number between
      // 0 to AlphaNumericString variable length
      int index = (int)(AlphaNumericString.length() * Math.random());

      // add Character one by one in end of sb
      sb.append(AlphaNumericString.charAt(index));
    }
    return sb.toString();
  }

}
  
