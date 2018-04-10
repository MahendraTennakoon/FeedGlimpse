package com.syndicator.feedglimpse.ui;

import android.os.Bundle;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.MenuItem;
import android.widget.CompoundButton;
import android.widget.Switch;

import com.syndicator.feedglimpse.R;
import com.syndicator.feedglimpse.core.Callback;
import com.syndicator.feedglimpse.core.FeedUpdates;
import com.syndicator.feedglimpse.data.FeedUpdate;
import com.syndicator.feedglimpse.data.FeedsAdapter;
import com.syndicator.feedglimpse.util.XMLParseUtil;

import org.xmlpull.v1.XmlPullParserException;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements Callback {

    ArrayList<FeedUpdate> updates = new ArrayList<>();
    RecyclerView feedsRecyclerView;
    RecyclerView.LayoutManager feedsLayoutManager;
    RecyclerView.Adapter feedsAdapter;

    private DrawerLayout mDrawerLayout;
    private ActionBarDrawerToggle mToggle;
    private Switch onOffSwitch;

    FeedUpdates feedUpdates;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initSidebar();

        // MainActivity implements the Callback interface and hence can be passed as an argument
        // to Feedupdates constructor.
        feedUpdates = new FeedUpdates(this);
        feedUpdates.requestAllFeeds();
    }

    private void loadFeedUpdates() {
        feedsRecyclerView = findViewById(R.id.feeds_recycler_view);
        feedsRecyclerView.setHasFixedSize(true);
        feedsLayoutManager = new LinearLayoutManager(this);
        feedsAdapter = new FeedsAdapter(updates,this);
        feedsRecyclerView.setLayoutManager(feedsLayoutManager);
        feedsRecyclerView.setAdapter(feedsAdapter);
    }

    private void initSidebar() {
        mDrawerLayout = findViewById(R.id.drawerLayout);
        mToggle = new ActionBarDrawerToggle(this, mDrawerLayout, R.string.open, R.string.close);
        mDrawerLayout.addDrawerListener(mToggle);
        mToggle.syncState();
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (mToggle.onOptionsItemSelected(item)) {
            onOffSwitch = findViewById(R.id.app_bar_switch_verge);
            onOffSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {

                @Override
                public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                    Log.v("Switch State=", "" + isChecked);
                    if (isChecked) {
                        NewsCategories.setCategorySports(true);
                    } else {
                        NewsCategories.setCategorySports(false);
                    }

                    feedUpdates.requestAllFeeds();
                }

            });
            return true;
        }

        return onOptionsItemSelected(item);
    }

    @Override
    public void onCallbackCompleted(String[] data) {

        for (int i = 0; i < data.length; i++) {
            if (data[i] == null) {
                break;
            }
            InputStream stream = new ByteArrayInputStream(data[i].getBytes(StandardCharsets.UTF_8));
            try {
                updates.addAll(XMLParseUtil.parseFeed(stream));

                loadFeedUpdates();
            } catch (XmlPullParserException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
