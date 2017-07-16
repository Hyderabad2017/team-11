package com.example.sumanth.studentmainpage;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.gson.JsonElement;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import retrofit.Callback;
import retrofit.RestAdapter;
import retrofit.RetrofitError;
import retrofit.client.Response;

import static com.example.sumanth.studentmainpage.StudentMainPage.examName;

/**
 * Created by Sumanth on 16-Jul-17.
 */

public class Request extends Fragment {


    private static void loadSpinnerData(final View rootView) {

        final List<String> spinnerArray =  new ArrayList<>();
        RestAdapter api = new RestAdapter.Builder().setEndpoint("http://hemanthraparthi.000webhostapp.com/CodeForGood/StudentMainPage").build();
        Student_Main_Retro ai = api.create(Student_Main_Retro.class);
        //Retrieve from DB and add to spinnerArray
        ai.spn_pop(new Callback<JsonElement>() {
            @Override
            public void success(JsonElement jsonElement, Response response) {
                JSONObject jsonObject = null;
                try {
                    jsonObject = new JSONObject(jsonElement.toString());
                    if(jsonObject.getInt("result")==1)
                    {
                        JSONArray bunk= jsonObject.getJSONArray("exam");
                        for(int i=0;i<bunk.length();i++)
                        {
                            JSONObject ele=bunk.getJSONObject(i);
                            spinnerArray.add(ele.getString("name"));
                        }
                    }
                }
                catch (JSONException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void failure(RetrofitError error) {
                Toast.makeText(rootView.getContext(), error.toString(), Toast.LENGTH_LONG).show();
                Log.e(null,error.getMessage());
            }
        });

        ArrayAdapter<String> adapter = new ArrayAdapter<>(
                rootView.getContext(), R.layout.request, spinnerArray);

        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        Spinner sItems = (Spinner) rootView.findViewById(R.id.exam_spn);
        sItems.setAdapter(adapter);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
       View rootView = inflater.inflate(R.layout.request, container, false);
        Spinner spn=(Spinner) rootView.findViewById(R.id.exam_spn);
        loadSpinnerData(rootView);
        spn.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
                        public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                            examName = String.valueOf(adapterView.getSelectedItem());
                        }

                        @Override
                        public void onNothingSelected(AdapterView<?> adapterView) {

                        }
                    });
                    final EditText medium=(EditText)rootView.findViewById(R.id.med_txt);
                    final EditText date=(EditText)rootView.findViewById(R.id.date_txt);
                    final EditText city=(EditText)rootView.findViewById(R.id.city_txt);
                    final DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
                    final Date[] date1 = new Date[1];
                    //send the examName , medium, date, city to the database
                    Button request=(Button)rootView.findViewById(R.id.req_btn);
                    request.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(final View view) {
                            RestAdapter api = new RestAdapter.Builder().setEndpoint("http://hemanthraparthi.000webhostapp.com/CodeForGood/StudentMainPage").build();
                            Student_Main_Retro ai = api.create(Student_Main_Retro.class);
                            try {
                                date1[0] =df.parse(date.getText().toString());
                            } catch (ParseException e) {
                                e.printStackTrace();
                            }
                            ai.set_request(1,examName,
                                    Integer.parseInt(city.getText().toString()),
                                    Integer.parseInt(medium.getText().toString()), date1[0], new Callback<JsonElement>() {
                                        @Override
                                        public void success(JsonElement jsonElement, Response response) {
                                            Toast.makeText(view.getContext(), "", Toast.LENGTH_SHORT).show();
                                        }

                                        @Override
                                        public void failure(RetrofitError error) {

                                        }
                                    });
                        }
                    });

        return rootView;
    }
}
