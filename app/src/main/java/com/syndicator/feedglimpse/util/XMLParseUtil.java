package com.syndicator.feedglimpse.util;

import android.util.Log;
import android.util.Xml;

import com.syndicator.feedglimpse.data.FeedUpdate;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlPullParserFactory;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

/**
 * Created by Mahendra on 4/1/2018.
 */

public class XMLParseUtil {

    public static ArrayList<FeedUpdate> parseFeed(InputStream inputStream) throws XmlPullParserException, IOException {
        XmlPullParserFactory factory = XmlPullParserFactory.newInstance();
        factory.setNamespaceAware(true);
        XmlPullParser xpp = factory.newPullParser();
        xpp.setInput(inputStream, null);
        ArrayList<FeedUpdate> updates = new ArrayList<>();

        int eventType = xpp.getEventType();
        boolean done = false;
        FeedUpdate item = null;
        String ITEM = "item";

        while (eventType != XmlPullParser.END_DOCUMENT && !done) {
            String name = null;
            switch (eventType) {
                case XmlPullParser.START_DOCUMENT:
                    break;
                case XmlPullParser.START_TAG:
                    name = xpp.getName();
                    if (name.equalsIgnoreCase(ITEM)) {
                        Log.i("new item", "Create new item");
                        item = new FeedUpdate();
                    } else if (item != null) {
                        if (name.equalsIgnoreCase("description")) {
                            Log.i("Attribute", "description");
                            item.setDescription(xpp.nextText().trim());
                        } else if (name.equalsIgnoreCase("title")) {
                            Log.i("Attribute", "title");
                            item.setTitle(xpp.nextText().trim());
                        } else if (name.equalsIgnoreCase("link")) {
                            Log.i("Attribute", "link");
                            item.setUrl(xpp.nextText().trim());
                        }
                    }
                    break;
                case XmlPullParser.END_TAG:
                    name = xpp.getName();
                    Log.i("End tag", name);
                    if (name.equalsIgnoreCase(ITEM) && item != null) {
                        Log.i("Added", item.toString());
                        updates.add(item);
                    } else if (name.equalsIgnoreCase("channel")) {
                        done = true;
                    }
                    break;
            }
            eventType = xpp.next();
        }

        return updates;
    }
}
