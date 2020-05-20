package POM.ObjectRepository.Pages;

import POM.ObjectRepository.BaseClass;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import java.io.File;
import java.io.FileInputStream;

public class InstagramHomePage extends BaseClass {

  WebDriver _webdriver;

  public InstagramHomePage(WebDriver _webdriver) {
    this._webdriver = _webdriver;
  }

  private By loginField = By.name("username");
  private By passwordField = By.name("password");

  public void setLoginField() throws Exception {
    _webdriver.findElement(loginField).sendKeys(readExcelSheet(0, 0, 0));
    Thread.sleep(700);
  }

  public void setPasswordField() throws Exception {
    _webdriver.findElement(passwordField).sendKeys(readExcelSheet(0, 0, 1));
    Thread.sleep(700);
  }
}
