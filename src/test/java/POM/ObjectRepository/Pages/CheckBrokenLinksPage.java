package POM.ObjectRepository.Pages;

import POM.ObjectRepository.BaseClass;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import javax.management.remote.rmi._RMIConnection_Stub;
import java.util.List;

public class CheckBrokenLinksPage extends BaseClass {
  WebDriver _webdriver;

  public CheckBrokenLinksPage(WebDriver _webdriver) {
    this._webdriver = _webdriver;
    PageFactory.initElements(_webdriver, this);//this line if only when using PageFactory approach (@FindBy)
  }


  @FindBy(xpath = "//img[@alt='Insureon logo']")
  private WebElement insureonLogo;

  public void clickOnLogoButton () {
    insureonLogo.click();
    SwitchFocus(_webdriver);
  }

  public void closeTab () {
    _webdriver.close();
  }
}

