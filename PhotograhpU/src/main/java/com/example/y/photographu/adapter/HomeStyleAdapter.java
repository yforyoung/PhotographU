package com.example.y.photographu.adapter;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.y.photographu.IntroduceActivity;
import com.example.y.photographu.R;

public class HomeStyleAdapter extends RecyclerView.Adapter<HomeStyleAdapter.ViewHolder> {

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull final ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.item_home_style,parent,false);
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 5;
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        ImageView img;
        TextView title,body;
        ViewHolder(final View itemView) {
            super(itemView);
            img=itemView.findViewById(R.id.item_home_style_img);
            title=itemView.findViewById(R.id.item_home_style_title);
            body=itemView.findViewById(R.id.item_home_style_body);

        }
    }
}
