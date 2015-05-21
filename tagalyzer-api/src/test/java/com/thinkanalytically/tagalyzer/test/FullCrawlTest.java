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
public class FullCrawlTest 
{
	private static WebCrawler _crawler;
	private static final Logger logger = LoggerFactory.getLogger(FullCrawlTest.class);
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
    	config.setMaxPagesToFetch(32000);
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

    public void addCrawlSeed(String siteToCrawl)
    {
    	String protocolPrefix = siteToCrawl.contains("secure") ? "https://" : "http://";
    	String fullSiteToCrawl = protocolPrefix + siteToCrawl;
    	_controller.addSeed(fullSiteToCrawl);
    }
    
    @Test
    public void testCrawlAllSites() {
        addCrawlSeed("cbsstore.com");
        addCrawlSeed("secure.cbsstore.com");
        addCrawlSeed("cbs.resultspage.com");
        addCrawlSeed("shop.fxnetworks.com");
        addCrawlSeed("secureshop.fxnetworks.com");
        addCrawlSeed("tv.fxnetworks.com");
        addCrawlSeed("nbcuniversalstore.com");
        addCrawlSeed("secure.nbcuniversalstore.com");
        addCrawlSeed("nbcshop.nbcuniversalstore.com");
        addCrawlSeed("store.sho.com");
        addCrawlSeed("securestore.sho.com");
        addCrawlSeed("showtime.resultspage.com");
        addCrawlSeed("shop.tcm.com");
        addCrawlSeed("classic-movies.tcm.com");
        addCrawlSeed("secureshop.tcm.com");
        addCrawlSeed("store.discovery.com");
        addCrawlSeed("securestore.discovery.com");
        addCrawlSeed("discovery.resultspage.com");
        addCrawlSeed("shop.foodnetworkstore.com");
        addCrawlSeed("foodnetworkstore.com");
        addCrawlSeed("secure.foodnetworkstore.com");
        addCrawlSeed("shop.fox.com");
        addCrawlSeed("secureshop.fox.com");
        addCrawlSeed("translate.googleusercontent.com");
        addCrawlSeed("store.hbo.com");
        addCrawlSeed("securestore.hbo.com");
        addCrawlSeed("tvshop.hbo.com");
        addCrawlSeed("shopbybravo.com");
        addCrawlSeed("secure.shopbybravo.com");
        addCrawlSeed("shopbybravo.resultspage.com");
        addCrawlSeed("store.comcast.com");
        addCrawlSeed("securestore.comcast.com");
        addCrawlSeed("franklinmint.com");
        addCrawlSeed("shop.franklinmint.com");
        addCrawlSeed("secure.franklinmint.com");
        addCrawlSeed("netsstore.com");
        addCrawlSeed("secure.netsstore.com");
        addCrawlSeed("brooklyn.netsstore.com");
        addCrawlSeed("oprahstore.ftr.seenon.com");
        addCrawlSeed("oprahstore.preview.seenon.com");
        addCrawlSeed("playboymagazinestore.com");
        addCrawlSeed("secure.playboymagazinestore.com");
        addCrawlSeed("bunny.playboymagazinestore.com");
        addCrawlSeed("rachaelraystore.com");
        addCrawlSeed("secure.rachaelraystore.com");
        addCrawlSeed("stadiumcollection.com");
        addCrawlSeed("shop.startrek.com");
        addCrawlSeed("secureshop.startrek.com");
        addCrawlSeed("trekkie.startrek.com");
        addCrawlSeed("shop.tapout.com");
        addCrawlSeed("secure.tapout.com");
        addCrawlSeed("shop.tbs.com");
        addCrawlSeed("ufcstore.eu");
        addCrawlSeed("secure.ufcstore.eu");
        addCrawlSeed("ufcukstore.resultspage.com");
        addCrawlSeed("celticsstore.com");
        addCrawlSeed("secure.celticsstore.com");
        addCrawlSeed("celticsstore.resultspage.com");
        addCrawlSeed("boston.celticsstore.com");
        addCrawlSeed("kingsteamstore.com");
        addCrawlSeed("secure.kingsteamstore.com");
        addCrawlSeed("shop.kingsteamstore.com");
        addCrawlSeed("shop.tntdrama.com");
        addCrawlSeed("secureshop.tntdrama.com");
        addCrawlSeed("ustashop.com");
        addCrawlSeed("secure.ustashop.com");
        addCrawlSeed("tennis.ustashop.com");
        
        _controller.start(TagalyzerCrawler.class, _numberOfCrawlers);
    }
        
}
