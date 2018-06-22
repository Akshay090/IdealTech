package com.theandroidprojects.idealtech.model;

public class subCategory {

    private String catID, duration, questions, subName, date;

    public subCategory() {
    }


    public subCategory(String catID, String durations, String questions, String subName, String date) {
        this.catID = catID;
        this.duration = durations;
        this.questions = questions;
        this.subName = subName;
        this.date = date;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getCatID() {
        return catID;
    }

    public void setCatID(String catID) {
        this.catID = catID;
    }

    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }

    public String getQuestions() {
        return questions;
    }

    public void setQuestions(String questions) {
        this.questions = questions;
    }

    public void setSubName(String subName) {
        this.subName = subName;
    }

    public String getSubName() {
        return subName;
    }
}
