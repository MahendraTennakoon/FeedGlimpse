package com.syndicator.feedglimpse.data;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Mahendra on 4/1/2018.
 */

public class FeedUpdate {
    private int id;
    private String title;

    public FeedUpdate(int id, String title) {
        this.id = id;
        this.title = title;
    }

    public FeedUpdate() {
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


}
