package com.example.y.photographu.fragment.school;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
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
import com.example.y.photographu.FindPasswordActivity;
import com.example.y.photographu.LoadActivity;
import com.example.y.photographu.R;
import com.example.y.photographu.Test;
import com.example.y.photographu.Util;
import com.example.y.photographu.beans.ResponseData;
import com.google.gson.Gson;
import com.google.gson.internal.LinkedTreeMap;
import com.google.gson.reflect.TypeToken;

import org.json.JSONArray;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import okhttp3.FormBody;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

import static android.support.constraint.Constraints.TAG;

public class FragmentSchoolShow extends Fragment {
    private List<String> schoolList;
    ArrayAdapter adapter;
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
        return inflater.inflate(R.layout.fragment_school,container,false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        schoolList=new ArrayList<>();

        final RequestBody requestBody=new FormBody.Builder()
                .add("name", Test.getInstance().school.getCity())
                .build();
        final Request request=new Request.Builder()
                .url("http://119.29.166.254:9090/api/university/getUniversityByCityName")
                .post(requestBody)
                .build();
        ((BaseActivity)getActivity()).doPost(request);
        ((BaseActivity)getActivity()).setHandleResponse(new BaseActivity.HandleResponse() {
            @Override
            public void handleResponses(Response response) throws IOException {
                String s=response.body().string();
                List<LinkedTreeMap> list=new Gson().fromJson(s,new TypeToken<List<LinkedTreeMap>>(){}.getType());
                for (int i=0;i<list.size();i++){
                    schoolList.add((String) list.get(i).get("name"));
                }
                handler.sendMessage(Message.obtain());

            }
        });



        adapter = new ArrayAdapter<>(getActivity(), android.R.layout.simple_list_item_1, schoolList);
        ListView listView = getActivity().findViewById(R.id.school_list);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Test.getInstance().school.setName(schoolList.get(position));

                RequestBody requestBody1=new FormBody.Builder()
                        .add("school",schoolList.get(position))
                        .add("nickname",Test.getInstance().user.getNickname())
                        .build();
                Request request1=new Request.Builder()
                        .post(requestBody1)
                        .url("http://www.xhban.com:8080/photograph_u/user/updateInfo")
                        .addHeader("cookie",Test.getInstance().cookie)
                        .build();
                ((BaseActivity)getActivity()).doPost(request1);
                ((BaseActivity)getActivity()).setHandleResponse(new BaseActivity.HandleResponse() {
                    @Override
                    public void handleResponses(Response response) throws IOException {
                        String s=response.body().string();

                        ResponseData responseData=new Gson().fromJson(s,ResponseData.class);
                        if (responseData.getCode()==0){
                            ((BaseActivity)getActivity()).showToast("修改成功");
                            Test.getInstance().user.setSchool(Test.getInstance().school.getName());
                            String m=new Gson().toJson(Test.getInstance().user);
                            new Util().save(m,getActivity());
                        }
                        else{
                            ((BaseActivity)getActivity()).showToast(responseData.getMessage());
                        }
                        getActivity().finish();
                    }
                });

            }
        });


    }
}
