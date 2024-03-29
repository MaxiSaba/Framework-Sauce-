package sauce.StepDefinitions;

import static config.WebBaseConfigProperties.getCurrentPath;

import config.ConfigDriver;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import io.cucumber.testng.AbstractTestNGCucumberTests;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import org.apache.commons.io.FileUtils;
import org.json.simple.JSONObject;
import org.openqa.selenium.*;
import org.testng.log4testng.Logger;

public class Hooks extends AbstractTestNGCucumberTests {
  public static WebDriver driver;
  public static Map<String, String> mainWindowsHandle = new HashMap<>();
  Logger log = Logger.getLogger(Hooks.class);
  Scenario scenario = null;

  public static JSONObject testData = new JSONObject();

  @Before("@WebTesting")
  public void initWebDriver(Scenario scenario) throws Exception {
    log.info(
        "*****************************************************************************************************");
    log.info("[ Configuration ] - Initializing driver configuration");
    log.info(
        "*****************************************************************************************************");
    driver = ConfigDriver.initWebConfig();
    mainWindowsHandle.put("main", driver.getWindowHandle());
    this.scenario = scenario;
    log.info(
        "*****************************************************************************************************");
    log.info("[ Scenario ] - " + scenario.getName());
    log.info(
        "*****************************************************************************************************");
  }

  private void takeScreenShot(Scenario scenario) {
    try {
      scenario.log("Scenario failed.");
      byte[] screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
      scenario.attach(
          screenshot, "image/png", scenario.getId() + "_" + scenario.getName().replace(" ", "_"));
      File destFile =
          new File(
              getCurrentPath()
                  + "/src/test/resources/screenshots/"
                  + scenario.getId()
                  + scenario.getName()
                  + ".jpg");
      FileUtils.copyFile(((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE), destFile);
    } catch (WebDriverException | IOException somePlatformsDontSupportScreenshots) {
      System.err.println(somePlatformsDontSupportScreenshots.getMessage());
    }
  }

  @After("@WebTesting")
  /** Embed a screenshot in test report if test is marked as failed */
  public void embedScreenshot(Scenario scenario) {
    if (scenario.isFailed()) {
      takeScreenShot(scenario);
    }

    log.info(
        "*****************************************************************************************************");
    log.info("[ WebDriver Status ] - Clean and close the instance of the driver");
    log.info(
        "*****************************************************************************************************");
    driver.quit();
  }

  @After("@WebTesting")
  private void killChromeDriverProcess() {
    log.info("[ Driver Status ] - Clean and close the instance of the driver");
    try {
      Runtime.getRuntime().exec("taskkill /F /IM chromedriver.exe");
      Runtime.getRuntime().exec("taskkill /F /IM chrome.exe");
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
  }
}
