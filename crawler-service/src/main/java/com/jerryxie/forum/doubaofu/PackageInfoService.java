package com.jerryxie.forum.doubaofu;

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

import com.jerryxie.forum.ForumCommonService;
import com.jerryxie.forum.comment.CommentService;
import com.jerryxie.forum.comment.models.CommentDetail;
import com.jerryxie.forum.doubaofu.models.SalaryPackage;
import com.jerryxie.forum.doubaofu.models.SalaryPackage.Decision;
import com.jerryxie.forum.doubaofu.models.SalaryPackage.JobType;
import com.jerryxie.forum.doubaofu.models.SalaryPackage.MaxDegree;
import com.jerryxie.forum.doubaofu.models.SalaryPackage.Status;

@Service
public class PackageInfoService {
    private final String baseUrl = "https://www.1point3acres.com/bbs/forum.php?mod=viewthread&tid=%d&page=%d";
    private final String secretAmountQueryUrl = "https://www.1point3acres.com/bbs/forum.php"
            + "?mod=misc&action=protectsort&tid=%d&optionid=%d";
    private final int oneThousand = 1000;
    private final int oneMillion = 1000000;
    private Logger logger = LogManager.getLogger(PackageInfoService.class);

    @Autowired
    ForumCommonService commonService;

    @Autowired
    CommentService commentService;

    public PackageInfoService() {

    }

    public Document getPackagePage(int tid) {
        return getPackagePage(tid, 1);
    }

    public Document getPackagePage(int tid, int pageNum) {
        String url = String.format(baseUrl, tid, pageNum);
        try {
            return commonService.getConnection(url).get();
        } catch (IOException e) {
            logger.error(e.toString());
        }
        return null;
    }

    public int getBase(int tid) {
        return getSecretNum(tid, DoubaofuConstants.BASE_ID);
    }

    public int getSignOn(int tid) {
        return getSecretNum(tid, DoubaofuConstants.SIGN_ON_ID);
    }

    public int getRSU(int tid) {
        return getSecretNum(tid, DoubaofuConstants.RSU_ID);
    }

    public String getBonus(int tid) {
        String url = String.format(secretAmountQueryUrl, tid, DoubaofuConstants.BONUS_ID);
        try {
            return commonService.getConnection(url).get().wholeText();
        } catch (IOException e) {
            logger.error(e.toString());
        }
        return "N/A";
    }

    public List<CommentDetail> getComments(Document doc, int tid) {
        List<CommentDetail> commentList = new ArrayList<>();
        int totalPageNum = commentService.getTotalPageNum(doc);
        commentList.addAll(commentService.getAllComments(doc));
        for (int i = 2; i <= totalPageNum; i++) {
            Document pageOfComment = this.getPackagePage(tid, i);
            commentList.addAll(commentService.getAllComments(pageOfComment));
        }
        return commentList;
    }

    private int getSecretNum(int tid, int optionId) {
        String url = String.format(secretAmountQueryUrl, tid, optionId);
        int times = 1;
        boolean isShareNum = false;
        try {
            String str = commonService.getConnection(url).get().wholeText().toLowerCase().trim();
            if (str.endsWith("k")) {
                str = str.substring(0, str.length() - 1);
                times = this.oneThousand;
            } else if (str.endsWith("m")) {
                str = str.substring(0, str.length() - 1);
                times = this.oneMillion;
            } else if (str.endsWith("share")) {
                str = str.split(" ")[0];
                isShareNum = true;
            }
            int amount = getNumByName(str);
            if (times == 1 && amount > 0 && amount < this.oneThousand && !isShareNum) {
                times = this.oneThousand;
            }

            return amount * times;

        } catch (IOException e) {
            logger.error(e.toString());
        }
        return -1;
    }

    public SalaryPackage generatePackageData(Document doc, int tid) {
        SalaryPackage pack = new SalaryPackage();
        pack.setTid(tid);
        Optional<Element> tableOptional = doc.select("div.typeoption").stream().findFirst();
        Optional<Element> commentOptional = doc.select("div.t_fsz").stream().findFirst();
        Optional<Element> titleOptional = doc.select("meta[name='description']").stream().findFirst();
        if (!tableOptional.isPresent()) {
            return pack;
        }
        Element table = tableOptional.get();

        table.select("tr").stream().forEach((element) -> {
            switch (element.child(0).text()) {
            case "找工年度:":
                pack.setJobYear(Integer.parseInt(element.child(1).text()));
                break;
            case "找工季节:":
                String monthRange = element.child(1).text().replace("月", "");
                String[] tokens = monthRange.split("-");
                pack.setJobMonthStart(Integer.parseInt(tokens[0]));
                pack.setJobMonthEnd(Integer.parseInt(tokens[1]));
                break;
            case "工作来源:":
                pack.setJobSource(element.child(1).text());
                break;
            case "工作职位类别:":
                pack.setPositionCategory(element.child(1).text());
                break;
            case "工作类别:":
                pack.setJobType(getJobTypeByName(element.child(1).text()));
                break;
            case "目前最高学历:":
                pack.setDegree(getMaxDegreeByName(element.child(1).text()));
                break;
            case "相关工作经验:":
                pack.setWorkingExperience(element.child(1).text());
                break;
            case "应届or在职跳槽:":
                pack.setStatus(getStatusByName(element.child(1).text()));
                break;
            case "具体工作，组，tech stack等:":
                pack.setCurrentWork(element.child(1).text());
                break;
            case "公司名称:":
                pack.setCompanyName(element.child(1).text());
                break;
            case "地区:":
                pack.setArea(element.child(1).text());
                break;
            case "Equity Vesting schedule:":
                pack.setVestSchedule(element.child(1).text());
                break;
            case "是否满意，打算去嘛:":
                pack.setAccept(getDecisionByName(element.child(1).text()));
                break;
            case "谈判，其他手头offer，目前工资对比:":
                pack.setCompeteOffer(element.child(1).text());
                break;
            case "annual refresh:":
                pack.setAnnualRefresh(getNumByName(element.child(1).text()));
                break;
            case "搬家费 relocation:":
                pack.setRelocation(getNumByName(element.child(1).text()));
                break;
            default:
            }
        });
        pack.setBase(getBase(tid));
        pack.setRsu(getRSU(tid));
        pack.setSignOn(getSignOn(tid));
        pack.setBonus(getBonus(tid));
        pack.setComment(commentOptional.isPresent() ? commentOptional.get().wholeText() : "");
        pack.setTitle(titleOptional.isPresent() ? titleOptional.get().attr("content") : "Unknown");
        pack.setComments(getComments(doc, tid));
        return pack;

    }

    private MaxDegree getMaxDegreeByName(String name) {
        switch (name) {
        case "本科":
            return MaxDegree.BACHELOR;
        case "硕士":
            return MaxDegree.MASTER;
        default:
            return MaxDegree.DOCTOR;
        }
    }

    private Status getStatusByName(String name) {
        switch (name) {
        case "fresh grad应届毕业生":
            return Status.FRESH_OUT_NO_EXPERIENCE;
        case "在职跳槽":
            return Status.JOB_CHANGE;
        case "其他":
            return Status.OTHER;
        default:
            return Status.UNKNOWN;
        }
    }

    private int getNumByName(String name) {
        try {
            return (int) Double.parseDouble(name.trim());
        } catch (NumberFormatException ex) {
            return -1;
        }
    }

    private Decision getDecisionByName(String name) {
        switch (name) {
        case "去":
            return Decision.ACCEPT;
        case "不去":
            return Decision.NOT_ACCEPT;
        default:
            return Decision.UNKNOWN;
        }
    }

    private JobType getJobTypeByName(String name) {
        switch (name) {
        case "全职":
            return JobType.FULL_TIME;
        case "合同工":
            return JobType.CONTRACTOR;
        case "实习":
            return JobType.INTERN;
        default:
            return JobType.OTHER;
        }
    }

}
