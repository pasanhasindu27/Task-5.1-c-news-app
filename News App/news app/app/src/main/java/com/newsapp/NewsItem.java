package com.newsapp;

public class NewsItem {
    public int imageResId;
    public String title;
    public String description;

    public NewsItem(int imageResId, String title, String description) {
        this.imageResId = imageResId;
        this.title = title;
        this.description = description;
    }
}
