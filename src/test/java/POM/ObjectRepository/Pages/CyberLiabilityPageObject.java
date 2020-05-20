package POM.ObjectRepository.Pages;

import POM.ObjectRepository.BaseClass;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

import javax.management.remote.rmi._RMIConnection_Stub;

public class CyberLiabilityPageObject extends BaseClass {
  public WebDriver _webdriver;
  private Actions actions;

  public CyberLiabilityPageObject(WebDriver _webdriver) {
    this._webdriver = _webdriver;
    actions = new Actions(this._webdriver);
  }

  private By carriersLogoImage = By.xpath("(//div[@class='copy-article']//img)[2]");
  private By logInButton = By.linkText("Log in");

  public void carriersLogoImage () {
    FlashingElement(_webdriver, _webdriver.findElement(carriersLogoImage));
    changeColor("green", _webdriver.findElement(carriersLogoImage), _webdriver);
  }

  public void clickLogInButton () throws InterruptedException {
    actions.moveToElement(_webdriver.findElement(logInButton)).keyDown(Keys.COMMAND).click().build().perform();
    SwitchFocus(_webdriver);
  }

  public void closeTab () {
    _webdriver.close();
  }

}
