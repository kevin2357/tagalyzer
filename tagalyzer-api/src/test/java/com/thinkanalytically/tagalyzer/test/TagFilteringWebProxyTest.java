package com.thinkanalytically.tagalyzer.test;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

import com.thinkanalytically.tagalyzer.CrawledWebPage;
import com.thinkanalytically.tagalyzer.ScriptableBrowser;
import com.thinkanalytically.tagalyzer.Tag;
import com.thinkanalytically.tagalyzer.TagFilteringWebProxy;
import com.thinkanalytically.tagalyzer.impl.AdobeAnalyticsTag;
import com.thinkanalytically.tagalyzer.impl.LittleProxy;
import com.thinkanalytically.tagalyzer.impl.SeleniumChromeBrowser;

import org.junit.AfterClass;
import org.junit.After;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Before;
import org.junit.Test;

/**
 * Unit test for tag filtering proxy
 */
public class TagFilteringWebProxyTest 
{
	private static TagFilteringWebProxy _proxy;
	private static ScriptableBrowser _browser;
    
    @BeforeClass
    public static void globalSetup() throws Exception
    {
    	_proxy = new LittleProxy();
    	_proxy.addTagFilter(Pattern.compile(".*metrics.seenon.com/b/ss.*"));
    	_proxy.addTagFilter(Pattern.compile(".*smetrics.seenon.com/b/ss.*"));
    	_proxy.addTagFilter(Pattern.compile(".*omtrdc.net/b/ss.*"));
    	_proxy.addTagFilter(Pattern.compile(".*2o7.net/b/ss.*"));
    	//_proxy.addTagFilter(Pattern.compile("demdex.net"));
    	_proxy.startup(8089);
    	_proxy.clearInterceptedTags();
    	
    	_browser = new SeleniumChromeBrowser("localhost",8089);
    	_browser.startup();
    }
    
    @Before
    public void setUp() throws Exception
    {
    	;
    }
    
    @After
    public void tearDown() throws Exception {
    	TagFilteringWebProxyTest._proxy.clearInterceptedTags();
    }
    
    @AfterClass
    public static void globalTearDown() {
    	_proxy.shutdown();
    	_browser.quit();
    }

    @Test
    public void testUntaggedPage()
    {
        CrawledWebPage page = TagFilteringWebProxyTest._browser.getPage("https://www.google.com");
        List<Tag> tags = new ArrayList<Tag>();
        for(String tagUri : TagFilteringWebProxyTest._proxy.getInterceptedTags()) {
        	Tag tag = new AdobeAnalyticsTag();
        	tag.setFields(tagUri);
        	tags.add(tag);
        }
        page.set_tags(tags);
        Assert.assertNotNull(page);
        Assert.assertEquals(page.get_pageTitle(), "Google");
        Assert.assertTrue(page.get_tags().size() == 0);
    }
    
    @Test
    public void testSecureTaggedPage()
    {
    	_proxy.clearInterceptedTags();
        CrawledWebPage page = TagFilteringWebProxyTest._browser.getPage("https://securestore.hbo.com/");
        List<Tag> tags = new ArrayList<Tag>();
        for(String tagUri : TagFilteringWebProxyTest._proxy.getInterceptedTags()) {
        	Tag tag = new AdobeAnalyticsTag();
        	tag.setFields(tagUri);
        	tags.add(tag);
        }
        page.set_tags(tags);
        Assert.assertNotNull(page);
        Assert.assertTrue(page.get_tags().size() > 0);
    }
    
    @Test
    public void testInsecureTaggedPage()
    {
    	_proxy.clearInterceptedTags();
        CrawledWebPage page = TagFilteringWebProxyTest._browser.getPage("http://store.hbo.com/");
        List<Tag> tags = new ArrayList<Tag>();
        for(String tagUri : TagFilteringWebProxyTest._proxy.getInterceptedTags()) {
        	Tag tag = new AdobeAnalyticsTag();
        	tag.setFields(tagUri);
        	tags.add(tag);
        }
        page.set_tags(tags);
        Assert.assertNotNull(page);
        Assert.assertTrue(page.get_tags().size() > 0);
    }
}
