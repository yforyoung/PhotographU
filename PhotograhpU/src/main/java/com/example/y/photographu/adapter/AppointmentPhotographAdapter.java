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

public class AppointmentPhotographAdapter extends RecyclerView.Adapter<AppointmentPhotographAdapter.ViewHolder> {
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.item_photograph,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 6;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView img,favorite,turnedIn,share;
        TextView id;
        ViewHolder(final View itemView) {
            super(itemView);
            img=itemView.findViewById(R.id.item_photograph_img);
            favorite=itemView.findViewById(R.id.item_photograph_favorite);
            turnedIn=itemView.findViewById(R.id.item_photograph_turned_in);
            share=itemView.findViewById(R.id.item_photograph_share);
            id=itemView.findViewById(R.id.item_photograph_id);
            img.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent=new Intent(itemView.getContext(), IntroduceActivity.class);
                    itemView.getContext().startActivity(intent);
                }
            });
        }
    }
}
