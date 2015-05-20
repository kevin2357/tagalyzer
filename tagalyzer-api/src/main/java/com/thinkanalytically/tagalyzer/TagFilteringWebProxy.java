/**
 * 
 */
package com.thinkanalytically.tagalyzer;

import java.util.List;
import java.util.regex.Pattern;

/**
 * @author ktankersley
 *
 */
public interface TagFilteringWebProxy {
	
	public void startup(int port);
	public void shutdown();
	
	/**
	 * @author ktankersley
	 * Adds a regex pattern to the list of tag filter patterns. Any request received
	 * by the proxy which matches one or more tag filter patterns will be blocked by
	 * the proxy and the URI requested will be saved to the list of intercepted tags.
	 * @param <T>
	 * @param uriPattern
	 */
	public void addTagFilter(Pattern uriPattern);
	
	public List<String> getInterceptedTags();
	public void clearInterceptedTags();
}
