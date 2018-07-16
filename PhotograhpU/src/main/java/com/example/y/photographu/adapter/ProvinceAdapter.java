package com.example.y.photographu.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.y.photographu.beans.Province;

import java.util.List;

public class ProvinceAdapter extends RecyclerView.Adapter<ProvinceAdapter.ViewHolder> {
    private List<Province> provinceList;
    private OnItemClickListener onItemClickListener;

    public interface OnItemClickListener{
        void onItemClick(View v,int position);
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

    public ProvinceAdapter(List<Province> provinceList) {
        this.provinceList = provinceList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view=LayoutInflater.from(parent.getContext())
                .inflate(android.R.layout.simple_list_item_1,parent,false);
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (onItemClickListener!=null){
                    onItemClickListener.onItemClick(v, (Integer) v.getTag());
                }
            }
        });
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.ProvinceName.setText(provinceList.get(position).getName());
        holder.itemView.setTag(position);
    }


    @Override
    public int getItemCount() {
        return provinceList.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        TextView ProvinceName;
        ViewHolder(View itemView) {
            super(itemView);
            ProvinceName=itemView.findViewById(android.R.id.text1);
        }
    }
}
