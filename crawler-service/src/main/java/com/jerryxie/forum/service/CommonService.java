package com.jerryxie.forum.service;

import java.util.HashMap;
import java.util.Map;

import javax.net.ssl.SSLSocketFactory;

import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jerryxie.forum.ForumConstants;

@Service
public class CommonService {
    @Autowired
    SSLSocketFactory sslSocketFactory;

    public Connection getConnection(String url) {
        return getConnection(url, null);
    }

    public Connection getConnection(String url, Map<String, String> cookies) {
        if (cookies == null) {
            cookies = getDefaultCookie();
        }
        return Jsoup.connect(url).cookies(cookies).sslSocketFactory(sslSocketFactory);
    }

    private Map<String, String> getDefaultCookie() {
        Map<String, String> cookies = new HashMap<>();
        cookies.put(ForumConstants.OAUTH_NAME, ForumConstants.OAUTH_VALUE);
        cookies.put(ForumConstants.SALT_KEY, ForumConstants.SALT_VALUE);
        return cookies;
    }
}
