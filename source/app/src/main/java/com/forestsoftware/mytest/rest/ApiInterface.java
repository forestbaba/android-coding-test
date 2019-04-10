package com.forestsoftware.mytest.rest;


import com.forestsoftware.mytest.model.MainResponse;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface ApiInterface {

    @GET("repos/rails/rails/commits?per_page=50&sha=24ece037b8abbd77accf6c4d766d1dcc231388af")
    Call<List<MainResponse>> getCurrent();


}
