package com.thinkanalytically.tagalyzer.impl;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.PreparedStatement;

import com.thinkanalytically.tagalyzer.CrawledWebPage;
import com.thinkanalytically.tagalyzer.Tag;

public class CrawlResultsDataUtil {
	
	private Connection _connection;
	private PreparedStatement _insertPageResults;
	private PreparedStatement _insertTagResults;
	
	private static final String[] PIXEL_FIELDS = {
		"bh","bw","v0","ch","c","ct","k","cc","v1","v2","v3","v4","v5","v6","v7","v8","v9","v10","v11","v12","v13","v14","v15","v16","v17","v18","v19","v20","v21","v22","v23","v24","v25","v26","v27","v28","v29","v30","v31","v32","v33","v34","v35","v36","v37","v38","v39","v40","v41","v42","v43","v44","v45","v46","v47","v48","v49","v50","v51","v52","v53","v54","v55","v56","v57","v58","v59","v60","v61","v62","v63","v64","v65","v66","v67","v68","v69","v70","v71","v72","v73","v74","v75","events","h1","h2","h3","h4","h5","hp","v","j","lang","pev2","pe","pev1","l1","l2","l3","pageName","pageType","g","p","products","c1","c2","c3","c4","c5","c6","c7","c8","c9","c10","c11","c12","c13","c14","c15","c16","c17","c18","c19","c20","c21","c22","c23","c24","c25","c26","c27","c28","c29","c30","c31","c32","c33","c34","c35","c36","c37","c38","c39","c40","c41","c42","c43","c44","c45","c46","c47","c48","c49","c50","c51","c52","c53","c54","c55","c56","c57","c58","c59","c60","c61","c62","c63","c64","c65","c66","c67","c68","c69","c70","c71","c72","c73","c74","c75","purchaseID","r","s","serer","state","ts","t","xact","userAgent","vid","mid","zip"
	};
	
	public CrawlResultsDataUtil() throws ClassNotFoundException, SQLException {
		Class.forName("org.sqlite.JDBC");
		_connection = DriverManager.getConnection("jdbc:sqlite:crawl_results.sqlite3");
		_insertPageResults = _connection.prepareStatement("INSERT INTO page_results(page_url,page_title,num_tags,has_body_tag,has_smarty_compile_text,has_digital_data_json,has_correct_satellite_lib,has_legacy_translation,has_satellite_pagebottom,satellite_lib_count,satellite_pagebottom_count,digital_data_json_count) values(?,?,?,?,?,?,?,?,?,?,?,?)");
		_insertTagResults = _connection.prepareStatement("INSERT INTO tag_results(page_url, pixel_prefix, browser_height, browser_width, campaign, channel, color_depth, connection_type, cookies_enabled, currency_code, evar1, evar2, evar3, evar4, evar5, evar6, evar7, evar8, evar9, evar10, evar11, evar12, evar13, evar14, evar15, evar16, evar17, evar18, evar19, evar20, evar21, evar22, evar23, evar24, evar25, evar26, evar27, evar28, evar29, evar30, evar31, evar32, evar33, evar34, evar35, evar36, evar37, evar38, evar39, evar40, evar41, evar42, evar43, evar44, evar45, evar46, evar47, evar48, evar49, evar50, evar51, evar52, evar53, evar54, evar55, evar56, evar57, evar58, evar59, evar60, evar61, evar62, evar63, evar64, evar65, evar66, evar67, evar68, evar69, evar70, evar71, evar72, evar73, evar74, evar75, events, hier1, hier2, hier3, hier4, hier5, home_page, java_enabled, javascript_version, language, link_name, link_type, link_url, list1, list2, list3, page_name, page_type, passed_page_url, plugins, products, sprop1, sprop2, sprop3, sprop4, sprop5, sprop6, sprop7, sprop8, sprop9, sprop10, sprop11, sprop12, sprop13, sprop14, sprop15, sprop16, sprop17, sprop18, sprop19, sprop20, sprop21, sprop22, sprop23, sprop24, sprop25, sprop26, sprop27, sprop28, sprop29, sprop30, sprop31, sprop32, sprop33, sprop34, sprop35, sprop36, sprop37, sprop38, sprop39, sprop40, sprop41, sprop42, sprop43, sprop44, sprop45, sprop46, sprop47, sprop48, sprop49, sprop50, sprop51, sprop52, sprop53, sprop54, sprop55, sprop56, sprop57, sprop58, sprop59, sprop60, sprop61, sprop62, sprop63, sprop64, sprop65, sprop66, sprop67, sprop68, sprop69, sprop70, sprop71, sprop72, sprop73, sprop74, sprop75, purchase_id, referrer, resolution, server, state, timestamp, formatted_time, transaction_id, user_agent, visitor_id, marketing_cloud_id, zip) VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)");
	}
	
	public void saveCrawlResults(CrawledWebPage page) throws SQLException {
		_insertPageResults.setString(1, page.get_pageURL());
		_insertPageResults.setString(2, page.get_pageTitle());
		_insertPageResults.setInt(3, page.get_tags().size());
		_insertPageResults.setString(4, page.is_hasBodyTag() ? "Y" : "N" );
		_insertPageResults.setString(5, page.is_hasSmartyCompileText() ? "Y" : "N"  );
		_insertPageResults.setString(6, page.is_hasDigitalDataJson() ? "Y" : "N"  );
		_insertPageResults.setString(7, page.is_hasCorrectSatelliteLib() ? "Y" : "N"  );
		_insertPageResults.setString(8, page.is_hasLegacyTranslation() ? "Y" : "N"  );
		_insertPageResults.setString(9, page.is_hasSatellitPageBottom() ? "Y" : "N"  );
		_insertPageResults.setInt(10, page.get_satelliteLibCount() );
		_insertPageResults.setInt(11, page.get_satellitePageBottomCount() );
		_insertPageResults.setInt(12, page.get_digitalDataJsonCount() );
		
		_insertPageResults.executeUpdate();
		
		for(Tag tag : page.get_tags()) {
			AdobeAnalyticsTag adobePixel = (AdobeAnalyticsTag) tag;
			_insertTagResults.setString(1, page.get_pageURL());
			_insertTagResults.setString(2, adobePixel.get_pixelPrefix());
			for(int i = 0; i < PIXEL_FIELDS.length; i++ ) {
				int paramPosition = i+3;
				String paramVal = adobePixel.getPixelVar(PIXEL_FIELDS[i]);
				_insertTagResults.setString(paramPosition, paramVal);
			}
			_insertTagResults.executeUpdate();
		}
	}

}
