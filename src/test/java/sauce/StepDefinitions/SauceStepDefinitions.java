package sauce.StepDefinitions;

import config.WebDriverHelper;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.WebDriver;
import org.testng.log4testng.Logger;
import sauce.web.Page.PageBase;
import sauce.web.Page.SaucePage;
import sauce.web.PageObjects.SaucePageObjects;

public class SauceStepDefinitions extends WebDriverHelper {
    static WebDriver driver;
    private static Logger log = Logger.getLogger(SauceStepDefinitions.class);

    SaucePageObjects saucePageObjects = new SaucePageObjects();

    private static PageBase basePage = new PageBase();
    private static SaucePage saucePage = new SaucePage();
    private String username = null;
    private String password = "secret_sauce";

    public SauceStepDefinitions() {
        driver = Hooks.driver;
    }

    @Given("user is on the login page")
    public void userIsOnTheLoginPage() {
        driver.manage().window().maximize();
        URL_BASE = getUrlBase();
        log.info("Navigating to: " + URL_BASE);
        driver.get(URL_BASE);
        mainWindowsHandle.put("main", driver.getWindowHandle());
        waitPageCompletelyLoaded();
    }

    @Then("^user enters (.*) and (.*)$")
    public void userEntersUsernameAndPassword(String username, String password) {
        saucePage.fillLoginFields(username, password);
    }

    @And("user should see the homepage")
    public void userShouldSeeTheHomepage() {
        saucePage.validateLogin();
    }

    @When("user should see the Epic sadface message")
    public void userShouldSeeTheEpicSadfaceMessage() {
        saucePage.validateTheLockedMessage();
    }

    @When("user should see the credentials are requided message")
    public void userShouldSeeTheCredentialsAreRequiredMessage() {
        saucePage.validateTheWithoutCredentialsMessage();
    }

    @When("user clicks the login button")
    public void userClicksTheLoginButton() {
        webClick(saucePageObjects.LOGIN_BTN);
    }

    @Given("^user is logged in with (.*) and (.*)$")
    public void userIsLoggedIn(String username, String password) {
        userIsOnTheLoginPage();
        userEntersUsernameAndPassword(username, password);
        userClicksTheLoginButton();
    }

    @When("user adds the product from the product detail page")
    public void userAddsTheProductFromTheProductDetailPage() {
        saucePage.addProductToTheCart();
    }

    @Then("the product should be added tho the cart")
    public void theProductShouldBeAddedThoTheCart() {
        saucePage.verifyProductIntheCart();
    }

    @When("user adds product to the cart")
    public void userHasAddedProductToTheCart() {
        webClick(saucePageObjects.ADD_TO_CART_LIST);
    }

    @And("user clicks on the product in the cart")
    public void userClicksOnTheProductInTheCart() {
        webClick(saucePageObjects.SHOPPING_CART_BTN);
        webClick(saucePageObjects.PRODUCT_IN_CART);
    }

    @Then("user should be taken to the product detail page")
    public void userShouldBeTakenToTheProductDetailPage() {
        saucePage.verifyTheProductDetailPage();
    }

    @And("user removes the product from the product detail page")
    public void userRemovesTheProductFromTheProductDetailPage() {
        userClicksOnTheProductInTheCart();
        webClick(saucePageObjects.REMOVE_BTN);
    }

    @Then("the product should be removed from the cart")
    public void theProductShouldBeRemovedFromTheCart() {
        webClick(saucePageObjects.SHOPPING_CART_BTN);
        saucePage.verifyProductDoesntExistInTheCart();
    }
}
