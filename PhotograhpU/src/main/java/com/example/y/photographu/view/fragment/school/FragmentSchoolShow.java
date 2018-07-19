package com.example.y.photographu.view.fragment.school;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.y.photographu.App;
import com.example.y.photographu.view.activity.BaseActivity;
import com.example.y.photographu.R;
import com.example.y.photographu.beans.ResponseData;
import com.example.y.photographu.util.FileUtil;
import com.example.y.photographu.util.OkHttpUtils;
import com.example.y.photographu.util.SpfUtil;
import com.google.gson.Gson;
import com.google.gson.internal.LinkedTreeMap;
import com.google.gson.reflect.TypeToken;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import okhttp3.Response;

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

        final Map<String ,String> param=new HashMap<>();
        param.put("name",App.getInstance().school.getCity());

        OkHttpUtils.doPost("http://119.29.166.254:9090/api/university/getUniversityByCityName",
        param,null,null, new OkHttpUtils.MyCallback() {
                    @Override
                    public void onResponse(Response response) throws IOException {
                        String s=response.body().string();
                        List<LinkedTreeMap> list=new Gson().fromJson(s,new TypeToken<List<LinkedTreeMap>>(){}.getType());
                        for (int i=0;i<list.size();i++){
                            schoolList.add((String) list.get(i).get("name"));
                        }
                        handler.sendMessage(Message.obtain());
                    }

                    @Override
                    public void onFailure(IOException e) {

                    }
                });


        adapter = new ArrayAdapter<>(getActivity(), android.R.layout.simple_list_item_1, schoolList);
        ListView listView = getActivity().findViewById(R.id.school_list);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                App.getInstance().school.setName(schoolList.get(position));

                Map<String ,String > params=new HashMap<>();
                params.put("school",schoolList.get(position));
                params.put("nickname", App.getInstance().user.getNickname());
                OkHttpUtils.doPost("http://www.xhban.com:8080/photograph_u/user/updateInfo",
                        params, "cookie", SpfUtil.getString("user_cookie", ""),
                        new OkHttpUtils.MyCallback() {
                            @Override
                            public void onResponse(Response response) throws IOException {
                                String s=response.body().string();

                                ResponseData responseData=new Gson().fromJson(s,ResponseData.class);
                                if (responseData.getCode()==0){
                                    ((BaseActivity)getActivity()).showToast("修改成功");
                                    App.getInstance().user.setSchool(App.getInstance().school.getName());
                                    String m=new Gson().toJson(App.getInstance().user);
                                    FileUtil.save("userData",m);
                                }
                                else{
                                    ((BaseActivity)getActivity()).showToast(responseData.getMessage());
                                }
                                getActivity().finish();
                            }

                            @Override
                            public void onFailure(IOException e) {

                            }
                        });

            }
        });


    }
}
