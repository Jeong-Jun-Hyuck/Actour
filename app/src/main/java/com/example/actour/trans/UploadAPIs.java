package com.example.actour.trans;

import com.example.actour.model.ProductSet;
import com.example.actour.model.Result;

import okhttp3.MultipartBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;

public interface UploadAPIs {

    @Multipart
    @POST("/user/upload")
    Call<Result> uploadImage(@Part MultipartBody.Part file);

    @POST("/user/uploadproductSet")
    Call<Result> uploadproductSet(@Body ProductSet productSet);

    @POST("/user/makeProductID")
    Call<Integer> makeProductID();


}
