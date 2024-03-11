package config;

import io.github.bonigarcia.wdm.WebDriverManager;
import java.util.HashMap;
import java.util.Map;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.log4testng.Logger;

public class WebDriverFactory extends WebBaseConfigProperties {
  /** **** Log Attribute ******* */
  private static Logger log = Logger.getLogger(WebDriverFactory.class);

  private WebDriverFactory() {}

  public static WebDriver createNewDriver(String platform) throws Exception {
    WebDriver driver;

    if (!"FIREFOX_LOCAL".equalsIgnoreCase(platform) && !"FIREFOX".equalsIgnoreCase(platform)) {
      log.info("Creating session using maxi saba");
    }

    if ("FIREFOX_LOCAL".equalsIgnoreCase(platform)) {
      setFireFoxLocalDriver();
      driver = new FirefoxDriver();

    } else if ("CHROME".equalsIgnoreCase(platform)) {
      setupChromeDriver();
      driver = new ChromeDriver();

    } else if ("CHROME_LOCAL".equalsIgnoreCase(platform)) {
      setupLocalChromeDriver();
      driver = new ChromeDriver();

    } else {
      log.debug("The Driver is not selected properly, invalid name: " + platform);
      return null;
    }

    return driver;
  }

  private static void setupFirefoxDriver() {
    WebDriverManager.firefoxdriver().clearResolutionCache().forceDownload().setup();
  }

  private static void setupChromeDriver() {
    WebDriverManager.chromedriver().clearResolutionCache().forceDownload().setup();
    setChromePreferences();
  }

  private static void setupLocalChromeDriver() {
    System.setProperty(
            "webdriver.chrome.driver",
            getCurrentPath() + "\\src\\test\\resources\\bin\\windows32\\chromedriver.exe");
    setChromePreferences();
  }

  private static void setFireFoxLocalDriver() {
    System.setProperty(
            "webdriver.gecko.driver",
            getCurrentPath() + "\\src\\test\\resources\\bin\\windows32\\geckodriver.exe");
  }

  private static void setChromePreferences() {
    Map<String, Object> prefs = new HashMap<String, Object>();
    ChromeOptions options = new ChromeOptions();
    prefs.put(
            "download.default_directory", getCurrentPath() + "\\src\\test\\resources\\downloads");
    prefs.put("download.prompt_for_download", false);
    options.setExperimentalOption("prefs", prefs);
    options.addArguments("--no-sandbox", "--disable-dev-shm-usage");
  }
}
