package com.rathana.retrofit_demo;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.rathana.retrofit_demo.data.entiry.Article;

public class ArticleDetailActivity extends AppCompatActivity {

    ImageView image, btnFavorite;
    TextView txtTitle, txtDate, txtContent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_article_detail);

        image = findViewById(R.id.image);
        btnFavorite = findViewById(R.id.btnFavorite);
        txtTitle = findViewById(R.id.textTitle);
        txtDate = findViewById(R.id.textDate);
        txtContent = findViewById(R.id.textContent);

        onDataReady();
    }

    private void onDataReady() {
        if (getIntent() != null) {
            Article article = getIntent().getParcelableExtra("article");
            txtTitle.setText(article.getTitle());
            txtContent.setText(article.getCreatedDate());
            txtContent.setText(article.getDescription());
            ImageBinder.bind(this, article.getThumbnail(), image);
        }
    }
}
