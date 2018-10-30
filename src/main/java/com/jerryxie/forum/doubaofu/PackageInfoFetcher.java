package com.jerryxie.forum.doubaofu;

import java.io.IOException;

import org.apache.log4j.Logger;
import org.jsoup.nodes.Document;
import org.springframework.stereotype.Service;

import com.jerryxie.forum.ForumCommonService;

@Service
public class PackageInfoFetcher {
    private final String baseUrl = "https://www.1point3acres.com/bbs/forum.php?mod=viewthread&tid=%d";
    private final String secretAmountQueryUrl = "https://www.1point3acres.com/bbs/forum.php"
            + "?mod=misc&action=protectsort&tid=%d&optionid=%d";
    private Logger logger = Logger.getLogger(PackageInfoFetcher.class);

    public PackageInfoFetcher() {

    }

    public Document getPackagePage(int tid) {
        String url = String.format(baseUrl, tid);
        try {
            return ForumCommonService.getConnection(url).get();
        } catch (IOException e) {
            logger.error(e.toString());
        }
        return null;
    }

    public String getBase(int tid) {
        String url = String.format(secretAmountQueryUrl, tid, DoubaofuConstants.BASE_ID);
        try {
            return ForumCommonService.getConnection(url).get().wholeText();
        } catch (IOException e) {
            logger.error(e.toString());
        }
        return null;
    }
}
