package com.thinkanalytically.tagalyzer.impl;

import com.thinkanalytically.tagalyzer.TagFilteringWebProxy;

import org.apache.log4j.Logger;
import org.littleshoot.proxy.HttpFilters;
import org.littleshoot.proxy.HttpFiltersAdapter;
import org.littleshoot.proxy.HttpFiltersSourceAdapter;
import org.littleshoot.proxy.HttpProxyServer;
import org.littleshoot.proxy.extras.SelfSignedMitmManager;
import org.littleshoot.proxy.impl.DefaultHttpProxyServer;

import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.http.DefaultFullHttpResponse;
import io.netty.handler.codec.http.HttpObject;
import io.netty.handler.codec.http.HttpRequest;
import io.netty.handler.codec.http.HttpResponse;
import io.netty.handler.codec.http.HttpResponseStatus;
import io.netty.handler.codec.http.HttpVersion;


import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

public class LittleProxy implements TagFilteringWebProxy {

	final static Logger logger = Logger.getLogger(LittleProxy.class);

	private List<Pattern> _tagFilters;
	private List<String> _interceptedTags;
	private HttpProxyServer _server;
	private int _serverPort;

	public LittleProxy() {
		_tagFilters = new ArrayList<Pattern>();
		_interceptedTags = new ArrayList<String>();
	}

	public void startup(int port) {
		_serverPort = port;

		_server = 
				DefaultHttpProxyServer
				.bootstrap()
				.withManInTheMiddle(new SelfSignedMitmManager())
				.withPort(port)
				.withFiltersSource(new HttpFiltersSourceAdapter() {
					@Override
					public HttpFilters filterRequest(HttpRequest originalRequest, ChannelHandlerContext ctx) {
						return new HttpFiltersAdapter(originalRequest) {
							@Override
							public HttpResponse requestPre(HttpObject httpObject) {
								if(httpObject instanceof HttpRequest) {
									HttpRequest req = (HttpRequest) httpObject;
									String requestHost = req.headers().get("Host");
									String fullUri = req.getUri().startsWith("http") ? req.getUri() : requestHost + req.getUri();
									//logger.error("Full URI requested is: " + fullUri);
									for(Pattern pattern : _tagFilters) {
										if(pattern.matcher(fullUri).matches() ) {
											logger.warn("Matched uri " + fullUri + " to pattern " + pattern.toString());
											_interceptedTags.add(fullUri);
											return new DefaultFullHttpResponse(
													HttpVersion.HTTP_1_1,
													HttpResponseStatus.OK);
										}
									}
								}
								return null;
							}
						};
					}
				})
				.start();
	}

	public void shutdown() {
		_server.stop();
	}

	public void addTagFilter(Pattern uriPattern)  {
		_tagFilters.add(uriPattern);
	}

	public List<String> getInterceptedTags() {
		return _interceptedTags;
	}

	public void clearInterceptedTags() {
		_interceptedTags.clear();
	}

}
