package com.jerryxie.forums;

import java.util.HashMap;
import java.util.Map;

import org.jsoup.Connection;
import org.jsoup.Jsoup;

public final class ForumUtils {

	public static Connection getConnection(String url) {
		return getConnection(url, null);
	}

	public static Connection getConnection(String url, Map<String, String> cookies) {
		if (cookies == null) {
			cookies = getDefaultCookie();
		}
		return Jsoup.connect(url).cookies(cookies);
	}

	private static Map<String, String> getDefaultCookie() {
		Map<String, String> cookies = new HashMap<>();
		cookies.put(ForumConstants.OAUTH_NAME, ForumConstants.OAUTH_VALUE);
		cookies.put(ForumConstants.SALT_KEY, ForumConstants.SALT_VALUE);
		return cookies;
	}
}
