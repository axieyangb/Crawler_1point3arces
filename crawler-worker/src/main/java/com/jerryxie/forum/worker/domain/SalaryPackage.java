package com.jerryxie.forum.worker.domain;

import java.util.ArrayList;
import java.util.List;

public class SalaryPackage {

    public String getCurrentWork() {
        return currentWork;
    }

    public void setCurrentWork(String currentWork) {
        this.currentWork = currentWork;
    }

    public SalaryPackage() {
        this.comments = new ArrayList<>();
    }

    public enum JobType {
        OTHER, FULL_TIME, INTERN, CONTRACTOR
    }

    public enum MaxDegree {
        BACHELOR, MASTER, DOCTOR
    }

    public enum Status {
        UNKNOWN, FRESH_OUT_NO_EXPERIENCE, JOB_CHANGE, OTHER
    }

    public enum Decision {
        UNKNOWN, ACCEPT, NOT_ACCEPT
    }

    private int tid;
    private int jobYear;
    private int jobMonthStart;
    private int jobMonthEnd;
    private String title;
    private String jobSource;
    private String positionCategory;
    private JobType jobType;
    private MaxDegree degree;
    private String workingExperience;
    private Status status;
    private String companyName;
    private String currentWork;
    private String area;
    private int base;
    private int rsu;
    private String vestSchedule;
    private int signOn;
    private String bonus;

    private int relocation;
    private List<CommentDetail> comments;

    public int getTid() {
        return tid;
    }

    public void setTid(int tid) {
        this.tid = tid;
    }

    public int getJobYear() {
        return jobYear;
    }

    public void setJobYear(int jobYear) {
        this.jobYear = jobYear;
    }

    public int getJobMonthStart() {
        return jobMonthStart;
    }

    public void setJobMonthStart(int jobMonthStart) {
        this.jobMonthStart = jobMonthStart;
    }

    public int getJobMonthEnd() {
        return jobMonthEnd;
    }

    public void setJobMonthEnd(int jobMonthEnd) {
        this.jobMonthEnd = jobMonthEnd;
    }

    public String getJobSource() {
        return jobSource;
    }

    public void setJobSource(String jobSource) {
        this.jobSource = jobSource;
    }

    public String getPositionCategory() {
        return positionCategory;
    }

    public void setPositionCategory(String positionCategory) {
        this.positionCategory = positionCategory;
    }

    public JobType getJobType() {
        return jobType;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public void setJobType(JobType jobType) {
        this.jobType = jobType;
    }

    public MaxDegree getDegree() {
        return degree;
    }

    public void setDegree(MaxDegree degree) {
        this.degree = degree;
    }

    public String getWorkingExperience() {
        return workingExperience;
    }

    public void setWorkingExperience(String workingExperience) {
        this.workingExperience = workingExperience;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public int getBase() {
        return base;
    }

    public void setBase(int base) {
        this.base = base;
    }

    public int getRsu() {
        return rsu;
    }

    public void setRsu(int rsu) {
        this.rsu = rsu;
    }

    public String getVestSchedule() {
        return vestSchedule;
    }

    public void setVestSchedule(String vestSchedule) {
        this.vestSchedule = vestSchedule;
    }

    public int getSignOn() {
        return signOn;
    }

    public void setSignOn(int signOn) {
        this.signOn = signOn;
    }

    public String getBonus() {
        return bonus;
    }

    public void setBonus(String bonus) {
        this.bonus = bonus;
    }

    public int getRelocation() {
        return relocation;
    }

    public void setRelocation(int relocation) {
        this.relocation = relocation;
    }

    public String getCompeteOffer() {
        return competeOffer;
    }

    public void setCompeteOffer(String competeOffer) {
        this.competeOffer = competeOffer;
    }

    public Decision getAccept() {
        return accept;
    }

    public void setAccept(Decision accept) {
        this.accept = accept;
    }

    public int getAnnualRefresh() {
        return annualRefresh;
    }

    public void setAnnualRefresh(int annualRefresh) {
        this.annualRefresh = annualRefresh;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public List<CommentDetail> getComments() {
        return comments;
    }

    @Override
    public String toString() {
        return "SalaryPackage [tid=" + tid + ", jobYear=" + jobYear + ", jobMonthStart=" + jobMonthStart
                + ", jobMonthEnd=" + jobMonthEnd + ", title=" + title + ", jobSource=" + jobSource
                + ", positionCategory=" + positionCategory + ", jobType=" + jobType + ", degree=" + degree
                + ", workingExperience=" + workingExperience + ", status=" + status + ", companyName=" + companyName
                + ", currentWork=" + currentWork + ", area=" + area + ", base=" + base + ", rsu=" + rsu
                + ", vestSchedule=" + vestSchedule + ", signOn=" + signOn + ", bonus=" + bonus + ", relocation="
                + relocation + ", comments=" + comments + ", competeOffer=" + competeOffer + ", accept=" + accept
                + ", annualRefresh=" + annualRefresh + ", comment=" + comment + "]";
    }

    public void setComments(List<CommentDetail> comments) {
        this.comments = comments;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    private String competeOffer;
    private Decision accept;
    private int annualRefresh;
    private String comment;

}
