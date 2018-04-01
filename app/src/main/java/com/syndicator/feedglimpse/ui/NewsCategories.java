package com.syndicator.feedglimpse.ui;

/**
 * Created by Mahendra on 4/1/2018.
 */

public class NewsCategories {
    private static Boolean categoryWorld = true;
    private static Boolean categorySports = false;

    private static final String WORLD_NEWS_URL =
            "http://rss.nytimes.com/services/xml/rss/nyt/World.xml";

    private static final String SPORTS_NEWS_URL =
            "http://rss.nytimes.com/services/xml/rss/nyt/Sports.xml";

    public static void setDefaultCategories() {
        categoryWorld = true;
        categorySports = false;
    }

    public static Boolean getCategoryWorld() {
        return categoryWorld;
    }

    public static void setCategoryWorld(Boolean pCategoryWorld) {
        categoryWorld = pCategoryWorld;
    }

    public static Boolean getCategorySports() {
        return categorySports;
    }

    public static void setCategorySports(Boolean pCategorySports) {
        categorySports = pCategorySports;
    }

    public static String getWorldNewsUrl() {
        return WORLD_NEWS_URL;
    }

    public static String getSportsNewsUrl() {
        return SPORTS_NEWS_URL;
    }
}
