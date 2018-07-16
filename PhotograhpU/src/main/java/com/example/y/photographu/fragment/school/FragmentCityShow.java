package com.example.y.photographu.fragment.school;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.y.photographu.BaseActivity;
import com.example.y.photographu.R;
import com.example.y.photographu.SchoolChooseActivity;
import com.example.y.photographu.Test;
import com.example.y.photographu.beans.School;
import com.google.gson.JsonArray;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import okhttp3.FormBody;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class FragmentCityShow extends Fragment {
    private static final String TAG = "FragmentCityShow";
    private List<String> cityList;
    private ArrayAdapter adapter;
    @SuppressLint("HandlerLeak")
    private Handler handler=new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            adapter.notifyDataSetChanged();
        }
    };


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_city, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        cityList = new ArrayList<>();
        /*查询城市*/
        RequestBody requestBody = new FormBody.Builder()
                .add("id", Test.getInstance().school.getProvince() + "")
                .build();
        final Request request = new Request.Builder()
                .post(requestBody)
                .url("http://119.29.166.254:9090/api/province/getCitiesByProvinceId/")
                .build();
        ((BaseActivity) getActivity()).doPost(request);
        ((BaseActivity) getActivity()).setHandleResponse(new BaseActivity.HandleResponse() {
            @Override
            public void handleResponses(Response response) throws IOException {
                String s = response.body().string();
                Log.i(TAG, "handleResponses: " + s);
                s = s.substring(2, s.indexOf("\"]"));
                Log.i(TAG, "handleResponses: " + s);
                String citys[] = s.split("\",\"");
                cityList.addAll(Arrays.asList(citys));
                handler.sendMessage(Message.obtain());
            }
        });

        adapter = new ArrayAdapter<>(getActivity(), android.R.layout.simple_list_item_1, cityList);
        ListView listView = getActivity().findViewById(R.id.city_list);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Test.getInstance().school.setCity(cityList.get(position));
                ((SchoolChooseActivity)getActivity()).showFragment(new FragmentSchoolShow());

            }
        });


    }
}
