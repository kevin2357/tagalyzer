/**
 * 
 */
package com.thinkanalytically.tagalyzer;

import java.util.regex.Pattern;

/**
 * @author ktankersley
 *
 */
public interface Tag {
	public Pattern getUriFilterPattern();
	public void setFields(String Uri);
	public String getPixelUri();
}
