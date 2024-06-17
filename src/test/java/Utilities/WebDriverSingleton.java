package Utilities;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;

public class WebDriverSingleton {
    private static WebDriver driver;
    private static String browser;
    private WebDriverSingleton()
    {

    }

    public static WebDriver getDriver() {
        if (driver == null) {
            if (browser == null) {
                throw new IllegalStateException("Browser is not set!");
            }
            if (browser.equalsIgnoreCase("Chrome")) {
                WebDriverManager.chromedriver().setup();
                driver = new ChromeDriver();
                System.out.println("---Successfully launched Chrome Browser---");
            } else if (browser.equalsIgnoreCase("Edge")) {
                WebDriverManager.edgedriver().setup();
                driver = new EdgeDriver();
                System.out.println("---Successfully launched Edge Browser---");
            } else {
                throw new IllegalStateException("Invalid browser option");
            }
        }
        return driver;
    }
    public static void setBrowser(String browserOption) {
        if (driver != null)
        {
            throw new IllegalStateException("Driver already initialized, can't change browser type");
        }
        browser = browserOption;
    }

    public static void resetDriver()
    {
        driver = null;
    }
}

