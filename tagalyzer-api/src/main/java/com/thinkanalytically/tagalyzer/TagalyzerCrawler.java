package com.thinkanalytically.tagalyzer;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

import org.apache.log4j.Logger;

import edu.uci.ics.crawler4j.crawler.Page;
import edu.uci.ics.crawler4j.crawler.WebCrawler;
import edu.uci.ics.crawler4j.parser.HtmlParseData;
import edu.uci.ics.crawler4j.url.WebURL;

import com.thinkanalytically.tagalyzer.CrawledWebPage;
import com.thinkanalytically.tagalyzer.ScriptableBrowser;
import com.thinkanalytically.tagalyzer.Tag;
import com.thinkanalytically.tagalyzer.TagFilteringWebProxy;
import com.thinkanalytically.tagalyzer.impl.AdobeAnalyticsTag;
import com.thinkanalytically.tagalyzer.impl.CrawlResultsDataUtil;
import com.thinkanalytically.tagalyzer.impl.LittleProxy;
import com.thinkanalytically.tagalyzer.impl.SeleniumChromeBrowser;

public class TagalyzerCrawler extends WebCrawler {

    private final static Pattern FILTERS = Pattern.compile("/|.*(\\.(css|js|gif|jpg|png|html|htm|php))$");

	private static TagFilteringWebProxy _proxy;
	private static ScriptableBrowser _chromeBrowser;
	//private static List<CrawledWebPage> _crawledPages;
	private static CrawlResultsDataUtil _dataUtil;
	private static Logger _logger;
	
	static {
		_logger = Logger.getLogger(TagalyzerCrawler.class);
    	_proxy = new LittleProxy();
    	_proxy.addTagFilter(Pattern.compile(".*metrics.seenon.com/b/ss.*"));
    	_proxy.addTagFilter(Pattern.compile(".*smetrics.seenon.com/b/ss.*"));
    	_proxy.addTagFilter(Pattern.compile(".*omtrdc.net/b/ss.*"));
    	_proxy.addTagFilter(Pattern.compile(".*2o7.net/b/ss.*"));
    	//_proxy.addTagFilter(Pattern.compile("demdex.net"));
    	_proxy.startup(8089);
    	_proxy.clearInterceptedTags();
    	
    	_chromeBrowser = new SeleniumChromeBrowser("localhost",8089);
    	_chromeBrowser.startup();
    	
    	//_crawledPages = new ArrayList<CrawledWebPage>();
    	
    	try {
    		_dataUtil = new CrawlResultsDataUtil();
    	}
    	catch (Exception e) {
    		_logger.error("Couldn't open data access utility", e);
    	}
	}
	
	public TagalyzerCrawler() {
		;
	}
	
	public static void close() {
		_proxy.shutdown();
		_chromeBrowser.quit();
	}
    
    /**
     * This method receives two parameters. The first parameter is the page
     * in which we have discovered this new url and the second parameter is
     * the new url. You should implement this function to specify whether
     * the given url should be crawled or not (based on your crawling logic).
     * In this example, we are instructing the crawler to ignore urls that
     * have css, js, git, ... extensions and to only accept urls that start
     * with "http://www.ics.uci.edu/". In this case, we didn't need the
     * referringPage parameter to make the decision.
     */
     @Override
     public boolean shouldVisit(Page referringPage, WebURL url) {
         String href = url.getURL().toLowerCase();
         return !FILTERS.matcher(href).matches()
                && 
                (
                	href.toLowerCase().contains("store.hbo.com")
                	|| href.toLowerCase().contains("cbsstore.com") || href.toLowerCase().contains("secure.cbsstore.com") || href.toLowerCase().contains("cbs.resultspage.com") || href.toLowerCase().contains("shop.fxnetworks.com") || href.toLowerCase().contains("secureshop.fxnetworks.com") || href.toLowerCase().contains("tv.fxnetworks.com") || href.toLowerCase().contains("nbcuniversalstore.com") || href.toLowerCase().contains("secure.nbcuniversalstore.com") || href.toLowerCase().contains("nbcshop.nbcuniversalstore.com") || href.toLowerCase().contains("store.sho.com") || href.toLowerCase().contains("securestore.sho.com") || href.toLowerCase().contains("showtime.resultspage.com") || href.toLowerCase().contains("shop.tcm.com") || href.toLowerCase().contains("classic-movies.tcm.com") || href.toLowerCase().contains("secureshop.tcm.com") || href.toLowerCase().contains("store.discovery.com") || href.toLowerCase().contains("securestore.discovery.com") || href.toLowerCase().contains("discovery.resultspage.com") || href.toLowerCase().contains("shop.foodnetworkstore.com") || href.toLowerCase().contains("foodnetworkstore.com") || href.toLowerCase().contains("secure.foodnetworkstore.com") || href.toLowerCase().contains("shop.fox.com") || href.toLowerCase().contains("secureshop.fox.com") || href.toLowerCase().contains("store.hbo.com") || href.toLowerCase().contains("securestore.hbo.com") || href.toLowerCase().contains("tvshop.hbo.com") || href.toLowerCase().contains("shopbybravo.com") || href.toLowerCase().contains("secure.shopbybravo.com") || href.toLowerCase().contains("shopbybravo.resultspage.com") || href.toLowerCase().contains("store.comcast.com") || href.toLowerCase().contains("securestore.comcast.com") || href.toLowerCase().contains("franklinmint.com") || href.toLowerCase().contains("shop.franklinmint.com") || href.toLowerCase().contains("secure.franklinmint.com") || href.toLowerCase().contains("netsstore.com") || href.toLowerCase().contains("secure.netsstore.com") || href.toLowerCase().contains("brooklyn.netsstore.com") || href.toLowerCase().contains("oprahstore.ftr.seenon.com") || href.toLowerCase().contains("oprahstore.preview.seenon.com") || href.toLowerCase().contains("playboymagazinestore.com") || href.toLowerCase().contains("secure.playboymagazinestore.com") || href.toLowerCase().contains("bunny.playboymagazinestore.com") || href.toLowerCase().contains("rachaelraystore.com") || href.toLowerCase().contains("secure.rachaelraystore.com") || href.toLowerCase().contains("stadiumcollection.com") || href.toLowerCase().contains("shop.startrek.com") || href.toLowerCase().contains("secureshop.startrek.com") || href.toLowerCase().contains("trekkie.startrek.com") || href.toLowerCase().contains("shop.tapout.com") || href.toLowerCase().contains("secure.tapout.com") || href.toLowerCase().contains("shop.tbs.com") || href.toLowerCase().contains("ufcstore.eu") || href.toLowerCase().contains("secure.ufcstore.eu") || href.toLowerCase().contains("ufcukstore.resultspage.com") || href.toLowerCase().contains("celticsstore.com") || href.toLowerCase().contains("secure.celticsstore.com") || href.toLowerCase().contains("celticsstore.resultspage.com") || href.toLowerCase().contains("boston.celticsstore.com") || href.toLowerCase().contains("kingsteamstore.com") || href.toLowerCase().contains("secure.kingsteamstore.com") || href.toLowerCase().contains("shop.kingsteamstore.com") || href.toLowerCase().contains("shop.tntdrama.com") || href.toLowerCase().contains("secureshop.tntdrama.com") || href.toLowerCase().contains("ustashop.com") || href.toLowerCase().contains("secure.ustashop.com") || href.toLowerCase().contains("tennis.ustashop.com")
                );
     }

     /**
      * This function is called when a page is fetched and ready
      * to be processed by your program.
      */
     @Override
     public void visit(Page page) {
    	 _proxy.clearInterceptedTags();
         String url = page.getWebURL().getURL();
         CrawledWebPage crawledPage = _chromeBrowser.getPage(url);
         //TagalyzerCrawler._crawledPages.add(crawledPage);
         crawledPage.set_pageURL(url);
         List<Tag> tags = new ArrayList<Tag>();
         for(String tagUri : _proxy.getInterceptedTags()) {
        	 Tag nextTag = new AdobeAnalyticsTag();
        	 nextTag.setFields(tagUri);
        	 tags.add(nextTag);
         }
         crawledPage.set_tags(tags);
         try {
        	 _dataUtil.saveCrawlResults(crawledPage);
         }
         catch(Exception e) {
        	 _logger.error("Couldn't save crawl results to DB!", e);
         }
    }
     
//     public static List<CrawledWebPage> getCrawledPages() {
//    	 return _crawledPages;
//     }
}