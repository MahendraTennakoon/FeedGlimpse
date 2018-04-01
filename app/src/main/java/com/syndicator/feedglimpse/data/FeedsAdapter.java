package com.syndicator.feedglimpse.data;

import android.arch.lifecycle.ViewModel;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.syndicator.feedglimpse.R;

import java.util.ArrayList;

/**
 * Created by Mahendra on 3/31/2018.
 */

public class FeedsAdapter extends RecyclerView.Adapter<FeedsAdapter.ViewHolder> {

    ArrayList<FeedUpdate> updates;


    public FeedsAdapter(ArrayList<FeedUpdate> bulletines) {
        this.updates = bulletines;
    }

    public void updateData(ArrayList<FeedUpdate> latest_updates) {
        updates.clear();
        updates.addAll(latest_updates);
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public FeedsAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.row, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull FeedsAdapter.ViewHolder holder, int position) {
        holder.bulletinSourceName.setText(updates.get(position).getTitle());
    }

    @Override
    public int getItemCount() {
        return updates.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        public TextView bulletinSourceName;

        public ViewHolder(View itemView) {
            super(itemView);

            bulletinSourceName = itemView.findViewById(R.id.source_name);
        }
    }

}
