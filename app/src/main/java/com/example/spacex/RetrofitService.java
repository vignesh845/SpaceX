package com.example.spacex;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface RetrofitService {

    @GET("v4/crew")
    Call<List<CrewModel>> getCrew();
}
