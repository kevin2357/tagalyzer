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
				_logger.debug("Error executing javascript!" + ex.getMessage());
			}
		}
		responsePage.set_pageTitle(_driver.getTitle());
		responsePage.set_pageContent(_driver.getPageSource());
		responsePage.set_pageURL(_driver.getCurrentUrl());
		
		String pageSource = _driver.getPageSource();
		List<WebElement> bodyTags = _driver.findElements(By.tagName("body"));
		responsePage.set_hasBodyTag(bodyTags.size()>0);
		responsePage.set_hasSmartyCompileText(pageSource != null && pageSource.toUpperCase().contains("SMARTY") && pageSource.toUpperCase().contains("COMPILE") );
		responsePage.set_hasDigitalDataJson(pageSource != null && pageSource.toUpperCase().contains("VAR DIGITALDATA=") );
		responsePage.set_hasCorrectSatelliteLib(pageSource != null && pageSource.toUpperCase().contains("assets.adobedtm.com/acf4fc818357d9e20cf038350a69714b00ae2a44/satelliteLib-1af53d57402476e51191c3769b04b63017440a84".toUpperCase() ) );
		responsePage.set_hasLegacyTranslation(pageSource != null && pageSource.toUpperCase().contains("s.account = s_account;".toUpperCase() ) );
		responsePage.set_hasSatellitPageBottom(pageSource != null && pageSource.toUpperCase().contains("satellite.page_bottom".toUpperCase() ) );
		
		String str = pageSource.toUpperCase();
		String findStr = "assets.adobedtm".toUpperCase();
		int lastIndex = 0;
		int count =0;
		while(lastIndex != -1){
		       lastIndex = str.indexOf(findStr,lastIndex);
		       if( lastIndex != -1){
		             count ++;
		             lastIndex+=findStr.length();
		      }
		}
		responsePage.set_satelliteLibCount(count);
		
		findStr = "satellite.page_bottom".toUpperCase();
		lastIndex = 0;
		count =0;
		while(lastIndex != -1){
		       lastIndex = str.indexOf(findStr,lastIndex);
		       if( lastIndex != -1){
		             count ++;
		             lastIndex+=findStr.length();
		      }
		}
		responsePage.set_satellitePageBottomCount(count);
		
		findStr = "var digitalData=".toUpperCase();
		lastIndex = 0;
		count =0;
		while(lastIndex != -1){
		       lastIndex = str.indexOf(findStr,lastIndex);
		       if( lastIndex != -1){
		             count ++;
		             lastIndex+=findStr.length();
		      }
		}
		responsePage.set_digitalDataJsonCount(count);

		return responsePage;
	}
	
	public void quit() {
		_driver.quit();
	}

}
