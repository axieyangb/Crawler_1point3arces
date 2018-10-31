package com.jerryxie.forum.comment;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Optional;

import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.springframework.stereotype.Service;

import com.jerryxie.forum.comment.models.CommentDetail;

@Service
public class CommentService {
    public CommentService() {

    }

    public List<CommentDetail> getAllComments(Document doc) {
        List<CommentDetail> comments = new ArrayList<>();
        doc.select("table[summary^='pid']").stream().forEach(section -> {
            CommentDetail comment = generateOneComment(section);
            comments.add(comment);
        });
        return comments;
    }

    private CommentDetail generateOneComment(Element section) {
        CommentDetail comment = new CommentDetail();
        Element metaInfo = section.select("div.authi").stream().findFirst().get();
        Element userSection = metaInfo.selectFirst("a.xi2");
        Element quoteSection = section.selectFirst("div.quote");
        if (metaInfo.wholeText().contains("楼主")) {
            comment.setLeader(true);
        }
        comment.setUsername(userSection.wholeText());
        String homeSpaceUrl = userSection.attr("href");
        String[] tokens = homeSpaceUrl.split("=");
        comment.setUserId(tokens[tokens.length - 1]);
        Element timeSection = metaInfo.selectFirst("em");
        comment.setPublishTime(parseTimeFromMeta(timeSection));
        if (quoteSection != null) {
            setUpQuotePart(quoteSection, comment);
        }

        section.selectFirst("td.t_f").children().stream().forEach(elem -> {
            elem.remove();
        });
        comment.setReplyContent(section.selectFirst("td.t_f").wholeText().trim());
        return comment;
    }

    private void setUpQuotePart(Element quoteSection, CommentDetail comment) {
        String quoteTitle = quoteSection.selectFirst("font").wholeText();
        comment.setQuoteTitle(quoteTitle);
        String quoteWholeContent = quoteSection.wholeText();
        comment.setQuoteContent(quoteWholeContent.replace(quoteTitle, "").trim());
    }

    private long getTime(int year, int month, int date, int hour, int minute, int second) {
        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.YEAR, year);
        cal.set(Calendar.MONTH, month);
        cal.set(Calendar.DATE, date);
        cal.set(Calendar.HOUR, hour);
        cal.set(Calendar.MINUTE, minute);
        cal.set(Calendar.SECOND, second);
        cal.add(Calendar.DATE, -1);
        return cal.getTimeInMillis();
    }

    private long parseTimeFromMeta(Element timeSection) {
        String timeStr = timeSection.wholeText().replace("发表于", "").trim();
        String[] tokens = timeStr.split(" ");
        if (tokens.length < 2) {
            timeStr = timeSection.selectFirst("span").attr("title");
            tokens = timeStr.split(" ");
        }
        String datePart = tokens[0];
        String timePart = tokens[1];
        String[] dateTokens = datePart.split("-");
        String[] timeTokens = timePart.split(":");
        return getTime(Integer.parseInt(dateTokens[0]), Integer.parseInt(dateTokens[1]),
                Integer.parseInt(dateTokens[2]), Integer.parseInt(timeTokens[0]), Integer.parseInt(timeTokens[1]),
                Integer.parseInt(timeTokens[2]));
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
