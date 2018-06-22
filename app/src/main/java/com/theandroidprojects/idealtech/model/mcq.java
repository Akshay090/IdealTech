package com.theandroidprojects.idealtech.model;

public class mcq {

    private String subCatID,question,A,B,C,D,ans;

    public mcq() {
    }


    public mcq(String subCatID, String question,
               String a, String b, String c, String d, String ans) {
        this.subCatID = subCatID;
        this.question = question;
        A = a;
        B = b;
        C = c;
        D = d;
        this.ans = ans;
    }

    public String getSubCatID() {
        return subCatID;
    }

    public void setSubCatID(String subCatID) {
        this.subCatID = subCatID;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public String getA() {
        return A;
    }

    public void setA(String a) {
        A = a;
    }

    public String getB() {
        return B;
    }

    public void setB(String b) {
        B = b;
    }

    public String getC() {
        return C;
    }

    public void setC(String c) {
        C = c;
    }

    public String getD() {
        return D;
    }

    public void setD(String d) {
        D = d;
    }

    public String getAns() {
        return ans;
    }

    public void setAns(String ans) {
        this.ans = ans;
    }
}
