package com.jerryxie.forum.service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ItemListPageService {
    private final String baseUrl = "https://www.1point3acres.com/bbs/forum.php?mod=forumdisplay&fid=%d&page=%d";
    private Logger logger = LogManager.getLogger(ItemListPageService.class);
    @Autowired
    CommonService commonService;

    public Document getItemListPage(int fid, int pageNum) {
        String url = String.format(baseUrl, fid, pageNum);
        try {
            return commonService.getConnection(url).get();
        } catch (IOException e) {
            logger.error(e.toString());
        }
        return null;
    }

    public List<String> getTidList(Document doc) {
        List<String> tids = new ArrayList<>();
        Optional<Element> tableOptional = doc.select("table#threadlisttableid").stream().findFirst();
        if (!tableOptional.isPresent()) {
            return tids;
        }
        tableOptional.get().select("tbody").stream().forEach(tbody -> {
            String id = tbody.attr("id");
            if (id.startsWith("normalthread")) {
                tids.add(id.split("_")[1]);
            }
        });
        return tids;
    }

    public int getTotalPageNum(Document doc) {
        Optional<Element> elementOptional = doc.select("input[name='custompage']").stream().findFirst();
        if (!elementOptional.isPresent()) {
            return 1;
        }
        String[] tokens = elementOptional.get().nextElementSibling().attr("title").split(" ");
        return Integer.parseInt(tokens[1].trim());
    }
}
