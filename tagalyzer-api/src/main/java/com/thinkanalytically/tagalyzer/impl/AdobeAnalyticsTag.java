/**
 * 
 */
package com.thinkanalytically.tagalyzer.impl;

import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLDecoder;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Pattern;

import org.apache.log4j.Logger;

import com.thinkanalytically.tagalyzer.Tag;

/**
 * @author ktankersley
 *
 */
public class AdobeAnalyticsTag implements Tag {
	
	private String _pixelUri;
	private String _pixelPrefix;
	private Logger _logger;
	private Map<String,String> _pixelVariables;
	
	public AdobeAnalyticsTag() {
		_logger = Logger.getLogger(AdobeAnalyticsTag.class);
		_pixelVariables = new HashMap<String,String>();
	}
	
	public Pattern getUriFilterPattern() {
		// TODO Auto-generated method stub
		return null;
	}

	public void setFields(String uri) {
		this._pixelUri = uri;
		this._pixelPrefix = URLDecoder.decode(uri.substring(0, uri.indexOf('?', 0))).trim();
		String query = uri.substring(uri.indexOf('?', 0)+1, uri.length());
		String[] terms = query.split("&");
		for(String term : terms) {
			String[] keyval = term.split("=");
			String key = URLDecoder.decode(keyval[0]).trim();
			String val = URLDecoder.decode(keyval[1]).trim();
			this._pixelVariables.put(key,val);
		}
		int k = 0;
	}

	public String getPixelUri() {
		return this._pixelUri;
	}

	public String get_pixelPrefix() {
		return _pixelPrefix;
	}
	
	public String getPixelVar(String key) {
		if(_pixelVariables.containsKey(key)) return _pixelVariables.get(key);
		else return "";
	}

}
