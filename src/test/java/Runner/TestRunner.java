package Runner;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
        features = "src/test/resources/Features",
        glue = "stepDefinitions",
        tags = "@TC_001",
        plugin = {"pretty", "html:target/cucumber-reports.html"}
)
public class TestRunner
{

}
