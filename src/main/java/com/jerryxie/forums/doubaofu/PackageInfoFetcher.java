package com.jerryxie.forums.doubaofu;

import java.io.IOException;
import java.util.logging.Logger;

import org.jsoup.nodes.Document;
import org.springframework.stereotype.Component;

import com.jerryxie.forums.ForumUtils;

@Component
public class PackageInfoFetcher {
	private final String baseUrl = "https://www.1point3acres.com/bbs/forum.php?mod=viewthread&tid=%d";
	private final String secretAmountQueryUrl = "https://www.1point3acres.com/bbs/forum.php?mod=misc&action=protectsort&tid=%d&optionid=%d";
	private Logger logger = Logger.getLogger(PackageInfoFetcher.class.getName());

	public PackageInfoFetcher() {

	}

	public Document getPackagePage(int tid) {
		String url = String.format(baseUrl, tid);
		try {
			return ForumUtils.getConnection(url).get();
		} catch (IOException e) {
			logger.warning(e.toString());
		}
		return null;
	}
	
	public String getBase(int tid) {
		String url = String.format(secretAmountQueryUrl, tid, DoubaofuConstants.BASE_ID);
		try {
			return ForumUtils.getConnection(url).get().wholeText();
		} catch (IOException e) {
			logger.warning(e.toString());
		}
		return null;
	}
}
