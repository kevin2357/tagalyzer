package com.thinkanalytically.tagalyzer;

import java.util.Date;
import java.util.logging.Level;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.logging.LogEntries;
import org.openqa.selenium.logging.LogEntry;
import org.openqa.selenium.logging.LogType;
import org.openqa.selenium.logging.LoggingPreferences;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.safari.SafariDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Selenium2Example {

	public static void main(String[] args) {

        // Create a new instance of the Firefox driver
        // Notice that the remainder of the code relies on the interface, 
        // not the implementation.
        //WebDriver driver = new FirefoxDriver();
		
		// Try ChromeDriver; note that this requires a property set which points 
		// to the chromium binary
		System.setProperty("webdriver.chrome.driver", "C:\\dev\\libs\\java\\chromedriver\\chromedriver.exe");
		DesiredCapabilities caps = DesiredCapabilities.chrome();
        LoggingPreferences logPrefs = new LoggingPreferences();
        logPrefs.enable(LogType.BROWSER, Level.ALL);
        caps.setCapability(CapabilityType.LOGGING_PREFS, logPrefs);
        WebDriver driver = new ChromeDriver(caps);
        
		// Try the IE driver
		//System.setProperty("webdriver.ie.driver", "C:\\dev\\libs\\java\\iedriver\\IEDriverServer.exe");
        //WebDriver driver = new InternetExplorerDriver();
		
		// Didn't try Safari...
		//WebDriver driver = new SafariDriver();
        
        JavascriptExecutor driverJs = (JavascriptExecutor) driver;

//        // And now use this to visit Google
//        driver.get("http://www.google.com");
//        // Alternatively the same thing can be done like this
//        // driver.navigate().to("http://www.google.com");
//
//        // Find the text input element by its name
//        WebElement element = driver.findElement(By.name("q"));
//
//        // Enter something to search for
//        element.sendKeys("Cheese!");
//
//        // Now submit the form. WebDriver will find the form for us from the element
//        element.submit();
//
//        // Check the title of the page
//        System.out.println("Page title is: " + driver.getTitle());
//        
//        // Google's search is rendered dynamically with JavaScript.
//        // Wait for the page to load, timeout after 10 seconds
//        (new WebDriverWait(driver, 10)).until(new ExpectedCondition<Boolean>() {
//            public Boolean apply(WebDriver d) {
//                return d.getTitle().toLowerCase().startsWith("cheese!");
//            }
//        });
//
//        // Should see: "cheese! - Google Search"
//        System.out.println("Page title is: " + driver.getTitle());
//        element.sendKeys(Keys.ESCAPE);
        
        driver.navigate().to("http://hbo.ktankersley.sf-devstore-01.shopthefilm.com/");
        Object dummy = driverJs.executeScript("_satellite.setDebug(true);");
        driver.get("http://hbo.ktankersley.sf-devstore-01.shopthefilm.com/");
        
        Object retval = driverJs.executeScript("return 'Hello, World!';");
        System.out.println(retval.toString());
        
        driverJs.executeScript("console.log('testme testme');");
        
        driverJs.executeScript("_satellite.notify('test2 test2',1);");
        
        LogEntries logEntries = driver.manage().logs().get(LogType.BROWSER);
        for (LogEntry entry : logEntries) {
            System.out.println(new Date(entry.getTimestamp()) + " " + entry.getLevel() + " " + entry.getMessage());
            //do something useful with the data
        }
        
        
        //Close the browser
        driver.quit();

	}

}
