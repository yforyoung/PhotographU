package com.example.y.photographu.fragment.school;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.example.y.photographu.BaseActivity;
import com.example.y.photographu.R;
import com.example.y.photographu.SchoolChooseActivity;
import com.example.y.photographu.Test;
import com.example.y.photographu.adapter.ProvinceAdapter;
import com.example.y.photographu.beans.Province;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import okhttp3.Request;
import okhttp3.Response;

import static android.support.constraint.Constraints.TAG;

public class FragmentProvinceShow extends Fragment {
    private ProvinceAdapter adapter;
    private List<Province> provinceList;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_province,container,false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        provinceList=new ArrayList<>();

        final Request request=new Request.Builder()
                .url("http://119.29.166.254:9090/api/provinces")
                .build();
        ((BaseActivity)getActivity()).doPost(request);
        ((BaseActivity)getActivity()).setHandleResponse(new BaseActivity.HandleResponse() {
            @Override
            public void handleResponses(Response response) throws IOException {
                String s=response.body().string();
                Log.i(TAG, "handleResponses: "+s);
                List<Province> provinces= new Gson().fromJson(s,new TypeToken<List<Province>>(){}.getType());
                provinceList.addAll(provinces);
                ((BaseActivity)getActivity()).refreshAdapter(adapter);
            }
        });


        RecyclerView recyclerView=getActivity().findViewById(R.id.province_list);
        recyclerView.addItemDecoration(new DividerItemDecoration(getActivity(),DividerItemDecoration.VERTICAL));
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity(),LinearLayout.VERTICAL,false));
        adapter=new ProvinceAdapter(provinceList);
        adapter.setOnItemClickListener(new ProvinceAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View v, int position) {
                Test.getInstance().school.setProvince(position+1);
                ((SchoolChooseActivity)getActivity()).showFragment(new FragmentCityShow());
            }
        });
        recyclerView.setAdapter(adapter);


    }
}
