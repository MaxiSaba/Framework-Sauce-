package udemy.StepDefinitions;

import config.WebDriverHelper;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import org.apache.commons.lang.StringUtils;
import org.openqa.selenium.WebDriver;
import org.testng.log4testng.Logger;
import udemy.web.Page.PageBase;
import udemy.web.Page.SaucePage;

public class SauceStepDefinitions extends WebDriverHelper {
  static WebDriver driver;
  private static Logger log = Logger.getLogger(SauceStepDefinitions.class);
  private static PageBase basePage = new PageBase();
  private static SaucePage saucePage = new SaucePage();
  private String username = null;
  private String password = "secret_sauce";

  public SauceStepDefinitions() {
    driver = Hooks.driver;
  }

  @Given("^I am in app main site$")
  public void iAmInAppMainSite() {
    driver.manage().window().maximize();
    URL_BASE = getUrlBase();
    log.info("Navigating to: " + URL_BASE);
    driver.get(URL_BASE);
    mainWindowsHandle.put("main", driver.getWindowHandle());
    waitPageCompletelyLoaded();
  }

  @Then("^I set (.*) credentials$")
  public void iFillTheValidCredentials(String credentials) {
    if (StringUtils.equalsIgnoreCase(credentials, "valid")) {
      username = "standard_user";
    } else if (StringUtils.equalsIgnoreCase(credentials, "locked")) {
      username = "locked_out_user";
    }

    saucePage.fillLoginFieldsAndClickLogin(username, password);
  }

  @And("I validate that user is logged in")
  public void iValidateThatUserIsLoggedIn() {
    saucePage.validateLogin();
  }
}
