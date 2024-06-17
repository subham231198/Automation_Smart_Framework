package Utilities;

import io.cucumber.java.Scenario;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class ScreenshotConfiguration
{
    WebDriver driver;
    private static final String screenshotDirectory = "Screenshots";
    public ScreenshotConfiguration()
    {
        this.driver = WebDriverSingleton.getDriver();
    }
    public void TakeScreenshot(Scenario scenario)
    {
        try {
            byte[] screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
            String screenshotName = getScreenshotName(scenario.getName());
            saveScreenshot(screenshot, screenshotName);
            scenario.attach(screenshot, "image/png", screenshotName);
        }
        catch (WebDriverException e)
        {
            System.err.println("Failed to capture screenshot: " + e.getMessage());
        }
    }
    private String getScreenshotName(String scenarioName) {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd_HH-mm-ss");
        LocalDateTime now = LocalDateTime.now();
        return scenarioName + "_" + dtf.format(now) + ".png";
    }
    private void saveScreenshot(byte[] screenshot, String fileName) {
        try {
            Path screenshotPath = Paths.get(screenshotDirectory, fileName);
            Files.write(screenshotPath, screenshot);
            System.out.println("Screenshot saved: " + screenshotPath);
        } catch (IOException e) {
            System.err.println("Failed to save screenshot: " + e.getMessage());
        }
    }
    private void createScreenshotDirectory() {
        try {
            File directory = new File(screenshotDirectory);
            if (!directory.exists()) {
                directory.mkdirs();
            }
        } catch (Exception e) {
            System.err.println("Failed to create screenshot directory: " + e.getMessage());
        }
    }
}
