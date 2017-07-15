package com.example.sumanth.studentmainpage;

import com.google.gson.JsonElement;

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
    @POST("/insert.php")
    void update(@Field("Subject") String subject,
                @Field("Attended") int attended,
                @Field("Total") int total, Callback<JsonElement> response);

    @GET("/spn_pop.php")
    void spn_pop(Callback<JsonElement> response);

    @GET("/student_requests.php")
    void student_requests(Callback<JsonElement> response);
}