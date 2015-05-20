package com.thinkanalytically.tagalyzer.impl;

import java.net.URL;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;

import org.apache.log4j.Logger;
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

import com.thinkanalytically.tagalyzer.CrawledWebPage;
import com.thinkanalytically.tagalyzer.ScriptableBrowser;

public class SeleniumChromeBrowser implements ScriptableBrowser {
	
	private String _proxyHost;
	private int _proxyPort;
	private WebDriver _driver;
	private JavascriptExecutor _driverJs;
	
	private Logger _logger;
	
	public SeleniumChromeBrowser() {
		
	}
	
	public SeleniumChromeBrowser(String proxyHost, int proxyPort) {
		this._proxyHost = proxyHost;
		this._proxyPort = proxyPort;
		_logger = Logger.getLogger(this.getClass());
	}

	public String get_proxyHost() {
		return _proxyHost;
	}

	public void set_proxyHost(String _proxyHost) {
		this._proxyHost = _proxyHost;
	}

	public int get_proxyPort() {
		return _proxyPort;
	}

	public void set_proxyPort(int _proxyPort) {
		this._proxyPort = _proxyPort;
	}

	public void startup() {
		String PROXY = _proxyHost + ":" + _proxyPort;
		org.openqa.selenium.Proxy proxy = new org.openqa.selenium.Proxy();
		proxy.setHttpProxy(PROXY).setFtpProxy(PROXY).setSslProxy(PROXY);
		
		DesiredCapabilities caps = DesiredCapabilities.chrome();
        LoggingPreferences logPrefs = new LoggingPreferences();
        logPrefs.enable(LogType.BROWSER, Level.ALL);
        caps.setCapability(CapabilityType.LOGGING_PREFS, logPrefs);
        caps.setCapability(CapabilityType.PROXY, proxy);
        _driver = new ChromeDriver(caps);
        _driverJs = (JavascriptExecutor) _driver;
	}
	
	public CrawledWebPage getPage(String uri) {
		CrawledWebPage responsePage = new CrawledWebPage();
		_driver.navigate().to(uri);
		List<WebElement> scripts = _driver.findElements(By.tagName("script"));
		for(WebElement script : scripts) {
			try {
				_driverJs.executeScript(script.getText());
			}
			catch(Exception ex) {
				_logger.warn("Error executing javascript!", ex);
			}
		}
		responsePage.set_pageTitle(_driver.getTitle());
		responsePage.set_pageContent(_driver.getPageSource());
		responsePage.set_pageURL(_driver.getCurrentUrl());
		return responsePage;
	}
	
	public void quit() {
		_driver.quit();
	}

}
