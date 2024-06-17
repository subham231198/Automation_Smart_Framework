package Pages;

import Utilities.ConfigReader;
import Utilities.WebDriverSingleton;
import io.cucumber.java.et.Ja;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import stepDefinitions.Hooks;

import java.io.IOException;
import java.time.Duration;
import java.util.Locale;

public class CommonMethods
{
    WebDriver driver;
    WebDriverWait wait;
    JavascriptExecutor js;
    ConfigReader cr;

    public CommonMethods() {
        this.driver = WebDriverSingleton.getDriver();
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(20));
        this.js = (JavascriptExecutor) driver;
        this.cr = new ConfigReader();
    }

    public void launchApp(String appName) throws InterruptedException {
        if(appName!=null && appName.length()>0)
        {
            if(appName.toUpperCase().equals("MORGAN STANLEY"))
            {
                driver.get("https://tier2.mydesk.morganstanley.com/logon/LogonPoint/mydesk.html#/login");
                pageTitleValidation();
            }
            else if(appName.toUpperCase().equals("FACEBOOK"))
            {
                driver.get("https://www.google.com");
                pageTitleValidation();
            }
            else if(appName.toUpperCase().equals("AMAZON"))
            {
                driver.get("https://www.amazon.in");
                pageTitleValidation();
            }
            else
            {
                System.out.println("Invalid application name!");
                driver.close();
            }
        }
    }
    public boolean isElementVisible(WebElement element)
    {
//        boolean isEleDisplayed = (boolean) js.executeScript("return (arguments[0].offsetParent !== null) && "+"(arguments[0].offsetWidth > 0 || arguments[0].offsetHeight > 0);", element);
        boolean isEleDisplayed = element.isDisplayed();
        if(isEleDisplayed)
        {
            return true;
        }
        else
        {
            return false;
        }
    }
    public void scrollToElement(WebElement element)
    {
        js.executeScript("return arguments[0].scrollIntoView(true);", element);
    }
    public void click_on_element(String element, String page) throws IOException
    {
        try
        {
            WebElement web_element = cr.getElement(element, page);
            if(isElementVisible(web_element))
            {
                scrollToElement(web_element);
                wait.until(ExpectedConditions.elementToBeClickable(web_element));
                web_element.click();
            }
        }
        catch (TimeoutException e)
        {
            System.out.println("TimeOutException: " +e.getMessage());
            driver.close();
        }
        catch (ElementClickInterceptedException e)
        {
            System.out.println("ElementClickInterceptedException: " +e.getMessage());
            driver.close();
        }
        catch (ElementNotInteractableException e)
        {
            System.out.println("ElementNotInteractableException: " +e.getMessage());
            driver.close();
        }
    }
    public void enter_input_element(String value, String element, String page) throws IOException, InterruptedException {
        try
        {
            WebElement webElement = cr.getElement(element, page);
            if(isElementVisible(webElement))
            {
//                scrollToElement(webElement);
                Thread.sleep(5000);
                webElement.sendKeys(value);
            }
        }
        catch (TimeoutException e)
        {
            System.out.println("TimeOutException: " +e.getMessage());
            driver.close();
        }
        catch (ElementNotInteractableException e)
        {
            System.out.println("ElementNotInteractableException: " +e.getMessage());
            driver.close();
        }
    }
    public void select_from_dropdown(String value, String element, String page) throws IOException, InterruptedException {
        try
        {
            WebElement web_element = cr.getElement(element, page);
            if(isElementVisible(web_element))
            {
                scrollToElement(web_element);
                Select select = new Select(web_element);
                wait.until(ExpectedConditions.elementToBeClickable(web_element));
                web_element.click();
                wait.until(ExpectedConditions.elementToBeSelected(web_element));
                Thread.sleep(5000);
                select.selectByVisibleText(value);
            }
        }
        catch (TimeoutException e)
        {
            System.out.println("TimeOutException: " +e.getMessage());
            driver.close();
        }
        catch (ElementNotInteractableException e)
        {
            System.out.println("ElementNotInteractableException: " +e.getMessage());
            driver.close();
        }
    }
    public void pageTitleValidation() throws InterruptedException {
        String title = this.driver.getTitle();
        Thread.sleep(5000);
        if(title!=null && title.length()>0)
        {
            System.out.println("Page Title = "+title);
        }
        else
        {
            throw new InterruptedException("Cannot validate page title");
        }
    }

    public void validateText(String expected, String element, String page) throws IOException {
        try {
            WebElement web_element = cr.getElement(element, page);
            if(isElementVisible(web_element))
            {
                String text = web_element.getText();
                if(text!=null && text.length()>0 && text.equals(expected))
                {
                    System.out.println("The text "+text+" is displayed on "+page+". (Validated)");
                }
                else
                {
                    System.out.println("Could not validate "+text+" element!");
                }
            }
        }
        catch (TimeoutException e)
        {
            System.out.println("TimeOutException: " +e.getMessage());
            driver.close();
        }
        catch (ElementNotInteractableException e)
        {
            System.out.println("ElementNotInteractableException: " +e.getMessage());
            driver.close();
        }
    }
}
