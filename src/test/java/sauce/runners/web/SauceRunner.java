package sauce.runners.web;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

@CucumberOptions(
    tags = "@Sauce and not @Ignore",
    features = "src/test/resources/features/sauce",
    glue = "sauce.StepDefinitions",
    plugin = {
      "pretty",
      "html:test-output",
      "json:target/cucumber/cucumber.json",
      "html:target/cucumber-html-report.html"
    })
public class SauceRunner extends AbstractTestNGCucumberTests {}
