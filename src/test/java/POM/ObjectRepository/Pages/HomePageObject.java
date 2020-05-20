package POM.ObjectRepository.Pages;

import POM.ObjectRepository.BaseClass;
import POM.ObjectRepository.TestNGListeners;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Listeners;

import java.util.List;

public class HomePageObject extends BaseClass {

  public WebDriver _webdriver;
  private Actions actions;

  public HomePageObject(WebDriver _webdriver) {
    this._webdriver = _webdriver;
    actions = new Actions(this._webdriver);
  }

  private By hover1 = By.linkText("Insurance Policies");
  private By hover2 = By.linkText("Industries");
  private By hover3 = By.linkText("Small Business Resources");
  private By cyberLiability = By.linkText("Cyber Liability");
  private By insurancePoliciesColumn1 = By.xpath("(//div[@class='styles_column__2qmpJ'])[1]/a");
  private By insurancePoliciesColumn2 = By.xpath("(//div[@class='styles_column__2qmpJ'])[2]/a");
  private By insurancePoliciesColumn3 = By.xpath("(//div[@class='styles_column__2qmpJ'])[3]/a");
  private By dropDown1ClickOn = By.xpath("(//div[@class='styles_base__e6krX']//select)[1]");
  private By selectOptionFromDopDown1 = By.xpath("//*[text()='Accounting & Finance Professionals']");
  private By dropDown1 = By.xpath("(//div[@class='styles_base__e6krX']//select)[1]/option");

  public void hover1() throws InterruptedException {
    actions.moveToElement(_webdriver.findElement(hover1)).build().perform();
    List<WebElement> column1 = _webdriver.findElements(insurancePoliciesColumn1);
    for (WebElement text : column1) {
      System.out.println("Present options are ---> " + text.getAttribute("innerHTML"));
    }
    Assert.assertEquals(column1.size(), 4, "Count doesn't match");
    Thread.sleep(700);
  }

  public void hover2() throws InterruptedException {
    actions.moveToElement(_webdriver.findElement(hover2)).build().perform();
    List<WebElement> column1 = _webdriver.findElements(insurancePoliciesColumn2);
    for (WebElement text : column1) {
      System.out.println("Present options are ---> " + text.getAttribute("innerHTML"));
    }
    Assert.assertEquals(column1.size(), 4, "Count doesn't match");
    Thread.sleep(700);
  }

  public void hover3() throws InterruptedException {
    actions.moveToElement(_webdriver.findElement(hover3)).build().perform();
    List<WebElement> column1 = _webdriver.findElements(insurancePoliciesColumn3);
    for (WebElement text : column1) {
      System.out.println("Present options are ---> " + text.getAttribute("innerHTML"));
    }
    Assert.assertEquals(column1.size(), 4, "Count doesn't match");
    Thread.sleep(700);
  }

  public void dropDown1() {
    //_webdriver.findElement(dropDown1ClickOn).click();
    //_webdriver.findElement(selectOptionFromDopDown1).click();can't choose option from first drop down
    List<WebElement> dropDown1Options = _webdriver.findElements(dropDown1);
    for (WebElement text : dropDown1Options) {
      System.out.println("Drop Down 1 Options ---> " + text.getText());
    }
  }

  public void dropDown2() {
    WebElement drop2 = _webdriver.findElement(By.xpath("(//div[@class='styles_base__e6krX']//select)[2]"));
    Select select = new Select(drop2);
    //select.selectByVisibleText("Accounting & Auditing");
    List<WebElement> dropdown2Options = select.getOptions();
    for (WebElement text : dropdown2Options) {
      System.out.println("Drop Down 2 Options ---> " + text.getText());
    }
  }

  public void scrollDownAndUp() throws InterruptedException {
    ScrollDown(_webdriver);
    Thread.sleep(700);
    JavascriptExecutor jse6 = (JavascriptExecutor) _webdriver;
    jse6.executeScript("scroll(0,-10000)");
    Thread.sleep(700);
  }

  public void cyberLiabilityButtonClick() throws InterruptedException {
    actions.moveToElement(_webdriver.findElement(cyberLiability)).keyDown(Keys.COMMAND).click().build().perform();
    Thread.sleep(700);
    SwitchFocus(_webdriver);
  }

  public void verifyBrokenLinks () {

    List<WebElement> links=_webdriver.findElements(By.tagName("a"));

    System.out.println("Total links are "+links.size());

    for(int i=0;i<links.size();i++) {

      System.out.println(links.get(i).getText());

      WebElement ele= links.get(i);

      String url=ele.getAttribute("href");

      verifyLinkActive(url);
    }
  }

  public void takeScreenshot () throws InterruptedException {//not on fail, on demand
    ScreenshotTakenOnFail(_webdriver, getAlphaNumericString(7));
    CustomAlert(_webdriver, "SCREENSHOT HAS BEEN TAKEN");
    Thread.sleep(1700);
    _webdriver.switchTo().alert().accept();
  }

  public void closeTab () {
    _webdriver.close();
  }

}
