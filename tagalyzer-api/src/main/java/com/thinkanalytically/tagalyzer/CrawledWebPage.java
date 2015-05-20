package com.thinkanalytically.tagalyzer;

import java.util.ArrayList;
import java.util.List;

public class CrawledWebPage {
	
	private String _pageContent;
	private String _pageTitle;
	private String _pageURL;
	private List<String> _consoleMessages;
	private List<Tag> _tags;

	public CrawledWebPage() {
		_consoleMessages = new ArrayList<String>();
	}
	
	public String get_pageContent() {
		return _pageContent;
	}

	public void set_pageContent(String _pageContent) {
		this._pageContent = _pageContent;
	}

	public String get_pageTitle() {
		return _pageTitle;
	}

	public void set_pageTitle(String _pageTitle) {
		this._pageTitle = _pageTitle;
	}
	
	public String get_pageURL() {
		return _pageURL;
	}

	public void set_pageURL(String _pageURL) {
		this._pageURL = _pageURL;
	}

	public List<String> get_consoleMessages() {
		return _consoleMessages;
	}

	public void set_consoleMessages(List<String> _consoleMessages) {
		this._consoleMessages = _consoleMessages;
	}
	
	public List<Tag> get_tags() {
		return _tags;
	}

	public void set_tags(List<Tag> _tags) {
		this._tags = _tags;
	}

}
