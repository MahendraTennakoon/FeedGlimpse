package com.syndicator.feedglimpse.data;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.util.Log;
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
    Context context;


    public FeedsAdapter(ArrayList<FeedUpdate> bulletines, Context context) {
        this.updates = bulletines;
        this.context = context;
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
        holder.txtDescription.setText(updates.get(position).getDescription());
    }

    @Override
    public int getItemCount() {
        return updates.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        public TextView bulletinSourceName;
        public TextView txtDescription;

        public ViewHolder(View itemView) {
            super(itemView);

            bulletinSourceName = itemView.findViewById(R.id.source_name);
            txtDescription = itemView.findViewById(R.id.description);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    String url = updates.get(getAdapterPosition()).getUrl();
                    if(!TextUtils.isEmpty(url)){
                        Log.d("MYAPP","URL : " + url);
                        Uri uriUrl = Uri.parse(url);
                        Intent intent = new Intent(Intent.ACTION_VIEW, uriUrl);
                        context.startActivity(intent);
                    }
                }
            });
        }
    }

}
