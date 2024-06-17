package Utilities;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import stepDefinitions.Hooks;

import java.io.FileInputStream;
import java.io.IOException;
import java.time.Duration;
import java.util.Properties;

public class ConfigReader
{
    WebDriver driver;
    WebDriverWait wait;
    public ConfigReader()
    {
        this.driver = WebDriverSingleton.getDriver();
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(20));
    }
    public WebElement getElement(String element, String page) throws IOException {
        Properties prop = new Properties();
        prop.load(new FileInputStream("src/test/resources/PageObject/"+page+".properties"));
        String value = prop.getProperty(element);
        String[] parts = value.split(">", 2);
        if(parts.length == 2 && parts[0].trim().equalsIgnoreCase("XPath") || parts[0].trim().equalsIgnoreCase("TagName") || parts[0].trim().equalsIgnoreCase("CssSelector") || parts[0].trim().equalsIgnoreCase("LinkedText") || parts[0].trim().equalsIgnoreCase("ClassName") || parts[0].trim().equalsIgnoreCase("ID") || parts[0].trim().equalsIgnoreCase("Name") || parts[0].trim().equalsIgnoreCase("PartialLinkedText"))
        {
            String ele =  parts[1].trim();
            if(parts[0].trim().equalsIgnoreCase("XPath"))
            {
                return wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(ele)));
            }
            else if (parts[0].trim().equalsIgnoreCase("TagName"))
            {
                return wait.until(ExpectedConditions.visibilityOfElementLocated(By.tagName(ele)));
            }
            else if (parts[0].trim().equalsIgnoreCase("CssSelector"))
            {
                return wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(ele)));
            }
            else if(parts[0].trim().equalsIgnoreCase("LinkedText"))
            {
                return wait.until(ExpectedConditions.visibilityOfElementLocated(By.linkText(ele)));
            }
            else if(parts[0].trim().equalsIgnoreCase("ClassName"))
            {
                return wait.until(ExpectedConditions.visibilityOfElementLocated(By.className(ele)));
            }
            else if(parts[0].trim().equalsIgnoreCase("ID"))
            {
                return wait.until(ExpectedConditions.visibilityOfElementLocated(By.id(ele)));
            }
            else if(parts[0].trim().equalsIgnoreCase("Name"))
            {
                return wait.until(ExpectedConditions.visibilityOfElementLocated(By.name(ele)));
            }
            else if(parts[0].trim().equalsIgnoreCase("PartialLinkedText"))
            {
                return wait.until(ExpectedConditions.visibilityOfElementLocated(By.partialLinkText(ele)));
            }
        }
        return null;
    }
}
