package com.example.y.photographu.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.y.photographu.BaseActivity;
import com.example.y.photographu.IntroduceActivity;
import com.example.y.photographu.R;
import com.example.y.photographu.Test;
import com.example.y.photographu.adapter.AppointmentPhotographAdapter;
import com.example.y.photographu.beans.PageBean;
import com.example.y.photographu.beans.Photographer;
import com.example.y.photographu.beans.ResponseData;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import okhttp3.FormBody;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

import static android.support.constraint.Constraints.TAG;

public class FragmentAppointment extends Fragment {
    private List<Photographer> photographerList;
    private int page=1;
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        photographerList = new ArrayList<>();
        initData();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_appointment, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

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

    private void initData() {
        RequestBody requestBody=new FormBody.Builder()
                .add("school","西华大学")
               // .add("school",Test.getInstance().school.getName())
                .add("page_size",20+"")
                .add("current_page",page+"")
                .build();
        final Request request=new Request.Builder()
                .post(requestBody)
                .url("http://www.xhban.com:8080/photograph_u/user/listPhotographersBySchoolWithPage")
                .build();
        ((BaseActivity)getActivity()).doPost(request);
        ((BaseActivity)getActivity()).setHandleResponse(new BaseActivity.HandleResponse() {
            @Override
            public void handleResponses(Response response) throws IOException {
                String s=response.body().string();
                ResponseData responseData=new Gson().fromJson(s,new TypeToken<ResponseData<PageBean>>(){}.getType());
                PageBean pageBean= (PageBean) responseData.getData();
               // List<Object> photographerL=pageBean.getData();
                Log.i(TAG, "handleResponses: ");

            }
        });

    }
}
