package com.syndicator.feedglimpse.core;

import com.syndicator.feedglimpse.ui.NewsCategories;

import java.util.ArrayList;

/**
 * Created by Mahendra on 4/1/2018.
 */

public class FeedUpdates {
    private Callback callback;
    private static final String BASE_URL =
            "http://rss.nytimes.com/services/xml/rss/nyt/World.xml";

    ArrayList<String> URLs;

    public FeedUpdates(Callback callback) {
        this.callback = callback;
    }

    public void requestAllFeeds() {
        URLs = new ArrayList<>();
        URLs.add(BASE_URL);

        if (NewsCategories.getCategorySports()) {
            URLs.add(NewsCategories.getSportsNewsUrl());
        }

        Executor executor = new Executor(URLs, callback);
        executor.execute();
    }
}
