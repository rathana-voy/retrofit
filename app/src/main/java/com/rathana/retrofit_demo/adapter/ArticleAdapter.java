package com.rathana.retrofit_demo.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.rathana.retrofit_demo.ArticleDetailActivity;
import com.rathana.retrofit_demo.ImageBinder;
import com.rathana.retrofit_demo.R;
import com.rathana.retrofit_demo.data.entiry.Article;

import java.util.List;

public class ArticleAdapter extends RecyclerView.Adapter<ArticleAdapter.ViewHolder> {

    private List<Article> articles;
    private Context context;

    public ArticleAdapter(Context context, List<Article> articles) {
        this.articles = articles;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_article, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.bind(articles.get(position));
    }

    @Override
    public int getItemCount() {
        return articles.size();
    }

    public void addItems(List<Article> articles) {
        this.articles.clear();
        this.articles.addAll(articles);
        notifyDataSetChanged();
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        ImageView image, btnFavorite;
        TextView txtTitle, txtDate;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            image = itemView.findViewById(R.id.image);
            btnFavorite = itemView.findViewById(R.id.btnFavorite);
            txtTitle = itemView.findViewById(R.id.textTitle);
            txtDate = itemView.findViewById(R.id.textDate);
        }

        void bind(Article article) {
            txtTitle.setText(article.getTitle());
            txtDate.setText(article.getCreatedDate());
            ImageBinder.bind(context, article.getThumbnail(), image);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(context, ArticleDetailActivity.class);
                    intent.putExtra("article", article);
                    context.startActivity(intent);
                }
            });
        }
    }

}
