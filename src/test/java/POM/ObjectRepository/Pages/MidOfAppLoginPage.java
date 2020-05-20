package POM.ObjectRepository.Pages;

import POM.ObjectRepository.BaseClass;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import javax.management.remote.rmi._RMIConnection_Stub;


public class MidOfAppLoginPage extends BaseClass {
  WebDriver _webdriver;
  private Actions actions;

  public MidOfAppLoginPage(WebDriver _webdriver) {
    this._webdriver = _webdriver;
    actions = new Actions(this._webdriver);
    PageFactory.initElements(_webdriver, this);
  }

  private By emailField  = By.xpath("//input[@field='email']");
  private By enterPassword = By.id("password");
  @FindBy(xpath = "//*[text()='Start a Quote']")
  private WebElement getQuotesButton;


  public WebElement enterInEmailField () {
    HighlightElementYellowColor(_webdriver, _webdriver.findElement(emailField));
    return _webdriver.findElement(emailField);
  }

  public WebElement enterPasswordField () {
    HighlightElementBlueColor(_webdriver, _webdriver.findElement(enterPassword));
    return _webdriver.findElement(enterPassword);
  }

  public void getQuotesButton () {
    actions.moveToElement(getQuotesButton).keyDown(Keys.COMMAND).click().build().perform();
    SwitchFocus(_webdriver);
  }

  public void closeTab () {
    _webdriver.close();
  }
}
