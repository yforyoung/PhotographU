package com.example.y.photographu.adapter;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.y.photographu.IntroduceActivity;
import com.example.y.photographu.R;
import com.example.y.photographu.beans.Photographer;
import java.util.List;

public class AppointmentPhotographAdapter extends RecyclerView.Adapter<AppointmentPhotographAdapter.ViewHolder> {
    private List<Photographer> photographerList;
    private OnItemClickListener onItemClickListener;

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

    public AppointmentPhotographAdapter(List<Photographer> photographerList) {
        this.photographerList = photographerList;
    }

    public interface OnItemClickListener {
        void onItemClick(View v, int tag);
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_photograph, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Photographer photographer = photographerList.get(position);
        holder.img.setImageResource(photographer.getUser().getHeadImage());
        holder.id.setText(photographer.getUser().getNickName());
        holder.img.setTag(position);
    }

    @Override
    public int getItemCount() {
        return photographerList.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        ImageView img, favorite, turnedIn, share;
        TextView id;
        int f = 0, t = 0;

        ViewHolder(final View itemView) {
            super(itemView);
            img = itemView.findViewById(R.id.item_photograph_img);
            favorite = itemView.findViewById(R.id.item_photograph_favorite);
            turnedIn = itemView.findViewById(R.id.item_photograph_turned_in);
            share = itemView.findViewById(R.id.item_photograph_share);
            id = itemView.findViewById(R.id.item_photograph_id);
            img.setOnClickListener(this);
            favorite.setOnClickListener(this);
            turnedIn.setOnClickListener(this);
            share.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.item_photograph_img:
                    Intent intent=new Intent(v.getContext(), IntroduceActivity.class);
                    intent.putExtra("p",photographerList.get((Integer) v.getTag()));
                    v.getContext().startActivity(intent);
                    /*if (onItemClickListener != null)
                        onItemClickListener.onItemClick(v, (Integer) v.getTag());*/
                    break;
                case R.id.item_photograph_favorite:
                    if (f == 0) {
                        favorite.setImageResource(R.drawable.ic_favorite_select);
                        Toast.makeText(v.getContext(), "已收藏", Toast.LENGTH_SHORT).show();
                        f = 1;
                    } else {
                        favorite.setImageResource(R.drawable.ic_favorite);
                        Toast.makeText(v.getContext(), "取消收藏", Toast.LENGTH_SHORT).show();
                        f = 0;
                    }
                    break;
                case R.id.item_photograph_turned_in:
                    if (t == 0) {
                        turnedIn.setImageResource(R.drawable.ic_turned_in_select);
                        Toast.makeText(v.getContext(), "已关注", Toast.LENGTH_SHORT).show();
                        t = 1;
                    } else {
                        turnedIn  .setImageResource(R.drawable.ic_turned_in);
                        Toast.makeText(v.getContext(), "取消关注", Toast.LENGTH_SHORT).show();
                        t = 0;
                    }
                    break;
                default:
            }
        }
    }
}
