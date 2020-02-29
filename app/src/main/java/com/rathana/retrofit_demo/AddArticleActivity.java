package com.rathana.retrofit_demo;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.textfield.TextInputLayout;
import com.rathana.retrofit_demo.data.ApiService;
import com.rathana.retrofit_demo.data.ServiceGenerator;
import com.rathana.retrofit_demo.data.entiry.ArticleRequest;

import org.json.JSONObject;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AddArticleActivity extends AppCompatActivity {

    TextInputLayout txtTitle, txtAuthorId, txtDate, txtContent;
    Button btnSave;

    ApiService apiService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_article);

        txtTitle = findViewById(R.id.txtTitle);
        txtAuthorId = findViewById(R.id.txtAuthorId);
        txtDate = findViewById(R.id.txtDate);
        txtContent = findViewById(R.id.txtContent);
        btnSave = findViewById(R.id.btnSave);

        apiService = ServiceGenerator.create(ApiService.class);

        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                saveArticle();
            }
        });
    }

    private void saveArticle() {
        ArticleRequest request = new ArticleRequest();
        request.setTitle(txtTitle.getEditText().getText().toString());
        request.setAuthor(Integer.parseInt(txtAuthorId.getEditText().getText().toString()));
        request.setDescription(txtContent.getEditText().getText().toString());
        request.setStatus("true");
        request.setCategoryId(1);
        //save to server

        Call<Response<JSONObject>> call = apiService.addArticle(request);
        call.enqueue(new Callback<Response<JSONObject>>() {
            @Override
            public void onResponse(Call<Response<JSONObject>> call, Response<Response<JSONObject>> response) {
                setResult(RESULT_OK);
                finish();
            }

            @Override
            public void onFailure(Call<Response<JSONObject>> call, Throwable t) {

            }
        });
    }
}

