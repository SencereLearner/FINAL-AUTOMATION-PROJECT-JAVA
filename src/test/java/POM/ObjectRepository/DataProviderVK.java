package POM.ObjectRepository;

public class DataProviderVK {

  @org.testng.annotations.DataProvider(name = "LoginDataProviderVK")
  public Object [][] getData () {
    return new Object[][]{
            {"login1VK", "password1"},
            {"login2K", "password3"},
            {"login3K", "password3"},

    };
  }
}
