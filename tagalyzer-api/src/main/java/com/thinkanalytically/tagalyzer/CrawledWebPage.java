package com.thinkanalytically.tagalyzer;

import java.util.ArrayList;
import java.util.List;

public class CrawledWebPage {
	
	private String _pageContent;
	private String _pageTitle;
	private String _pageURL;
	private List<String> _consoleMessages;
	private List<Tag> _tags;
	
	private boolean _hasBodyTag, _hasSmartyCompileText, _hasDigitalDataJson, _hasCorrectSatelliteLib, _hasLegacyTranslation, _hasSatellitPageBottom;
	private int _satelliteLibCount, _satellitePageBottomCount, _digitalDataJsonCount;

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

	public boolean is_hasBodyTag() {
		return _hasBodyTag;
	}

	public void set_hasBodyTag(boolean _hasBodyTag) {
		this._hasBodyTag = _hasBodyTag;
	}

	public boolean is_hasSmartyCompileText() {
		return _hasSmartyCompileText;
	}

	public void set_hasSmartyCompileText(boolean _hasSmartyCompileText) {
		this._hasSmartyCompileText = _hasSmartyCompileText;
	}

	public boolean is_hasDigitalDataJson() {
		return _hasDigitalDataJson;
	}

	public void set_hasDigitalDataJson(boolean _hasDigitalDataJson) {
		this._hasDigitalDataJson = _hasDigitalDataJson;
	}

	public boolean is_hasCorrectSatelliteLib() {
		return _hasCorrectSatelliteLib;
	}

	public void set_hasCorrectSatelliteLib(boolean _hasCorrectSatelliteLib) {
		this._hasCorrectSatelliteLib = _hasCorrectSatelliteLib;
	}

	public boolean is_hasLegacyTranslation() {
		return _hasLegacyTranslation;
	}

	public void set_hasLegacyTranslation(boolean _hasLegacyTranslation) {
		this._hasLegacyTranslation = _hasLegacyTranslation;
	}

	public int get_satelliteLibCount() {
		return _satelliteLibCount;
	}

	public void set_satelliteLibCount(int _satelliteLibCount) {
		this._satelliteLibCount = _satelliteLibCount;
	}

	public int get_satellitePageBottomCount() {
		return _satellitePageBottomCount;
	}

	public void set_satellitePageBottomCount(int _satellitePageBottomCount) {
		this._satellitePageBottomCount = _satellitePageBottomCount;
	}

	public boolean is_hasSatellitPageBottom() {
		return _hasSatellitPageBottom;
	}

	public void set_hasSatellitPageBottom(boolean _hasSatellitPageBottom) {
		this._hasSatellitPageBottom = _hasSatellitPageBottom;
	}

	public int get_digitalDataJsonCount() {
		return _digitalDataJsonCount;
	}

	public void set_digitalDataJsonCount(int _digitalDataJsonCount) {
		this._digitalDataJsonCount = _digitalDataJsonCount;
	}

}
