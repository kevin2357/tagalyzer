package com.thinkanalytically.tagalyzer.test;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

import edu.uci.ics.crawler4j.crawler.CrawlConfig;
import edu.uci.ics.crawler4j.crawler.CrawlController;
import edu.uci.ics.crawler4j.crawler.WebCrawler;
import edu.uci.ics.crawler4j.fetcher.PageFetcher;
import edu.uci.ics.crawler4j.robotstxt.RobotstxtConfig;
import edu.uci.ics.crawler4j.robotstxt.RobotstxtServer;

import org.junit.AfterClass;
import org.junit.After;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.thinkanalytically.tagalyzer.CrawledWebPage;
import com.thinkanalytically.tagalyzer.TagalyzerCrawler;

/**
 * Unit test for embeddable web spider
 */
public class CrawlerTest 
{
	private static WebCrawler _crawler;
	private static final Logger logger = LoggerFactory.getLogger(CrawlerTest.class);
	private static int _numberOfCrawlers;
	private static CrawlController _controller;
    
    @BeforeClass
    public static void globalSetup() throws Exception
    {
    	String crawlStorageFolder = "crawler_temp"; //args[0];
    	_numberOfCrawlers = 1; //Integer.parseInt(args[1]);
    	CrawlConfig config = new CrawlConfig();
    	config.setCrawlStorageFolder(crawlStorageFolder);
    	config.setPolitenessDelay(150);
    	config.setMaxDepthOfCrawling(-1);
    	config.setMaxPagesToFetch(5);
    	config.setIncludeBinaryContentInCrawling(false);
    	config.setResumableCrawling(false);
    	PageFetcher pageFetcher = new PageFetcher(config);
        RobotstxtConfig robotstxtConfig = new RobotstxtConfig();
        RobotstxtServer robotstxtServer = new RobotstxtServer(robotstxtConfig, pageFetcher);
        _controller = new CrawlController(config, pageFetcher, robotstxtServer);
        
    }
    
    @Before
    public void setUp() throws Exception
    {
    	;
    }
    
    @After
    public void tearDown() throws Exception {
    	;
    }
    
    @AfterClass
    public static void globalTearDown() {
    	TagalyzerCrawler.close();
    }

    @Test
    public void testHboCrawl()
    {
    	_controller.addSeed("http://store.hbo.com");
    	_controller.start(TagalyzerCrawler.class, _numberOfCrawlers);
//    	List<CrawledWebPage> crawledPages = TagalyzerCrawler.getCrawledPages();
//    	for(CrawledWebPage crawledPage : crawledPages) {
//    		Assert.assertTrue("Page should have tracking tag on it!", crawledPage.get_tags().size() > 0);
//    		logger.warn("Page " + crawledPage.get_pageURL() + " has " + crawledPage.get_tags().size() + " tracking tags on it");
//    	}
    }
        
}
