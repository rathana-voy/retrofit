package com.rathana.retrofit_demo.data;

import com.rathana.retrofit_demo.data.entiry.Post;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface ApiService {

    @GET("/posts")
    Call<List<Post>> getPosts();

}
