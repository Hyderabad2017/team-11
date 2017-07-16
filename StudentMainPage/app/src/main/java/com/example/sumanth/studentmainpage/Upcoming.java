package com.example.sumanth.studentmainpage;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.google.gson.JsonElement;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import retrofit.Callback;
import retrofit.RestAdapter;
import retrofit.RetrofitError;
import retrofit.client.Response;

import static com.example.sumanth.studentmainpage.StudentMainPage.ExamNames;
import static com.example.sumanth.studentmainpage.StudentMainPage.Request_status;


public class Upcoming extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState){
        View rootView = inflater.inflate(R.layout.upcoming, container, false);
                    ListView ls= (ListView) rootView.findViewById(R.id.upc_lst);
                    RestAdapter api = new RestAdapter.Builder().setEndpoint("http://hemanthraparthi.000webhostapp.com/CodeForGood/StudentMainPage").build();
                    final Student_Main_Retro ai = api.create(Student_Main_Retro.class);

                    ai.student_requests(new Callback<JsonElement>() {
                        @Override
                        public void success(JsonElement jsonElement, Response response) {
                            ai.get_requests(new Callback<JsonElement>() {
                                @Override
                                public void success(JsonElement jsonElement, Response response) {
                                    JSONObject jsonObject = null;
                                    try {
                                        jsonObject = new JSONObject(jsonElement.toString());
                                        if(jsonObject.getInt("result")==1)
                                        {
                                            JSONArray bunk= jsonObject.getJSONArray("data");
                                            for(int i=0;i<bunk.length();i++)
                                            {
                                                JSONObject ele=bunk.getJSONObject(i);
                                                ExamNames.add(ele.getString("exam"));
                                                Request_status.add("UnAssigned");
                                            }
                                        }
                                    }
                                    catch (JSONException e) {
                                        e.printStackTrace();
                                    }
                                }

                                @Override
                                public void failure(RetrofitError error) {

                                }
                            });
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
