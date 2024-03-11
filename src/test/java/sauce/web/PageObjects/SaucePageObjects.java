package sauce.web.PageObjects;

import org.openqa.selenium.By;

public class SaucePageObjects {

  public static final By USERNAME_TXT = By.cssSelector("input[id='user-name']");
  public static final By PASSWORD_TXT = By.cssSelector("input[id='password']");
  public static final By LOGIN_BTN = By.cssSelector("input[id='login-button']");
  public static final By BIKE_LIGHT_IMG = By.cssSelector("img[alt='Sauce Labs Bike Light']");
  public static final By SHOPPING_CART_BTN = By.cssSelector("a[class='shopping_cart_link']");
  public static final By ADD_TO_CART_DETAIL = By.cssSelector("button[id='add-to-cart-sauce-labs-bike-light']");
  public static final By PRODUCT_IN_CART = By.cssSelector("div[class='inventory_item_name']");
  public static final By ADD_TO_CART_LIST = By.cssSelector("button[id='add-to-cart-sauce-labs-backpack']");
  public static final By REMOVE_BTN = By.cssSelector("button[id='remove-sauce-labs-backpack']");
  public static final By LOCKED_MSG =
      By.xpath("//h3[contains(text(),'Epic sadface: Sorry, this user has been locked out')]");
  public static final By WITHOUT_CREDENTIALS_MSG =
      By.xpath("//h3[contains(text(), Epic sadface: Username is required']");
}
