package com.example.sumanth.studentmainpage;

import com.google.gson.JsonElement;

import java.util.Date;

import retrofit.Callback;
import retrofit.http.Field;
import retrofit.http.FormUrlEncoded;
import retrofit.http.GET;
import retrofit.http.POST;

/**
 * Created by Sumanth on 15-Jul-17.
 */

public interface Student_Main_Retro {
    @FormUrlEncoded
    @POST("/set_request.php")
    void set_request(@Field("userid") int userid,
                     @Field("exam") String subject,
                     @Field("location") int attended,
                     @Field("language") int total,
                     @Field("date")Date date, Callback<JsonElement> response);

    @GET("/get_request.php")
    void get_requests(Callback<JsonElement> response);

    @GET("/spn_pop.php")
    void spn_pop(Callback<JsonElement> response);

    @GET("/student_requests.php")
    void student_requests(Callback<JsonElement> response);
}