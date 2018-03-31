package com.syndicator.feedglimpse;

import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;
import android.view.MenuItem;

public class MainActivity extends AppCompatActivity {

    ArrayList<String> updates;
    RecyclerView feedsRecyclerView;
    RecyclerView.LayoutManager feedsLayoutManager;
    RecyclerView.Adapter feedsAdapter;

    private DrawerLayout mDrawerLayout;
    private ActionBarDrawerToggle mToggle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        loadFeedUpdates();
    }

    private void loadFeedUpdates() {
        feedsRecyclerView = findViewById(R.id.feeds_recycler_view);

        updates = new ArrayList<>();
        updates.add("Update 01");
        updates.add("Update 02");

        for (int i = 0; i < 100; i++) {
            updates.add("Verge " + i);
        }

        feedsRecyclerView.setHasFixedSize(true);
        feedsLayoutManager = new LinearLayoutManager(this);
        feedsAdapter = new FeedsAdapter(updates);
        feedsRecyclerView.setLayoutManager(feedsLayoutManager);
        feedsRecyclerView.setAdapter(feedsAdapter);

        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawerLayout);
        mToggle = new ActionBarDrawerToggle(this, mDrawerLayout,R.string.open,R.string.close);

        mDrawerLayout.addDrawerListener(mToggle);
        mToggle.syncState();

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(mToggle.onOptionsItemSelected(item)){
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
