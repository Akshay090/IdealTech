package com.theandroidprojects.idealtech.model;

public class Job {

    private String TITLE, LOCATION, EXPERIENCE, POSTED_ON;

    public Job() {
    }

    public Job(String job_title, String job_location, String job_experience, String job_postedOn) {
        TITLE = job_title;
        LOCATION = job_location;
        EXPERIENCE = job_experience;
        POSTED_ON = job_postedOn;
    }

    public String getTITLE() {
        return TITLE;
    }

    public void setTITLE(String TITLE) {
        this.TITLE = TITLE;
    }

    public String getLOCATION() {
        return LOCATION;
    }

    public void setLOCATION(String LOCATION) {
        this.LOCATION = LOCATION;
    }

    public String getEXPERIENCE() {
        return EXPERIENCE;
    }

    public void setEXPERIENCE(String EXPERIENCE) {
        this.EXPERIENCE = EXPERIENCE;
    }

    public String getPOSTED_ON() {
        return POSTED_ON;
    }

    public void setPOSTED_ON(String POSTED_ON) {
        this.POSTED_ON = POSTED_ON;
    }
}
