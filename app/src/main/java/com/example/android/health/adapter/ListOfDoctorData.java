package com.example.android.health.adapter;

/**
 * Created by Android on 25-01-2017.
 */
public class ListOfDoctorData {
    private String text;
    private int image;

    public ListOfDoctorData(int image, String text) {
        this.image = image;
        this.text = text;
    }

    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

}
