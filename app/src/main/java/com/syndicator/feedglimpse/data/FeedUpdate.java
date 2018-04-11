package com.syndicator.feedglimpse.data;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Mahendra on 4/1/2018.
 */

public class FeedUpdate {
    @SerializedName("id")
    private int id;
    @SerializedName("title")
    private String title;
    @SerializedName("description")
    private String description;
    private String url;

    public FeedUpdate() {
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        if (url.length() > 2) {
            this.url = url;
        }
    }


}
