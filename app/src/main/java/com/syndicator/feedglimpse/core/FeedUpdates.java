package com.syndicator.feedglimpse.core;

import com.syndicator.feedglimpse.data.FeedUpdate;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Mahendra on 4/1/2018.
 */

public class FeedUpdates {
    private Callback callback;
    private static final String BASE_URL =
            "http://rss.nytimes.com/services/xml/rss/nyt/World.xml";

    public FeedUpdates(Callback callback) {
        this.callback = callback;
    }

    public void requestAllFeeds() {
        Executor executor = new Executor(BASE_URL, callback);
        executor.execute();
    }
}
