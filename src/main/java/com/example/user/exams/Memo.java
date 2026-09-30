package com.example.user.exams;

import java.util.Calendar;

public class Memo {
    private String idMemo;
    private String subject;
    private String description;
    private String dateStart;
    private String dateFinish;
    private String time1;
    private String time2;
    private String time3;

    public Memo( String subject, String description,String dateStart ,String dateFinish, String time1, String time2, String time3) {
        this.idMemo  = String.valueOf(  Calendar.getInstance().getTimeInMillis());
        this.subject = subject;
        this.description = description;
        this.dateStart = dateStart;
        this.dateFinish = dateFinish;
        this.time1 = time1;
        this.time2 = time2;
        this.time3 = time3;
    }

    public Memo() {
    }

    public String getIdMemo() {
        return idMemo;
    }

    public void setIdMemo(String idMemo) {
        this.idMemo = idMemo;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDateStart() {
        return dateStart;
    }

    public void setDateStart(String dateStart) {
        this.dateStart = dateStart;
    }

    public String getDateFinish() {
        return dateFinish;
    }

    public void setDateFinish(String dateFinish) {
        this.dateFinish = dateFinish;
    }

    public String getTime1() {
        return time1;
    }

    public void setTime1(String time1) {
        this.time1 = time1;
    }

    public String getTime2() {
        return time2;
    }

    public void setTime2(String time2) {
        this.time2 = time2;
    }

    public String getTime3() {
        return time3;
    }

    public void setTime3(String time3) {
        this.time3 = time3;
    }


}