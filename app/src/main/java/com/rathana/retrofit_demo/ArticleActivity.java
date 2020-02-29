package com.rathana.retrofit_demo;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.rathana.retrofit_demo.adapter.ArticleAdapter;
import com.rathana.retrofit_demo.data.ApiService;
import com.rathana.retrofit_demo.data.ServiceGenerator;
import com.rathana.retrofit_demo.data.entiry.Article;
import com.rathana.retrofit_demo.data.entiry.ArticleResponse;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ArticleActivity extends AppCompatActivity {

    private ApiService apiService;

    private RecyclerView recyclerView;
    private ArticleAdapter articleAdapter;

    private FloatingActionButton btnAdd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_article);

        btnAdd = findViewById(R.id.btnAdd);

        apiService = ServiceGenerator.create(ApiService.class);
        setupRecyclerView();
        getArticles(1, 20);

        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ArticleActivity.this, AddArticleActivity.class);
                startActivityForResult(intent, 1);
            }
        });
    }

    private void setupRecyclerView() {
        recyclerView = findViewById(R.id.articleRecyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        articleAdapter = new ArticleAdapter(this, new ArrayList<Article>());
        recyclerView.setAdapter(articleAdapter);
    }

    private void getArticles(int page, int limit) {
        Call<ArticleResponse> call = apiService.getArticles(page, limit);
        call.enqueue(new Callback<ArticleResponse>() {
            @Override
            public void onResponse(Call<ArticleResponse> call, Response<ArticleResponse> response) {

                try {
                    Log.e("ARTICLES", "" + response.body().getData());
                    if (response.body().getData() != null) {
                        articleAdapter.addItems(response.body().getData());
                    }

                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(Call<ArticleResponse> call, Throwable t) {
                Log.e("ARTICLES", "" + t.toString());
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode==1 && resultCode==RESULT_OK){

            getArticles(1,20);
        }
    }
}
