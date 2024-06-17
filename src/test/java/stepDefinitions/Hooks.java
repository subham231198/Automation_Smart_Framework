package stepDefinitions;

import Utilities.*;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import static org.bouncycastle.cert.DeltaCertificateTool.subject;

public class Hooks {
    public WebDriver driver;
    Scenario scenario;
    ScreenshotConfiguration screenshot;
    Configuration config = new Configuration();

    public Hooks()
    {

    }

    public Hooks(WebDriver driver, ScreenshotConfiguration screenshot) {
        this.driver = driver;
        this.screenshot = screenshot;
    }

    @Before
    public void initialSetup(Scenario scenario) {
        this.scenario = scenario;

        try {
            config.Config();
            driver = WebDriverSingleton.getDriver();
            driver.get("http://www.google.com");
            Thread.sleep(3000);
        } catch (Exception e) {
            System.out.println("Error during setup: " + e.getMessage());
        }
    }

    @After
    public void terminate() throws IOException, InterruptedException {
        WebDriver driver = WebDriverSingleton.getDriver();
        if (driver != null) {

            if (scenario.isFailed()) {
                MailerConfigReport report = new MailerConfigReport();


                screenshot.TakeScreenshot(scenario);
                System.out.println("Status: Failed.... Closing the browser!");
                driver.quit();

                WebDriverSingleton.resetDriver();

                Thread.sleep(5000);
                report.mailerReport(scenario);
            }
            else {
                MailerConfigReport report = new MailerConfigReport();


                System.out.println("Status: Passed...All steps have been executed!");
                System.out.println("-----Closing Web Browser----");
                driver.quit();
                WebDriverSingleton.resetDriver();

                Thread.sleep(5000);
                report.mailerReport(scenario);
            }
        }
    }

}