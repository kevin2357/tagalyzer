package com.thinkanalytically.tagalyzer;

public interface ScriptableBrowser {
	public CrawledWebPage getPage(String uri);
	public void quit();
	public void startup();
}
