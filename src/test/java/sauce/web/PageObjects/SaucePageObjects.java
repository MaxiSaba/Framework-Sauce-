package udemy.web.PageObjects;

import org.openqa.selenium.By;

public class SaucePageObjects {

  public static final By USERNAME_LOC = By.cssSelector("input[id='user-name']");
  public static final By PASSWORD_LOC = By.cssSelector("input[id='password']");
  public static final By LOGIN_BUTTON = By.cssSelector("input[id='login-button']");
  public static final By SHOPPING_CART = By.cssSelector("a[class='shopping_cart_link']");
}
