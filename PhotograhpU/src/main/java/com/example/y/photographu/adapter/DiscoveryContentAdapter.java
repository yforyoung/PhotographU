package com.example.y.photographu.adapter;

import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.text.Layout;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.y.photographu.R;
import com.example.y.photographu.beans.Topic;

import java.util.List;

public class DiscoveryContentAdapter extends RecyclerView.Adapter<DiscoveryContentAdapter.ViewHolder> {
    private List<Topic> topicList;
    private OnItemClickListener onItemClickListener;


    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

    public DiscoveryContentAdapter(List<Topic> topicList) {
        this.topicList = topicList;
    }

    public interface OnItemClickListener {
        void onItemClick(View v, int tag);
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_discovery_1, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Topic topic = topicList.get(position);
        holder.head.setBackgroundResource(topic.getUser().getHeadImage());
        holder.name.setText(topic.getUser().getNickName());
        holder.img.setBackgroundResource(topic.getContentImage());
        holder.thumbUpCount.setText(topic.getThumbUp() + "");
        holder.commentCount.setText(topic.getCommentList().size() + "");
        holder.content.setText(topic.getContent());
        holder.img.setTag(position);
        holder.thumbUp.setTag(position);
    }

    @Override
    public int getItemCount() {
        return topicList.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        ImageView head, img;
        TextView name, content, thumbUp, thumbUpCount, comment, commentCount, share;

        ViewHolder(View itemView) {
            super(itemView);
            head = itemView.findViewById(R.id.item_discovery_1_head);
            img = itemView.findViewById(R.id.item_discovery_1_img);
            name = itemView.findViewById(R.id.item_discovery_1_name);
            content = itemView.findViewById(R.id.item_discovery_1_content);
            thumbUp = itemView.findViewById(R.id.item_discovery_1_thumb_up);
            thumbUpCount = itemView.findViewById(R.id.item_discovery_1_thumb_up_count);
            comment = itemView.findViewById(R.id.item_discovery_1_comment);
            commentCount = itemView.findViewById(R.id.item_discovery_1_comment_count);
            share = itemView.findViewById(R.id.item_discovery_1_share);
            thumbUp.setOnClickListener(this);
            comment.setOnClickListener(this);
            img.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.item_discovery_1_thumb_up:
                    if (thumbUp.getText().toString().equals("点赞")) {
                        thumbUpCount.setText((topicList.get((int) v.getTag()).getThumbUp()+1)+"");
                        thumbUp.setText("已赞");
                        thumbUp.setTextColor(Color.RED);
                    } else {
                        thumbUpCount.setText(topicList.get((int) v.getTag()).getThumbUp()+"");
                        thumbUp.setText("点赞");
                        thumbUp.setTextColor(Color.parseColor("#ff62b6d7"));
                    }
                    break;
                case R.id.item_discovery_1_comment:
                    Toast.makeText(v.getContext(), "该功能暂未开放", Toast.LENGTH_SHORT).show();
                    break;
                case R.id.item_discovery_1_img:
                    if (onItemClickListener != null)
                        onItemClickListener.onItemClick(v, (int) v.getTag());
                    break;
                default:

            }
        }
    }
}
