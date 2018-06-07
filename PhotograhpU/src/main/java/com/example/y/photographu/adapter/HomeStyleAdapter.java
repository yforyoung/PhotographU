package com.example.y.photographu.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import com.example.y.photographu.R;
import com.example.y.photographu.beans.Type;
import java.util.List;

public class HomeStyleAdapter extends RecyclerView.Adapter<HomeStyleAdapter.ViewHolder> {
    private List<Type> typeList;
    private OnItemClickListener onItemClickListener;

    public HomeStyleAdapter(List<Type> typeList) {
        this.typeList = typeList;
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

    public interface OnItemClickListener {
        void onItemClick(View v, int tag);
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull final ViewGroup parent, int viewType) {
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

        holder.title.setText(typeList.get(position).getTypeName());
      //  holder.body.setText(typeList.get(position).getTypeIntro());
        holder.img.setBackgroundResource(typeList.get(position).getTypePic());
        holder.itemView.setTag(position);
    }

    @Override
    public int getItemCount() {
        return typeList.size();
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
