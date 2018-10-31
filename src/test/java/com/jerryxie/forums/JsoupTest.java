package com.jerryxie.forums;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Logger;

import javax.net.ssl.SSLSocketFactory;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.junit4.SpringRunner;

import com.jerryxie.forum.CustomizedSocketFactoryConfig;
import com.jerryxie.forum.ForumCommonService;
import com.jerryxie.forum.doubaofu.PackageInfoFetcher;

@RunWith(SpringRunner.class)
public class JsoupTest {

    @TestConfiguration
    static class CustomizedSocketFactoryConfigTest extends CustomizedSocketFactoryConfig {
        @Bean
        public ForumCommonService getForumCommonService() {
            return new ForumCommonService();
        }
    }

    @Autowired
    SSLSocketFactory sslFactory;

    PackageInfoFetcher fetcher = new PackageInfoFetcher();
    Logger logger = Logger.getLogger(JsoupTest.class.getName());
    Map<String, String> cookies = new HashMap<>();

    public JsoupTest() {
        cookies.put("4Oaf_61d6_auth",
                "9a597bPpQ%2BWfCOWpN6kYdzwl%2FKiL5PyqKb40256n0Nwi6hMzqj8P1nnYQBIk%2Fqfi8dgthDLi8Yzm%2B%2FV337bkvDXeFqY");
        cookies.put("4Oaf_61d6_saltkey", "V0h0TUbW");
    }

    @Test
    public void testWebLogin() {
        String url = "http://www.1point3acres.com/bbs/forum.php";
        Document doc;
        try {

            doc = Jsoup.connect(url).cookies(cookies).get();
            System.out.println(doc.wholeText());
        } catch (IOException e) {
            logger.warning(e.toString());
        }

    }

    private Document getDoc(String url) {
        Document doc;
        try {
            doc = Jsoup.connect(url).sslSocketFactory(sslFactory).cookies(cookies).get();
            return doc;
        } catch (IOException e) {
            logger.warning(e.toString());
        }
        return null;
    }

    @Test
    public void testDoubaofu() {
        String url = "https://www.1point3acres.com/bbs/forum.php?mod=misc&action=protectsort&tid=453437&optionid=3121&inajax=1&ajaxtarget=sortmessage_package_base";
        Document doc = getDoc(url);
        System.out.println(doc.text());
    }

    @Test
    public void testDoubaofuClass() {
        String url = "https://www.1point3acres.com/bbs/forum.php?mod=viewthread&tid=441132";
        Document doc = getDoc(url);
        System.out.println(doc.select("div.typeoption").html());
        System.out.println(fetcher.generatePackageData(doc, 441132));
    }

}
