package com.example.practicebnews;

import retrofit2.Call;
import retrofit2.http.GET;

public interface News {
    @GET("v2/top-headlines?country=us&apiKey=d18cfdeee6c44308a2c20a787f4f1b8e")
    Call<Topnews> getMyNews();
}