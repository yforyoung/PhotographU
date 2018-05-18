package com.example.y.potographu.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.y.potographu.R;
import com.example.y.potographu.adapter.HomeStyleAdapter;

public class FragmentAppointment extends Fragment {
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_appointment,container,false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        RecyclerView recyclerView=getActivity().findViewById(R.id.appointment_list);
        recyclerView.setLayoutManager(new GridLayoutManager(getActivity(),2));
        HomeStyleAdapter adapter=new HomeStyleAdapter();
        recyclerView.setAdapter(adapter);

    }
}
