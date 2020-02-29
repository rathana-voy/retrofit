package com.rathana.retrofit_demo.data;

import com.rathana.retrofit_demo.data.entiry.ArticleRequest;
import com.rathana.retrofit_demo.data.entiry.ArticleResponse;
import com.rathana.retrofit_demo.data.entiry.Post;

import org.json.JSONObject;

import java.util.List;

import retrofit2.Call;
import retrofit2.Response;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface ApiService {

    @GET("/posts")
    Call<List<Post>> getPosts();

    @GET("v1/api/articles")
    Call<ArticleResponse> getArticles(
            @Query("page") int page,
            @Query("limit") int limit
    );

    @GET("v1/api/articles/{page}/{limit}")
    Call<ArticleResponse> getArticle(
            @Path("page") int page,
            @Path("limit") int limit
    );

    @POST("v1/api/articles")
    Call<Response<JSONObject>> addArticle(@Body ArticleRequest request);

}
