package Utilities;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;


public class Configuration
{
    Properties prop = new Properties();

    public Configuration()
    {

    }
    public void Config() throws IOException {
        try {
            prop.load(new FileInputStream("src/test/resources/config.properties"));
        }
        catch (FileNotFoundException e)
        {
            throw new FileNotFoundException(e.getMessage());
        }
        String BrowserOption = prop.getProperty("BrowserOption");
        String BrowserSetSize = prop.getProperty("BrowserSetSize");
        String ClearCookies = prop.getProperty("ClearCookies");
        System.out.println("The selected browser option is "+BrowserOption);
        if(BrowserOption!= null && BrowserSetSize!=null && BrowserOption.length()>0 && BrowserSetSize.length()>0)
        {
            WebDriverSingleton.setBrowser(BrowserOption);
            SetBrowserSize(BrowserSetSize);
            ClearBrowserCookies(ClearCookies);
        }

    }

    public void ClearBrowserCookies(String option)
    {
        if(option.equalsIgnoreCase("Yes"))
        {
            WebDriverSingleton.getDriver().manage().deleteAllCookies();
            System.out.println("All cookies deleted successfully!");
        }
    }
    public void SetBrowserSize(String option)
    {
        if(option.equalsIgnoreCase("Maximized") || option.equalsIgnoreCase("Maximum"))
        {
            WebDriverSingleton.getDriver().manage().window().maximize();
        }
        else if (option.equalsIgnoreCase("Minimized") || option.equalsIgnoreCase("Minimum"))
        {
            WebDriverSingleton.getDriver().manage().window().minimize();
        }
        else if(option.equalsIgnoreCase("Full Screen") || option.equalsIgnoreCase("FullScreen"))
        {
            WebDriverSingleton.getDriver().manage().window().fullscreen();
        }
    }
}
