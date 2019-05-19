package com.chaboox.algeriaplus.model;

public class JsonDataModel {
    private int categories;
    private String date;
    private String featured_media_url;
    private int id;
    private String link;
    private String title_rendered;

    public int getCategories() {
        return this.categories;
    }

    public void setCategorie(int categories) {
        this.categories = categories;
    }

    public String getFeaturedMediaUrl() {
        return this.featured_media_url;
    }

    public void setFeaturedMediaUrl(String featured_media_url) {
        this.featured_media_url = featured_media_url;
    }

    public String getTitleRendered() {
        return this.title_rendered;
    }

    public void setTitleRendered(String title_rendered) {
        this.title_rendered = title_rendered;
    }

    public int getId() {
        return this.id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDate() {
        return this.date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getLink() {
        return this.link;
    }

    public void setLink(String link) {
        this.link = link;
    }
}
