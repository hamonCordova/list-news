package com.blackscreen.listbrnews.models;

/**
 * Created by Hamon on 14/02/2018.
 */

public class NewsModel {

    private String title;
    private String publisher;
    private String publishDateAsString;
    private String newsUrl;
    private String imageUrl;
    private String description;

    public NewsModel(String title, String publisher, String publishDateAsString, String newsUrl, String imageUrl, String description) {
        this.title = title;
        this.publisher = publisher;
        this.publishDateAsString = publishDateAsString;
        this.newsUrl = newsUrl;
        this.imageUrl = imageUrl;

        if (description.equals("null")) {
            this.description = "No description yeat";
        } else {
            this.description = description;
        }

    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getPublisher() {
        return publisher;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

    public String getPublishDateAsString() {
        return publishDateAsString;
    }

    public void setPublishDateAsString(String publishDateAsString) {
        this.publishDateAsString = publishDateAsString;
    }

    public String getNewsUrl() {
        return newsUrl;
    }

    public void setNewsUrl(String newsUrl) {
        this.newsUrl = newsUrl;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
