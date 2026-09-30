package com.example.user.exams;

import java.util.Calendar;

public class Image {
    private String idImage;
    private String title;
    private String location;


    public Image(String title, String location) {
        this.idImage = String.valueOf(Calendar.getInstance().getTimeInMillis());
        this.title = title;
        this.location = location;
    }

    public Image() {
    }



    public String getIdImage() {
        return idImage;
    }

    public void setIdImage(String idImage) {
        this.idImage = idImage;
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


