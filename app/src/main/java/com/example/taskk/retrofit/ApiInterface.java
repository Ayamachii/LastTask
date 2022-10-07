package com.example.taskk.retrofit;

import com.example.taskk.models.Root;

import retrofit2.Call;
import retrofit2.http.GET;

public interface ApiInterface {

    @GET("/products")
    Call<Root> getRoot();
}
