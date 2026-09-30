package com.example.user.exams;

import java.util.Calendar;

public class Doc {
    private String idDoc;
    private String title;
    private String location;
    private String Image;

    public Doc( String title, String location) {
        this.idDoc = String.valueOf(Calendar.getInstance().getTimeInMillis());
        this.title = title;
        this.location = location;
        Image = "";
    }

    public Doc() {
    }

    public String getImage() {
        return Image;
    }

    public void setImage(String image) {
        Image = image;
    }

    public String getIdDoc() {
        return idDoc;
    }

    public void setIdDoc(String idDoc) {
        this.idDoc = idDoc;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }
}
