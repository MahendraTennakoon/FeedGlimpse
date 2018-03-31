package com.syndicator.feedglimpse.core;

import com.syndicator.feedglimpse.data.FeedUpdate;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Mahendra on 4/1/2018.
 */

public class FeedUpdates {
    public ArrayList<FeedUpdate> requestAllFeeds() {
        FeedUpdate update = new FeedUpdate();
        update.setTitle("The Verge");
        ArrayList<FeedUpdate> updates = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            update.setId(i);
            updates.add(update);
        }
        return updates;
    }
}
