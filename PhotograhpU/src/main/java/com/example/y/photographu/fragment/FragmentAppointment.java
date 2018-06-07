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

        List<Integer> photoList1 = new ArrayList<>();
        Photographer photographer1 = Test.getInstence().photographer1;
        photoList1.add(R.drawable.tu1);
        photoList1.add(R.drawable.tu2);
        photoList1.add(R.drawable.tu3);
        photographer1.setPhotoList(photoList1);
        photographerList.add(photographer1);


        List<Integer> photoList2 = new ArrayList<>();
        photoList2.add(R.drawable.tu4);
        photoList2.add(R.drawable.tu5);
        photoList2.add(R.drawable.tu6);
        Photographer photographer2 = Test.getInstence().photographer2;
        photographer2.setPhotoList(photoList2);
        photographerList.add(photographer2);

        List<Integer> photoList3 = new ArrayList<>();
        photoList3.add(R.drawable.personal_index);
        photoList3.add(R.drawable.people_index);
        photoList3.add(R.drawable.gu);
        Photographer photographer3 = Test.getInstence().photographer3;
        photographer3.setPhotoList(photoList3);
        photographerList.add(photographer3);

        List<Integer> photoList4 = new ArrayList<>();
        photoList4.add(R.drawable.roll_2);
        photoList4.add(R.drawable.people_index);
        photoList4.add(R.drawable.graduation);
        Photographer photographer4 = Test.getInstence().photographer4;
        photographer4.setPhotoList(photoList4);
        photographerList.add(photographer4);

        List<Integer> photoList5 = new ArrayList<>();
        photoList5.add(R.drawable.tu7);
        photoList5.add(R.drawable.tu6);
        photoList5.add(R.drawable.tu2);
        Photographer photographer5 = Test.getInstence().photographer5;
        photographer5.setPhotoList(photoList5);
        photographerList.add(photographer5);

        List<Integer> photoList6 = new ArrayList<>();

        photoList6.add(R.drawable.tu2);
        photoList6.add(R.drawable.tu5);
        photoList6.add(R.drawable.tu3);
        Photographer photographer6 = Test.getInstence().photographer6;
        photographer6.setPhotoList(photoList6);
        photographerList.add(photographer6);


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
