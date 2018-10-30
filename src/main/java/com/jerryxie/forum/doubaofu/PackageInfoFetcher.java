package com.jerryxie.forum.doubaofu;

import java.io.IOException;
import java.util.Optional;

import org.apache.log4j.Logger;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jerryxie.forum.ForumCommonService;
import com.jerryxie.forum.doubaofu.models.SalaryPackage;
import com.jerryxie.forum.doubaofu.models.SalaryPackage.JobType;
import com.jerryxie.forum.doubaofu.models.SalaryPackage.MaxDegree;
import com.jerryxie.forum.doubaofu.models.SalaryPackage.Status;

@Service
public class PackageInfoFetcher {
    private final String baseUrl = "https://www.1point3acres.com/bbs/forum.php?mod=viewthread&tid=%d";
    private final String secretAmountQueryUrl = "https://www.1point3acres.com/bbs/forum.php"
            + "?mod=misc&action=protectsort&tid=%d&optionid=%d";
    private Logger logger = Logger.getLogger(PackageInfoFetcher.class);

    @Autowired
    ForumCommonService commonService;

    public PackageInfoFetcher() {

    }

    public Document getPackagePage(int tid) {
        String url = String.format(baseUrl, tid);
        try {
            return commonService.getConnection(url).get();
        } catch (IOException e) {
            logger.error(e.toString());
        }
        return null;
    }

    public String getBase(int tid) {
        String url = String.format(secretAmountQueryUrl, tid, DoubaofuConstants.BASE_ID);
        try {
            return commonService.getConnection(url).get().wholeText();
        } catch (IOException e) {
            logger.error(e.toString());
        }
        return null;
    }

    public SalaryPackage generatePackageData(Document doc) {
        SalaryPackage pack = new SalaryPackage();
        Optional<Element> tableOptional = doc.select("div.typeoption").stream().findFirst();
        if (tableOptional.isEmpty()) {
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
            }
        });
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
