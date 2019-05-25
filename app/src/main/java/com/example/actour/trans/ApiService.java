package com.example.actour.trans;

import android.provider.MediaStore;

import com.example.actour.model.LoginResultModel;
import com.example.actour.model.RegisterResultModel;
import com.example.actour.model.User;
import com.example.actour.model.UserForLogin;


import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;
public interface ApiService {

    //로컬"http://10.0.2.2:8080";
    String API_URL= "http://10.0.2.2:8080";

    @POST("/user")
    Call<RegisterResultModel> registerUser(@Body User user);


    @POST("/user/login")
    Call<LoginResultModel> login(@Body UserForLogin userForLogin);

}
