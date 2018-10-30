package com.jerryxie.forum.doubaofu.models;

public class Package {
    enum JobType {
        OTHER, FULL_TIME, PART_TIME
    }

    enum MaxDegree {
        BACHELOR, MASTER, DOCTOR
    }

    enum Status {
        UNKNOWN, FRESH_OUT_NO_EXPERIENCE, JOB_CHANGE, OTHER
    }

    private int tid;
    private int jobYear;
    private int jobMonthStart;
    private int jobMonthEnd;
    private String jobSource;
    private String positionCategory;
    private JobType jobType;
    private MaxDegree degree;
    private String workingExperience;
    private Status status;
    private String companyName;
    private int base;
    private int rsu;
    private String vestSchedule;
    private int signOn;
    private int bouns;
    private int relocation;

    public int getTid() {
        return tid;
    }

    public void setTid(int tid) {
        this.tid = tid;
    }

    public int getJobYear() {
        return jobYear;
    }

    public void setJobYear(
            int jobYear) {
        this.jobYear = jobYear;
    }

    public int getJobMonthStart() {
        return jobMonthStart;
    }

    public void setJobMonthStart(
            int jobMonthStart) {
        this.jobMonthStart = jobMonthStart;
    }

    public int getJobMonthEnd() {
        return jobMonthEnd;
    }

    public void setJobMonthEnd(
            int jobMonthEnd) {
        this.jobMonthEnd = jobMonthEnd;
    }

    public String getJobSource() {
        return jobSource;
    }

    public void setJobSource(
            String jobSource) {
        this.jobSource = jobSource;
    }

    public String getPositionCategory() {
        return positionCategory;
    }

    public void setPositionCategory(
            String positionCategory) {
        this.positionCategory = positionCategory;
    }

    public JobType getJobType() {
        return jobType;
    }

    public void setJobType(
            JobType jobType) {
        this.jobType = jobType;
    }

    public MaxDegree getDegree() {
        return degree;
    }

    public void setDegree(
            MaxDegree degree) {
        this.degree = degree;
    }

    public String getWorkingExperience() {
        return workingExperience;
    }

    public void setWorkingExperience(
            String workingExperience) {
        this.workingExperience = workingExperience;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(
            Status status) {
        this.status = status;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(
            String companyName) {
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

    public void setVestSchedule(
            String vestSchedule) {
        this.vestSchedule = vestSchedule;
    }

    public int getSignOn() {
        return signOn;
    }

    public void setSignOn(int signOn) {
        this.signOn = signOn;
    }

    public int getBouns() {
        return bouns;
    }

    public void setBouns(int bouns) {
        this.bouns = bouns;
    }

    public int getRelocation() {
        return relocation;
    }

    public void setRelocation(
            int relocation) {
        this.relocation = relocation;
    }

    public String getCompeteOffer() {
        return competeOffer;
    }

    public void setCompeteOffer(
            String competeOffer) {
        this.competeOffer = competeOffer;
    }

    public boolean isAccept() {
        return accept;
    }

    public void setAccept(
            boolean accept) {
        this.accept = accept;
    }

    public int getAnnualRefresh() {
        return annualRefresh;
    }

    public void setAnnualRefresh(
            int annualRefresh) {
        this.annualRefresh = annualRefresh;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(
            String comment) {
        this.comment = comment;
    }

    private String competeOffer;
    private boolean accept;
    private int annualRefresh;
    private String comment;

}
