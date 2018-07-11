package com.example.y.photographu.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.y.photographu.R;
import com.example.y.photographu.beans.Style;

import java.util.List;

public class StyleAdapter extends RecyclerView.Adapter<StyleAdapter.ViewHolder> {
    private List<Style> StyleList;
    private OnItemClickListener onItemClickListener;
    private Context context;

    public StyleAdapter(List<Style> StyleList,Context context) {
        this.StyleList = StyleList;
        this.context=context;
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

    public interface OnItemClickListener {
        void onItemClick(View v, int tag);
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull final ViewGroup parent, int viewStyle) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_home_style, parent, false);
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (onItemClickListener != null)
                    onItemClickListener.onItemClick(v, (Integer) v.getTag());
            }
        });
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        holder.title.setText(StyleList.get(position).getName());

      //  holder.img.setBackgroundResource(StyleList.get(position).getImage());
        Glide.with(context).load("http://www.xhban.com:8080/photograph_u/style_images/"
                +StyleList.get(position).getImage())
                .into(holder.img);
        holder.itemView.setTag(position);
    }

    @Override
    public int getItemCount() {
        return StyleList.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        ImageView img;
        TextView title, body;

        ViewHolder(final View itemView) {
            super(itemView);
            img = itemView.findViewById(R.id.item_home_style_img);
            title = itemView.findViewById(R.id.item_home_style_title);
           // body = itemView.findViewById(R.id.item_home_style_body);
        }
    }
}
