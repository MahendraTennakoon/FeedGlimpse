package com.syndicator.feedglimpse;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by Mahendra on 3/31/2018.
 */

class FeedsAdapter extends RecyclerView.Adapter<FeedsAdapter.ViewHolder> {

    ArrayList<String> updates;


    public FeedsAdapter(ArrayList<String> bulletines) {
        this.updates = bulletines;
    }

    @NonNull
    @Override
    public FeedsAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.row, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull FeedsAdapter.ViewHolder holder, int position) {
        holder.bulletinSourceName.setText(updates.get(position));
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
