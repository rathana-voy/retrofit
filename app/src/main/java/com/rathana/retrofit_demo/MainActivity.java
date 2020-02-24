package com.rathana.retrofit_demo;

import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import com.rathana.retrofit_demo.data.ApiService;
import com.rathana.retrofit_demo.data.ServiceGenerator;
import com.rathana.retrofit_demo.data.entiry.Post;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    private ApiService apiService;
    private ListView listView;
    ArrayAdapter<String> arrayAdapter;
    List<String> data = new ArrayList();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        apiService = ServiceGenerator.create(ApiService.class);

        setupListView();
        //get posts
        getPosts();
    }

    private void setupListView() {
        listView = findViewById(R.id.listView);
        arrayAdapter = new ArrayAdapter<String>(
                this,
                android.R.layout.simple_list_item_1,
                data
        );
        listView.setAdapter(arrayAdapter);
    }

    private void getPosts() {
        Call<List<Post>> call = apiService.getPosts();

        call.enqueue(new Callback<List<Post>>() {
            @Override
            public void onResponse(Call<List<Post>> call, Response<List<Post>> response) {
                List<Post> posts = response.body();
                for (Post post : posts) {
                    data.add(post.getTitle());
                }
                arrayAdapter.notifyDataSetChanged();
            }

            @Override
            public void onFailure(Call<List<Post>> call, Throwable t) {
                Log.e("@@@@", t.toString());
            }
        });

    }
}
