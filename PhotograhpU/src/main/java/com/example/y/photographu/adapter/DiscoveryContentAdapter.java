package com.example.y.potographu.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.y.potographu.R;

public class DiscoveryContentAdapter extends RecyclerView.Adapter<DiscoveryContentAdapter.ViewHolder> {
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_discovery_1, parent, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 3;
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        ImageView head, img;
        TextView name, order, content, thumbUp, thumbUpCount, comment, commentCount, share;

        ViewHolder(View itemView) {
            super(itemView);
            head = itemView.findViewById(R.id.item_discovery_1_head);
            img = itemView.findViewById(R.id.item_discovery_1_img);
            name = itemView.findViewById(R.id.item_discovery_1_name);
            order = itemView.findViewById(R.id.item_discovery_1_order);
            content = itemView.findViewById(R.id.item_discovery_1_content);
            thumbUp = itemView.findViewById(R.id.item_discovery_1_thumb_up);
            thumbUpCount = itemView.findViewById(R.id.item_discovery_1_thumb_up_count);
            comment = itemView.findViewById(R.id.item_discovery_1_comment);
            commentCount = itemView.findViewById(R.id.item_discovery_1_comment_count);
            share = itemView.findViewById(R.id.item_discovery_1_share);
        }
    }
}
