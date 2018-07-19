package com.example.y.photographu.view.fragment;

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
import com.example.y.photographu.R;
import com.example.y.photographu.adapter.AppointmentPhotographAdapter;
import com.example.y.photographu.beans.PageBean;
import com.example.y.photographu.beans.Photographer;
import com.example.y.photographu.beans.ResponseData;
import com.example.y.photographu.util.OkHttpUtils;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
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

        Map<String ,String> param=new HashMap<>();
        param.put("school","西华大学");
        param.put("page_size", String.valueOf(20));
        param.put("current_page", String.valueOf(page));
        OkHttpUtils.doPost("http://www.xhban.com:8080/photograph_u/user/listPhotographersBySchoolWithPage",
                param, null, null, new OkHttpUtils.MyCallback() {
                    @Override
                    public void onResponse(Response response) throws IOException {
                        String s=response.body().string();
                        ResponseData responseData=new Gson().fromJson(s,new TypeToken<ResponseData<PageBean>>(){}.getType());
                        PageBean pageBean= (PageBean) responseData.getData();
                        // List<Object> photographerL=pageBean.getData();
                        Log.i(TAG, "handleResponses: "+s);
                    }

                    @Override
                    public void onFailure(IOException e) {

                    }
                });

    }
}
