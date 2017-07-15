package com.example.sumanth.studentmainpage;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.google.gson.JsonElement;

import retrofit.Callback;
import retrofit.RestAdapter;
import retrofit.RetrofitError;
import retrofit.client.Response;

import static com.example.sumanth.studentmainpage.StudentMainPage.ExamNames;
import static com.example.sumanth.studentmainpage.StudentMainPage.Request_status;

/**
 * Created by Sumanth on 16-Jul-17.
 */

public class Upcoming extends Fragment {
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState){
        View rootView = inflater.inflate(R.layout.upcoming, container, false);
                    ListView ls= (ListView) rootView.findViewById(R.id.upc_lst);
                    RestAdapter api = new RestAdapter.Builder().setEndpoint("http://10.49.181.118/StudentMainPage").build();
                    Student_Main_Retro ai = api.create(Student_Main_Retro.class);

                    ai.student_requests(new Callback<JsonElement>() {
                        @Override
                        public void success(JsonElement jsonElement, Response response) {
                            //get the requests from DB and add it to ExamNames and Request Status
                        }

                        @Override
                        public void failure(RetrofitError error) {

                        }
                    });
                    Upcoming_List up=new Upcoming_List(rootView.getContext(),ExamNames,Request_status);
                    ls.setAdapter(up);
        return rootView;
    }
}
