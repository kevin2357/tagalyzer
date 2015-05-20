/**
 * 
 */
package com.thinkanalytically.tagalyzer.impl;

import java.util.regex.Pattern;

import com.thinkanalytically.tagalyzer.Tag;

/**
 * @author ktankersley
 *
 */
public class AdobeAnalyticsTag implements Tag {
	
	private String _pixelUri;
	
	public Pattern getUriFilterPattern() {
		// TODO Auto-generated method stub
		return null;
	}

	public void setFields(String Uri) {
		this._pixelUri = Uri;
	}

	public String getPixelUri() {
		return this._pixelUri;
	}



}
