package com.example.movielocater;

public class ListItem {
    private String head;
    private String cinemas;
    private String img_url;

    public String getHead() {
        return head;
    }

    public ListItem(String head, String cinemas, String img_url) {
        this.head = head;
        this.cinemas = cinemas;
        this.img_url = img_url;
    }

    public String getCinemas() {
        return cinemas;
    }

    public String getImg_url() {
        return img_url;
    }
}
