package com.example.y.photographu.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.y.photographu.IntroduceActivity;
import com.example.y.photographu.R;
import com.example.y.photographu.Test;
import com.example.y.photographu.adapter.AppointmentPhotographAdapter;
import com.example.y.photographu.beans.Photographer;

import java.util.ArrayList;
import java.util.List;

public class FragmentAppointment extends Fragment {
    private List<Photographer> photographerList;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_appointment, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        photographerList = new ArrayList<>();

        RecyclerView recyclerView = getActivity().findViewById(R.id.appointment_list);
        recyclerView.setLayoutManager(new GridLayoutManager(getActivity(), 2));
        AppointmentPhotographAdapter adapter = new AppointmentPhotographAdapter(photographerList);
        /*adapter.setOnItemClickListener(new AppointmentPhotographAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View v, int tag) {
                Intent intent=new Intent(getActivity(), IntroduceActivity.class);
                intent.putExtra("photographer",photographerList.get(tag));
                startActivity(intent);
            }
        });*/
        recyclerView.setAdapter(adapter);

    }
}
