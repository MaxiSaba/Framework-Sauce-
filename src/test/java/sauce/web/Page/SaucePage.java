package udemy.web.Page;

import config.WebDriverHelper;
import org.testng.Assert;
import org.testng.log4testng.Logger;
import udemy.web.PageObjects.SaucePageObjects;

public class SaucePage extends WebDriverHelper {

  private static Logger log = Logger.getLogger(SaucePage.class);

  SaucePageObjects saucePageObjects = new SaucePageObjects();
  private static final int EXPLICIT_TIMEOUT = 20;

  public void fillLoginFieldsAndClickLogin(String username, String password) {
    webSendKeys(saucePageObjects.USERNAME_LOC, username);
    webSendKeys(saucePageObjects.PASSWORD_LOC, password);
    webClick(saucePageObjects.LOGIN_BUTTON);
  }

  public void validateLogin() {
    boolean shoppingCart = isWebElementDisplayed(saucePageObjects.SHOPPING_CART);
    Assert.assertTrue(shoppingCart, "User is not logged");
  }
}
