package sauce.web.Page;

import config.WebDriverHelper;
import org.testng.Assert;
import org.testng.log4testng.Logger;
import sauce.web.PageObjects.SaucePageObjects;

public class SaucePage extends WebDriverHelper {

    private static Logger log = Logger.getLogger(SaucePage.class);

    SaucePageObjects saucePageObjects = new SaucePageObjects();
    private static final int EXPLICIT_TIMEOUT = 20;

    public void fillLoginFields(String username, String password) {
        webSendKeys(saucePageObjects.USERNAME_TXT, username);
        webSendKeys(saucePageObjects.PASSWORD_TXT, password);
    }

    public void validateLogin() {
        boolean shoppingCart = isWebElementDisplayed(saucePageObjects.SHOPPING_CART_BTN);
        Assert.assertTrue(shoppingCart, "User is not logged");
    }

    public void validateTheLockedMessage() {
        boolean lockedMessage = isWebElementDisplayed(saucePageObjects.LOCKED_MSG);
        Assert.assertTrue(lockedMessage, "Locked user is logged in");
    }

    public void validateTheWithoutCredentialsMessage() {
        webClick(saucePageObjects.LOGIN_BTN);
        boolean withoutCredentialsMessage =
                isWebElementDisplayed(saucePageObjects.WITHOUT_CREDENTIALS_MSG);
        Assert.assertTrue(withoutCredentialsMessage, "The message is incorrect");
    }

    public void addProductToTheCart() {
        webClick(saucePageObjects.BIKE_LIGHT_IMG);
        webClick(saucePageObjects.ADD_TO_CART_DETAIL);
    }

    public void verifyProductIntheCart() {
        webClick(saucePageObjects.SHOPPING_CART_BTN);
        boolean producto = isElementDisplayed(saucePageObjects.PRODUCT_IN_CART);
        Assert.assertTrue(producto, "Product is not in the cart");
    }

    public void verifyTheProductDetailPage() {
        boolean removeButtonIsPresent =
                isWebElementDisplayed(saucePageObjects.REMOVE_BTN);
        Assert.assertTrue(removeButtonIsPresent, "The message is incorrect");
    }

    public void verifyProductDoesntExistInTheCart() {
        boolean addButton =
                isElementDisplayed(saucePageObjects.PRODUCT_IN_CART);
        Assert.assertFalse(addButton, "Product is present in the cart");
    }
}

