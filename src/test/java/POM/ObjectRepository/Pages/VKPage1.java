package POM.ObjectRepository.Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class VKPage1 {
  WebDriver _webdriver;

  public VKPage1(WebDriver _webdriver) {
    this._webdriver = _webdriver;
  }

  private By emailField = By.id("email");
  private By passwordField = By.id("pass");
  private By loginButton = By.id("u_0_b");

  public WebElement enterEmail () {
    return _webdriver.findElement(emailField);
  }

  public WebElement enterPassword () {
    return _webdriver.findElement(passwordField);
  }

  public WebElement pressLoginButton () {
    return _webdriver.findElement(loginButton);
  }

}
