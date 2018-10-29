package com.jerryxie.forums;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Logger;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.junit.Test;

import com.jerryxie.forums.doubaofu.PackageInfoFetcher;

public class JsoupTest {
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
			
			doc = Jsoup.connect(url).cookies(cookies)
					.get();
			System.out.println(doc.wholeText());
		} catch (IOException e) {
			logger.warning(e.toString());
		}

	}
	@Test
	public void testDoubaofu() {
		String url="https://www.1point3acres.com/bbs/forum.php?mod=misc&action=protectsort&tid=453437&optionid=3121&inajax=1&ajaxtarget=sortmessage_package_base";
		Document doc;
		try {
			doc = Jsoup.connect(url).cookies(cookies)
					.get();
			System.out.println(doc.wholeText());
		} catch (IOException e) {
			logger.warning(e.toString());
		}
	}
	
	@Test
	public void testDoubaofuClass() {
		PackageInfoFetcher fetcher  = new PackageInfoFetcher();
		System.out.println(fetcher.getBase(451958));
	}

}
