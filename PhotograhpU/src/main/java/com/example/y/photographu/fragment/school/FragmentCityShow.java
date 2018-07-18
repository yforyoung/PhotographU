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

import com.example.y.photographu.App;
import com.example.y.photographu.R;
import com.example.y.photographu.activity.SchoolChooseActivity;
import com.example.y.photographu.util.OkHttpUtils;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import okhttp3.Response;

public class FragmentCityShow extends Fragment {
    private static final String TAG = "FragmentCityShow";
    private List<String> cityList;
    private ArrayAdapter adapter;
    @SuppressLint("HandlerLeak")
    private Handler handler = new Handler() {
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
        Map<String, String> param = new HashMap<>();
        param.put("id", App.getInstance().school.getProvince() + "");
        OkHttpUtils.doPost("http://119.29.166.254:9090/api/province/getCitiesByProvinceId/", param,
                null, null,
                new OkHttpUtils.MyCallback() {
                    @Override
                    public void onResponse(Response response) throws IOException {
                        String s = response.body().string();
                        Log.i(TAG, "handleResponses: " + s);
                        s = s.substring(2, s.indexOf("\"]"));
                        Log.i(TAG, "handleResponses: " + s);
                        String citys[] = s.split("\",\"");
                        cityList.addAll(Arrays.asList(citys));
                        handler.sendMessage(Message.obtain());
                    }

                    @Override
                    public void onFailure(IOException e) {

                    }
                });

        adapter = new ArrayAdapter<>(getActivity(), android.R.layout.simple_list_item_1, cityList);
        ListView listView = getActivity().findViewById(R.id.city_list);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                App.getInstance().school.setCity(cityList.get(position));
                ((SchoolChooseActivity) getActivity()).showFragment(new FragmentSchoolShow());

            }
        });


    }
}
